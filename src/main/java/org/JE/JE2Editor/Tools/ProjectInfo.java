package org.JE.JE2Editor.Tools;

public class ProjectInfo {
    public static String sourceDirectory = "";
    public static String activeScenePath = "scene.JEScene";

    public static String sceneDir = "";
    public static String runPath = "";
    public static String runPackage = "";
    public static String outPath = "";
    public static String javaC = "javac";
    public static String je2Jar = "";
    public static String jarName = "Project.jar";
    public static String jarC = "";
    public static String je2ScriptJar = "";
    public static String je2CompilerJar = "";
    public static String java = "java";
    public static String[] semicolonSeparatedDependencies = new String[0];

    public static void loadProjectInfo(String[] projectFile){
        StringBuilder fileData = new StringBuilder(1000);
        for (String line : projectFile) {
            fileData.append(line).append("\n");
        }

        String[] lines = fileData.toString().split("\n");
        for (String line : lines) {
            if(line.startsWith("src:")){
                ProjectInfo.sourceDirectory = line.replace("src:", "").trim();
            }
            else if(line.startsWith("sceneDir:")){
                ProjectInfo.sceneDir = line.replace("sceneDir:", "").trim();
            }
            else if(line.startsWith("runPath:")){
                ProjectInfo.runPath = line.replace("runPath:", "").trim();
            }
            else if(line.startsWith("runPackage:")){
                ProjectInfo.runPackage = line.replace("runPackage:", "").trim();
            }
            else if(line.startsWith("outPath:")){
                ProjectInfo.outPath = line.replace("outPath:", "").trim();
            }
            else if(line.startsWith("javaC:")){
                ProjectInfo.javaC = line.replace("javaC:", "").trim();
            }
            else if(line.startsWith("je2Jar:")){
                ProjectInfo.je2Jar = line.replace("je2Jar:", "").trim();
            }
            else if(line.startsWith("jarName:")){
                ProjectInfo.jarName = line.replace("jarName:", "").trim();
            }
            else if(line.startsWith("jarC:")){
                ProjectInfo.jarC = line.replace("jarC:", "").trim();
            }
            else if(line.startsWith("je2ScriptJar:")){
                ProjectInfo.je2ScriptJar = line.replace("je2ScriptJar:", "").trim();
            }
            else if(line.startsWith("je2CompilerJar:")){
                ProjectInfo.je2CompilerJar = line.replace("je2CompilerJar:", "").trim();
            }
            else if(line.startsWith("java:")){
                ProjectInfo.java = line.replace("java:", "").trim();
            }
            else if(line.startsWith("semicolonSeparatedDependencies:")){
                ProjectInfo.semicolonSeparatedDependencies = line.replace("semicolonSeparatedDependencies:", "").trim().split(";");
            }
            else if(line.startsWith("defaultScene:")){
                ProjectInfo.activeScenePath = sceneDir + "\\" + line.replace("defaultScene:", "").trim();
            }
        }
    }
}
