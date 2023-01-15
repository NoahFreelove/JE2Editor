package org.JE.UI;

import JE.UI.UIElements.Style.Color;
import JE.UI.UIElements.Style.StyleInfo;

public record Styles() {
    public static final StyleInfo buttonStyle = new StyleInfo()
            .setHoverColor(Color.createColorHex("#CBCFE4"))
            .setPressedColor(Color.createColorHex("#272A3A"))
            .setInactiveColor(Color.createColorHex("#191A1D"))
            .setNormalColor(Color.createColorHex("#7E87AC"));
}
