package org.JE.JE2Editor.EditorUI;

import org.JE.JE2.UI.UIElements.Style.Color;
import org.JE.JE2.UI.UIElements.Style.StyleInfo;

public record Styles() {
    public static final StyleInfo buttonStyle = new StyleInfo()
            .setHoverColor(Color.createColorHex("#737272"))
            .setPressedColor(Color.createColorHex("#212121"))
            .setInactiveColor(Color.createColorHex("#191A1D"))
            .setNormalColor(Color.createColorHex("#424242"));
}
