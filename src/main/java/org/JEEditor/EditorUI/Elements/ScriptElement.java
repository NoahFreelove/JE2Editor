package org.JEEditor.EditorUI.Elements;

import JE.Annotations.HideFromInspector;
import JE.Objects.GameObject;
import JE.Objects.Scripts.Base.Script;
import JE.UI.UIElements.UIElement;
import JE.Window.UIHandler;
import org.JEEditor.EditorUI.StringFormatter;
import org.JEEditor.ScriptElement.FieldType;
import org.JEEditor.ScriptElement.RemoveScriptButton;
import org.JEEditor.ScriptElement.UIPairs.*;
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
    private NkColor color = NkColor.create();
    private ArrayList<Field> fields;
    private ArrayList<FieldUIPair> visibleFields;
    private RemoveScriptButton rsb;
    private String scriptTitle = "";

    public ScriptElement(Script comp){
        this.ref = comp;
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
        Nuklear.nk_label(UIHandler.nuklearContext, scriptTitle, Nuklear.NK_TEXT_CENTERED);
        // for each public field in the component, display it with a label. Don't include those with the @HideInInspector annotation
        for (FieldUIPair f : visibleFields) {

            try {
                f.requestRender();
                Nuklear.nk_spacer(UIHandler.nuklearContext);

            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        if(ref.getRestrictions().canBeRemoved)
        {
            rsb.requestRender();
        }
        nk_spacer(UIHandler.nuklearContext);
    }

    public static FieldUIPair getType(Field field, Script ref){
        Object fieldValue = null;

        try {
            fieldValue = field.get(ref);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(fieldValue instanceof Vector3f vec3){ return new Vec3Field(field, vec3, ref, field.getName()); }
        else if(fieldValue instanceof Vector2f vec2){ return new Vec2Field(field, vec2, ref, field.getName()); }
        else if(fieldValue instanceof String str){ return new StringField(field, str, ref, field.getName()); }
        else if(fieldValue instanceof Integer i){ return new IntField(field, field.getName(), ref,-10000,i,10000); }
        //else if(fieldValue instanceof Integer[] i){ return new ArrayWrapperField(field, FieldType.INT, i); }

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
