package org.JE.JE2Editor.ScriptElement.UIPairs;

import org.JE.JE2.UI.UIElements.Label;
import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.Window.UIHandler;
import org.JE.JE2Editor.EditorUI.StringFormatter;
import org.JE.JE2Editor.ScriptElement.FieldType;

import java.lang.reflect.Field;

import static org.lwjgl.nuklear.Nuklear.NK_TEXT_ALIGN_LEFT;
import static org.lwjgl.nuklear.Nuklear.nk_property_float;

public class ColorField extends FieldUIPair {
    private final float[] r = new float[1];
    private final float[] g = new float[1];
    private final float[] b = new float[1];
    private final float[] a = new float[1];

    private final Color colorRef;
    Label titleText;

    public ColorField(Field field, Object ref, Color init, String title) {
        super(field, FieldType.COLOR, ref);
        this.colorRef = init;
        r[0] = init.r();
        g[0] = init.g();
        b[0] = init.b();
        a[0] = init.a();
        titleText = new Label(StringFormatter.capSplit(title), NK_TEXT_ALIGN_LEFT);
    }

    @Override
    protected void render(){
        titleText.textColor = colorRef;
        titleText.requestRender();

        nk_property_float(UIHandler.nuklearContext, "Red", 0, r, 1, 0.05f, 0.01f);
        nk_property_float(UIHandler.nuklearContext, "Green", 0, g, 1, 0.05f, 0.01f);
        nk_property_float(UIHandler.nuklearContext, "Blue", 0, b, 1, 0.05f, 0.01f);
        nk_property_float(UIHandler.nuklearContext, "Alpha", 0, a, 1, 0.05f, 0.01f);

        colorRef.set(r[0], g[0], b[0], a[0]);
        try {
            field.set(ref, colorRef);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
