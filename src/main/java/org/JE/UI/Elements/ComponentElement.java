package org.JE.UI.Elements;

import JE.Annotations.ForceShowInInspector;
import JE.Annotations.HideFromInspector;
import JE.Objects.Components.Base.Component;
import JE.UI.UIElements.UIElement;
import JE.Window.UIHandler;
import org.lwjgl.nuklear.NkColor;
import org.lwjgl.nuklear.NkRect;
import org.lwjgl.nuklear.Nuklear;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.lwjgl.nuklear.Nuklear.nk_spacer;

public class ComponentElement extends UIElement {
    private final Component ref;
    private NkRect rect = NkRect.create();
    private NkColor color = NkColor.create();
    public ComponentElement (Component comp){
        this.ref = comp;
        Nuklear.nk_rgb(50,50,50,color);
    }
    @Override
    protected void render() {
        Nuklear.nk_label(UIHandler.ctx, ref.getClass().getSimpleName(), Nuklear.NK_TEXT_CENTERED);
        // for each public field in the component, display it with a label. Don't include those with the @HideInInspector annotation
        for (Field f :
                ref.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            System.out.println(Arrays.toString(f.getDeclaredAnnotations()));
            if(f.isAnnotationPresent(HideFromInspector.class)){
                continue;
            }
            //System.out.println(Arrays.toString(f.getAnnotations()));
            // skip if private
            /*if((f.getModifiers() & 2) == 2 && !f.isAnnotationPresent(ForceShowInInspector.class)){
                continue;
            }*/
            try {
                Nuklear.nk_label(UIHandler.ctx, f.getName() + ": " + f.get(ref), Nuklear.NK_TEXT_ALIGN_LEFT);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        nk_spacer(UIHandler.ctx);
    }
}
