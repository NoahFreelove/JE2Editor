package org.JE.EditorUI;

import JE.UI.UIElements.Label;
import JE.UI.UIElements.Style.Color;
import JE.UI.UIElements.UIElement;

public class MultiLine extends UIElement {
    private String[] lines;
    private Label[] labels;

    public MultiLine(String[] lines, int alignment, Color c) {
        set(lines,alignment, c);
    }

    @Override
    protected void render() {
        for (Label l :
                labels) {
            l.requestRender();
        }
    }

    public void set(String[] lines, int alignment, Color color){
        this.lines = lines;
        this.labels = new Label[lines.length];
        int i = 0;
        for (String str :
                lines) {
            labels[i] = new Label(lines[i], alignment);
            labels[i].textColor = color;
            i++;
        }
    }
}
