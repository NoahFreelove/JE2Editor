package org.JEEditor.EditorUI;

import JE.UI.UIElements.Style.Color;
import JE.UI.UIElements.Style.StyleInfo;

public record Styles() {
    public static final StyleInfo buttonStyle = new StyleInfo()
            .setHoverColor(Color.createColorHex("#737272"))
            .setPressedColor(Color.createColorHex("#212121"))
            .setInactiveColor(Color.createColorHex("#191A1D"))
            .setNormalColor(Color.createColorHex("#424242"));
}
