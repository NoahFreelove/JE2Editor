package org.JE.JE2Editor.EditorUI;

public class StringFormatter {

    public static String splitCaps(String in){
        // add a space between every capital letter
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);

            if(i == 0) {
                out.append(c);
                continue;
            }
            if(Character.isUpperCase(c)){
                out.append(" ");
            }
            out.append(c);
        }
        return out.toString();
    }

    public static String capitalize(String in){
        // capitalize the first letter of a string
        return in.substring(0,1).toUpperCase() + in.substring(1);
    }
    public static String capAndSplit(String in){
        // capitalize the first letter of a string and add a space between every capital letter
        return splitCaps(capitalize(in));
    }
}
