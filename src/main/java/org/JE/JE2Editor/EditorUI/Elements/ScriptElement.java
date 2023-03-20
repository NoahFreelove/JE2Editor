package org.JE.JE2Editor.EditorUI.Elements;

import org.JE.JE2.Annotations.EditorEnum;
import org.JE.JE2.Annotations.HideFromInspector;
import org.JE.JE2.Objects.GameObject;
import org.JE.JE2.Objects.Scripts.Base.Script;
import org.JE.JE2.Rendering.Renderers.Renderer;
import org.JE.JE2.Rendering.Renderers.SpriteRenderer;
import org.JE.JE2.Rendering.Texture;
import org.JE.JE2.Resources.ResourceLoader;
import org.JE.JE2.UI.UIElements.Buttons.Button;
import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.UI.UIElements.UIElement;
import org.JE.JE2.Window.UIHandler;
import org.JE.JE2Editor.EditorUI.StringFormatter;
import org.JE.JE2Editor.ScriptElement.EditorEnumHint;
import org.JE.JE2Editor.ScriptElement.FieldType;
import org.JE.JE2Editor.ScriptElement.RemoveScriptButton;
import org.JE.JE2Editor.ScriptElement.UIPairs.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.nuklear.NkColor;
import org.lwjgl.nuklear.Nuklear;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.nuklear.Nuklear.nk_label_colored;
import static org.lwjgl.nuklear.Nuklear.nk_spacer;

public class ScriptElement extends UIElement {
    private final Script ref;
    private NkColor color = NkColor.create();
    private ArrayList<Field> fields;
    private ArrayList<FieldUIPair> visibleFields;
    private RemoveScriptButton rsb;
    private String scriptTitle = "";
    private SceneObject sceneObject;
    public boolean isCollapsed = false;
    private Button toggleCollapsed;

    public ScriptElement(Script comp, SceneObject sceneObject){
        this.ref = comp;
        this.sceneObject = sceneObject;
        toggleCollapsed = new Button("+/-", () -> isCollapsed = !isCollapsed);

        Nuklear.nk_rgb(50,50,50,color);

        rsb = new RemoveScriptButton(comp);

        scriptTitle = StringFormatter.splitCaps(ref.getClass().getSimpleName());

        fields = new ArrayList<>();
        Class<?> c = comp.getClass();
        while(c != Object.class){
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
            c = c.getSuperclass();
        }
        visibleFields = new ArrayList<>();

        for (Field f : fields){
            if((f.getModifiers() == (f.getModifiers() | Modifier.PRIVATE)) || (f.getModifiers() == (f.getModifiers() | Modifier.PROTECTED))){
                f.setAccessible(true);
            }

            if(f.getModifiers() == (f.getModifiers() | Modifier.TRANSIENT))
                continue;

            if(f.isAnnotationPresent(HideFromInspector.class))
                continue;
            visibleFields.add(getType(f,ref));

        }
    }

    @Override
    protected void render() {
        nk_spacer(UIHandler.nuklearContext);
        nk_label_colored(UIHandler.nuklearContext,scriptTitle,Nuklear.NK_TEXT_ALIGN_LEFT, Color.WHITE.nkColor());
        toggleCollapsed.text = (isCollapsed ? "Show" : "Hide");
        toggleCollapsed.requestRender();

        if(!isCollapsed){
            for (FieldUIPair f : visibleFields) {

                try {
                    if(f.hint !=null)
                        f.hint.requestRender();
                    f.requestRender();
                    Nuklear.nk_spacer(UIHandler.nuklearContext);

                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }

        if(ref.getRestrictions().canBeRemoved)
        {
            rsb.requestRender();
        }
    }

    private FieldUIPair getType(Field field, Script ref){
        Object fieldValue = null;

        try {
            fieldValue = field.get(ref);

        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(fieldValue instanceof Vector3f vec3){ return new Vec3Field(field, vec3, ref, field.getName()); }
        else if(fieldValue instanceof Vector2f vec2){ return new Vec2Field(field, vec2, ref, field.getName()); }
        else if(fieldValue instanceof String str){
            StringField sf = new StringField(field, str, ref, field.getName());
            if(field.getName().equals("textureFilepath") && ref.getClass() == SpriteRenderer.class)
            {
                sf.tf.eventChanged = s -> {
                    if(ResourceLoader.get(s) != null){
                        File f = new File(ResourceLoader.get(s));
                        if(f.exists() && !f.isDirectory()){
                            sceneObject.sceneRef.getSpriteRenderer().setTexture(new Texture(ResourceLoader.getBytes(s)));
                        }
                    }
                };
            }
            else if(field.getName().equals("normalFilepath") && ref.getClass() == SpriteRenderer.class)
            {
                sf.tf.eventChanged = s -> {
                    if(ResourceLoader.get(s) != null){
                        File f = new File(ResourceLoader.get(s));
                        if(f.exists() && !f.isDirectory()){
                            sceneObject.sceneRef.getSpriteRenderer().setNormalTexture(new Texture(ResourceLoader.getBytes(s)));
                        }
                    }
                };
            }
            return sf;
        }
        else if(fieldValue instanceof Integer i){
            IntField intField = new IntField(field, field.getName(), ref,-10000,i,10000);
            if(field.isAnnotationPresent(EditorEnum.class)){
                if(field.getName().equals("defaultShaderIndex") && ref instanceof Renderer)
                {
                    intField = new IntField(field, field.getName(), ref,0,i,2);
                }
                if(field.getName().equals("drawMode") && ref instanceof Renderer)
                {
                    intField = new IntField(field, field.getName(), ref,0,i,9);
                    intField.onChanged = i1 -> sceneObject.sceneRef.getRenderer().setDrawMode(i1);
                }
                EditorEnum hint = field.getAnnotation(EditorEnum.class);
                intField.hint = new EditorEnumHint(hint.values());
            }

            return intField;
        }
        //else if(fieldValue instanceof Integer[] i){ return new ArrayWrapperField(field, FieldType.INT, i); }
        else if(fieldValue instanceof Color c){
            return new ColorField(field, ref,c, field.getName());
        }

        else if(fieldValue instanceof Float f){ return new FloatField(field,ref, field.getName(), -10000, f, 10000, 0.1f, 0.1f, true);}
        else if(fieldValue instanceof Double d){return new DoubleField(field,ref, field.getName(), -10000, d, 10000, 0.1f, 0.1f, true);}
        else if(fieldValue instanceof Boolean b){ return new BoolField(field, b, ref);}
        else if(fieldValue instanceof Long l){ return new FieldUIPair(field, FieldType.LONG, ref);}
        else if(fieldValue instanceof Byte b){ return new FieldUIPair(field, FieldType.BYTE, ref);}
        else if(fieldValue instanceof GameObject go){ return new FieldUIPair(field, FieldType.GAMEOBJECT, ref);}
        else if(fieldValue instanceof Script script){ return new FieldUIPair(field, FieldType.SCRIPT, ref);}
        return new FieldUIPair(field, FieldType.OBJECT, ref);
    }
}
