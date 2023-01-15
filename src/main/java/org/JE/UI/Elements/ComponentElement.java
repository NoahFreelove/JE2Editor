package org.JE.UI.Elements;

import JE.Objects.Components.Base.Component;
import JE.UI.UIElements.UIElement;
import JE.Window.UIHandler;
import org.lwjgl.nuklear.Nuklear;

public class ComponentElement extends UIElement {
    private final Component ref;
    public ComponentElement (Component comp){
        this.ref = comp;
    }
    @Override
    protected void render() {
        Nuklear.nk_label(UIHandler.ctx, ref.getClass().getSimpleName(), Nuklear.NK_TEXT_ALIGN_LEFT);
    }
}
