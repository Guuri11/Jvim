package org.jvim.domain.FileHandler;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static Path currentFile;
    public static boolean renamingTmpFile = false;
    public static String fileNameForTmpFile = "";
    public static List<String> content = new ArrayList<>();
}
