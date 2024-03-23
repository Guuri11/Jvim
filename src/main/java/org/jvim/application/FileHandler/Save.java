package org.jvim.application.FileHandler;

import org.jvim.application.Editor.EditorService;
import org.jvim.domain.FileHandler.FileHandler;

import java.io.IOException;
import java.nio.file.Files;

public class Save {
    public static void editorSave() {
        if (FileHandler.currentFile == null) {
            return;
        }


        if (FileHandler.currentFile.toString().endsWith("tmp")) {
            EditorService.setStatusMessage("File name: " + FileHandler.currentFile.getFileName());
            return;
        }

        try {
            Files.write(FileHandler.currentFile, FileHandler.content);
            EditorService.setStatusMessage("Successfully saved file");
        } catch (IOException e) {
            e.printStackTrace();
            EditorService.setStatusMessage("There was an error saving your file %s".formatted(e.getMessage()));
        }
    }
}
