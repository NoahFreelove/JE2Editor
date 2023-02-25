package org.JE.EditorUI.Elements;

import JE.Objects.GameObject;
import JE.Objects.Identity;
import JE.Objects.Scripts.Base.Script;
import JE.Objects.Scripts.Common.Transform;
import JE.Rendering.Shaders.ShaderProgram;
import JE.Rendering.Texture;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.Arrays;

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
        return super.toString();
        /*StringBuilder sb = new StringBuilder();
        sb.append("OBJECT\n");
        sb.append(sceneRef.identity().name).append("\n");
        sb.append(sceneRef.identity().tag).append("\n");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject(sceneRef);
            sb.append(Arrays.toString(baos.toByteArray())).append("\n");
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            for (ScriptObject s : scripts) {
                sb.append("SCRIPT\n");
                sb.append(s.scriptRef.getClass().getName()).append("\n");
                // serialize the script and append it to the string
                oos.writeObject(s.scriptRef);
                sb.append(Arrays.toString(baos.toByteArray())).append("\n");
                oos.flush();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();*/
    }
}
