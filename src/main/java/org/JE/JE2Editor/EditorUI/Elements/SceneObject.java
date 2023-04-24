package org.JE.JE2Editor.EditorUI.Elements;

import org.JE.JE2.Objects.GameObject;
import org.JE.JE2.Objects.Identity;

import org.JE.JE2.Objects.Scripts.Transform;
import org.JE.JE2.Rendering.Renderers.SpriteRenderer;
import org.JE.JE2.Rendering.Shaders.ShaderProgram;
import org.JE.JE2.Rendering.Texture;
import org.JE.JE2.Resources.DataLoader;
import org.JE.JE2Editor.Tools.TransformUpdater;
import org.joml.Vector2f;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import org.JE.JE2.Objects.Scripts.Script;

public class SceneObject{
    public ArrayList<Script> scripts = new ArrayList<>();
    public ArrayList<SceneObject> children = new ArrayList<>();
    public GameObject sceneRef;
    public SceneObject(){
        sceneRef = new GameObject();
        scripts.add(new Transform());
        sceneRef.addScript(new TransformUpdater((Transform) scripts.get(0)));
        SpriteRenderer sr = new SpriteRenderer(ShaderProgram.spriteShader());
        sr.setTexture(Texture.checkExistElseCreate("baseGameObject",-1,"texture1.png"));
        sr.setNormalTexture(Texture.checkExistElseCreate("baseGameObject_N",-1,"texture1_N.png"));
        sceneRef.addScript(sr);
    }
    public SceneObject(Identity id, Texture image, Vector2f Scale, Script[] componentObjects){
        sceneRef = GameObject.Sprite(ShaderProgram.spriteShader(), image);
        sceneRef.setIdentity(id);
        sceneRef.getTransform().setScale(Scale);
        scripts.add(new Transform());
        sceneRef.addScript(new TransformUpdater((Transform) scripts.get(0)));
        scripts.addAll(Arrays.asList(componentObjects));
        SpriteRenderer sr = new SpriteRenderer(ShaderProgram.spriteShader());
        sr.setTexture(Texture.checkExistElseCreate("baseGameObject",-1,"texture1.png"));
        sr.setNormalTexture(Texture.checkExistElseCreate("baseGameObject_N",-1,"texture1_N.png"));
        sceneRef.addScript(sr);
    }
    public SceneObject(GameObject reference){
        sceneRef = reference;
        scripts.addAll(reference.getScripts());
        SpriteRenderer sr = new SpriteRenderer(ShaderProgram.spriteShader());
        sr.setTexture(Texture.checkExistElseCreate("baseGameObject",-1,"texture1.png"));
        sr.setNormalTexture(Texture.checkExistElseCreate("baseGameObject_N",-1,"texture1_N.png"));
        sceneRef.addScript(sr);
    }

    public SceneObject(Identity id, Texture image, Vector2f Scale, Script[] componentObjects, SceneObject[] children){
        sceneRef = GameObject.Sprite(ShaderProgram.spriteShader(), image);
        sceneRef.setIdentity(id);
        sceneRef.getTransform().setScale(Scale);
        this.children.addAll(Arrays.asList(children));
        scripts.add(new Transform());
        sceneRef.addScript(new TransformUpdater((Transform) scripts.get(0)));
        scripts.addAll(Arrays.asList(componentObjects));
        SpriteRenderer sr = new SpriteRenderer(ShaderProgram.spriteShader());
        sr.setTexture(Texture.checkExistElseCreate("baseGameObject",-1,"texture1.png"));
        sr.setNormalTexture(Texture.checkExistElseCreate("baseGameObject_N",-1,"texture1_N.png"));
        sceneRef.addScript(sr);
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
