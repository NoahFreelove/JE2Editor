package org.JE.JE2Editor.EditorUI;

import org.JE.JE2.UI.UIElements.Label;
import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.UI.UIElements.Style.StyleInfo;
import org.JE.JE2.UI.UIElements.UIElement;

public class MultiLine extends UIElement {
    private Label[] labels;

    public MultiLine(String[] lines, int alignment, Color c) {
        set(lines,alignment, c);
    }

    @Override
    protected void render() {
        for (Label l : labels) {
            l.requestRender();
        }
    }

    public void set(String[] lines, int alignment, Color color){
        this.labels = new Label[lines.length];
        for (int i=0;i<lines.length;i++){
            labels[i] = new Label(lines[i], alignment);
            labels[i].setStyle(new StyleInfo().setTextColor(color));
        }
    }
}
