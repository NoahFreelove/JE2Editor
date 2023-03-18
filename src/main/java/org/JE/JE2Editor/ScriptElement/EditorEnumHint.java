package org.JE.JE2Editor.ScriptElement;

import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.UI.UIElements.UIElement;
import org.JE.JE2Editor.EditorUI.MultiLine;

import static org.lwjgl.nuklear.Nuklear.NK_TEXT_ALIGN_LEFT;

public class EditorEnumHint extends UIElement {
    public MultiLine ml;

    public EditorEnumHint(String[] titles) {
        super();
        /*StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String s : titles) {
            sb.append(i).append(" : ").append(s).append(" | ");
            i++;
        }
        // remove last 2 characters
        sb.delete(sb.length() - 2, sb.length());
        title = sb.toString();*/
        for (int i = 0; i < titles.length; i++) {
            titles[i] = i + " : " + titles[i];
        }
        ml = new MultiLine(titles, NK_TEXT_ALIGN_LEFT, Color.WHITE);

    }
    @Override
    protected void render() {
        //nk_label_colored(UIHandler.nuklearContext,title, Nuklear.NK_TEXT_ALIGN_LEFT, Color.WHITE.nkColor());
        ml.requestRender();
    }
}
