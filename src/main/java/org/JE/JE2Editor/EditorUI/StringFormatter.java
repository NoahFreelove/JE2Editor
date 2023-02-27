package org.JE.JE2Editor.EditorUI;

public class StringFormatter {

    public static String splitCaps(String in){
        // add a space between every capital letter
        String out = "";
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);

            if(i == 0) {
                out += c;
                continue;
            }
            if(Character.isUpperCase(c)){
                out += " ";
            }
            out += c;
        }
        return out;
    }

    public static String capitalize(String in){
        // capitalize the first letter of a string
        return in.substring(0,1).toUpperCase() + in.substring(1);
    }
    public static String capSplit(String in){
        // capitalize the first letter of a string and add a space between every capital letter
        return splitCaps(capitalize(in));
    }
}
