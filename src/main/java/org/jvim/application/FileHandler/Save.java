package org.jvim.application.FileHandler;

import org.jvim.application.Editor.EditorService;
import org.jvim.domain.FileHandler.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Save {
    public static void editorSave() {
        if (FileHandler.currentFile == null) {
            return;
        }

        if (FileHandler.renamingTmpFile) {
            saveTmpFile();
            return;
        }

        if (FileHandler.currentFile.toString().endsWith("tmp")) {
            FileHandler.renamingTmpFile = true;
            EditorService.setStatusMessage("File name: " + FileHandler.fileNameForTmpFile);
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

    private static void saveTmpFile() {
        try {
            Files.write(FileHandler.currentFile, FileHandler.content);
            Files.move(FileHandler.currentFile, FileHandler.currentFile.resolveSibling(
                    Path.of(System.getProperty("user.dir") + "/" + FileHandler.fileNameForTmpFile)));
            EditorService.setStatusMessage("Successfully saved file");
            FileHandler.currentFile = Path.of(System.getProperty("user.dir") + "/" + FileHandler.fileNameForTmpFile);
            FileHandler.renamingTmpFile = false;
        } catch (IOException e) {
            e.printStackTrace();
            EditorService.setStatusMessage("There was an error saving your file %s".formatted(e.getMessage()));
        }
    }
}
