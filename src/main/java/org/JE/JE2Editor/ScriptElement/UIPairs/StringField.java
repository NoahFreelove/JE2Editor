package org.JE.JE2Editor.ScriptElement.UIPairs;

import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.UI.UIElements.TextField;
import org.JE.JE2.Window.UIHandler;
import org.JE.JE2Editor.EditorUI.StringFormatter;
import org.JE.JE2Editor.ScriptElement.FieldType;
import org.lwjgl.nuklear.Nuklear;

import java.lang.reflect.Field;

import static org.lwjgl.nuklear.Nuklear.nk_label_colored;

public class StringField extends FieldUIPair{
    public TextField tf;
    private String title;

    public StringField(Field field, String defaultValue, Object ref, String title) {
        super(field, FieldType.STRING, ref);
        this.title = StringFormatter.capAndSplit(title);
        this.tf = new TextField(128);
        tf.setValue(defaultValue);
    }

    @Override
    protected void render() {
        nk_label_colored(UIHandler.nuklearContext,title,Nuklear.NK_TEXT_ALIGN_LEFT, Color.WHITE.nkColor());
        tf.requestRender();

        try {
            field.set(ref, tf.getValue());
        } catch (Exception e) {
            System.out.println("string field err: " + e.getMessage());
        }
    }
}
