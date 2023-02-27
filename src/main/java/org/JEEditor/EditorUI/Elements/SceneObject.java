package org.JEEditor.EditorUI.Elements;

import JE.Objects.GameObject;
import JE.Objects.Identity;
import JE.Objects.Scripts.Base.Script;
import JE.Objects.Scripts.Common.Transform;
import JE.Rendering.Shaders.ShaderProgram;
import JE.Rendering.Texture;
import org.joml.Vector2f;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class SceneObject{
    public ArrayList<Script> scripts = new ArrayList<>();
    public ArrayList<SceneObject> children = new ArrayList<>();
    public GameObject sceneRef;
    public SceneObject(){
        sceneRef = new GameObject();
        scripts.add(new Transform());
    }
    public SceneObject(Identity id, Texture image, Vector2f Scale, Script[] componentObjects){
        sceneRef = GameObject.Sprite(ShaderProgram.spriteShader(), image);
        sceneRef.setIdentity(id);
        sceneRef.getTransform().setScale(Scale);
        scripts.add(new Transform());
        scripts.addAll(Arrays.asList(componentObjects));

    }
    public SceneObject(GameObject reference){
        sceneRef = reference;
        scripts.addAll(reference.getScripts());
    }

    public SceneObject(Identity id, Texture image, Vector2f Scale, Script[] componentObjects, SceneObject[] children){
        sceneRef = GameObject.Sprite(ShaderProgram.spriteShader(), image);
        sceneRef.setIdentity(id);
        sceneRef.getTransform().setScale(Scale);
        this.children.addAll(Arrays.asList(children));
        scripts.add(new Transform());
        scripts.addAll(Arrays.asList(componentObjects));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("start\n");
        try {
            sb.append("id:").append(serializeObject(sceneRef.identity())).append("\n");
        }catch (Exception e){
            System.out.println("error serializing object: " + e.getMessage());
        }
        for (Script script :
                scripts) {
            try {
                sb.append(serializeObject(script)).append("\n");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        sb.append("end\n");
        return sb.toString();
    }

    private String serializeField(Field field, Object ref) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        field.setAccessible(true);
        oos.writeObject(field.get(ref));

        oos.flush();
        oos.close();
        bos.close();
        return bos.toString();
    }

    private String serializeObject(Object object) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
