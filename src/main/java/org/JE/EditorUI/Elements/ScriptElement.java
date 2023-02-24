package org.JE.EditorUI.Elements;

import JE.Annotations.ForceShowInInspector;
import JE.Annotations.HideFromInspector;
import JE.Objects.Scripts.Base.Script;
import JE.UI.UIElements.UIElement;
import JE.Window.UIHandler;
import org.lwjgl.nuklear.NkColor;
import org.lwjgl.nuklear.NkRect;
import org.lwjgl.nuklear.Nuklear;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.lwjgl.nuklear.Nuklear.nk_spacer;

public class ScriptElement extends UIElement {
    private final Script ref;
    private NkRect rect = NkRect.create();
    private NkColor color = NkColor.create();
    public ScriptElement(Script comp){
        this.ref = comp;
        Nuklear.nk_rgb(50,50,50,color);
    }
    @Override
    protected void render() {
        Nuklear.nk_label(UIHandler.nuklearContext, ref.getClass().getSimpleName(), Nuklear.NK_TEXT_CENTERED);
        // for each public field in the component, display it with a label. Don't include those with the @HideInInspector annotation
        for (Field f :
                ref.getClass().getDeclaredFields()) {


            if(f.getModifiers() == (f.getModifiers() | Modifier.PRIVATE))
            {
                f.setAccessible(true);
            }

            // If field is transient, skip it. Need to use bit manipulation to check if the transient bit is set
            if(f.getModifiers() == (f.getModifiers() | Modifier.TRANSIENT))
                continue;

            if(f.isAnnotationPresent(HideFromInspector.class))
                continue;

            try {

                Nuklear.nk_label(UIHandler.nuklearContext, f.getName() + ": " + f.get(ref).toString(), Nuklear.NK_TEXT_ALIGN_LEFT);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        nk_spacer(UIHandler.nuklearContext);
    }
}
