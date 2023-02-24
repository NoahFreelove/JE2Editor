package org.JE.EditorUI.Elements;

import JE.Annotations.HideFromInspector;
import JE.Objects.GameObject;
import JE.Objects.Scripts.Base.Script;
import JE.UI.UIElements.UIElement;
import JE.Window.UIHandler;
import org.JE.ScriptElement.FieldType;
import org.JE.ScriptElement.UIPairs.BoolField;
import org.JE.ScriptElement.UIPairs.FieldUIPair;
import org.JE.ScriptElement.UIPairs.FloatField;
import org.JE.ScriptElement.UIPairs.Vec3Field;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.nuklear.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.nuklear.Nuklear.*;

public class ScriptElement extends UIElement {
    private final Script ref;
    private NkRect rect = NkRect.create();
    private NkColor color = NkColor.create();
    private ArrayList<Field> fields;
    private ArrayList<FieldUIPair> visibleFields;

    public ScriptElement(Script comp){
        this.ref = comp;
        Nuklear.nk_rgb(50,50,50,color);

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
            visibleFields.add(getType(f));

        }
    }
    @Override
    protected void render() {
        Nuklear.nk_label(UIHandler.nuklearContext, ref.getClass().getSimpleName(), Nuklear.NK_TEXT_CENTERED);
        // for each public field in the component, display it with a label. Don't include those with the @HideInInspector annotation
        for (FieldUIPair f : visibleFields) {

            try {

                Nuklear.nk_label(UIHandler.nuklearContext, f.field.getName() + ": " + f.field.get(ref).toString(), Nuklear.NK_TEXT_ALIGN_LEFT);
                f.requestRender();
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        nk_spacer(UIHandler.nuklearContext);
    }

    private FieldUIPair getType(Field field){
        Object fieldValue = null;

        try {
            fieldValue = field.get(ref);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(fieldValue instanceof Vector3f vec3){
            return new Vec3Field(field, vec3, fieldValue);
        }
        else if(fieldValue instanceof Vector2f vec2){
            return new FieldUIPair(field, FieldType.VEC2, vec2);
        }
        else if(fieldValue instanceof String str){
            return new FieldUIPair(field, FieldType.STRING, str);
        }

        else if(fieldValue instanceof Integer i){ return new FieldUIPair(field, FieldType.INT, fieldValue); }
        else if(fieldValue instanceof Float f){ return new FloatField(field,fieldValue, field.getName(), Float.MIN_VALUE, 0, Float.MAX_VALUE, 1, 1);}
        else if(fieldValue instanceof Double d){return new FieldUIPair(field, FieldType.DOUBLE, fieldValue);}
        else if(fieldValue instanceof Boolean b){ return new BoolField(field, b, fieldValue);}
        else if(fieldValue instanceof Long l){ return new FieldUIPair(field, FieldType.LONG, fieldValue);}
        else if(fieldValue instanceof Byte b){ return new FieldUIPair(field, FieldType.BYTE, fieldValue);}
        else if(fieldValue instanceof GameObject go){ return new FieldUIPair(field, FieldType.GAMEOBJECT, fieldValue);}
        else if(fieldValue instanceof Script script){ return new FieldUIPair(field, FieldType.SCRIPT, fieldValue);}
        return new FieldUIPair(field, FieldType.OBJECT, fieldValue);
    }
}
