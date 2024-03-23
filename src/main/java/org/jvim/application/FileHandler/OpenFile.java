package org.jvim.application.FileHandler;

import org.jvim.domain.FileHandler.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpenFile {
    public static void open(String[] args) {
        if (args.length == 0 || args[0].equals(".")) {
            createFile();
            return;
        }
        if (args.length == 1 ) {
            String filename = args[0];
            Path path = Path.of(filename);
            if (Files.exists(path)) {
                try (Stream<String> stream = Files.lines(path)) {
                    FileHandler.content = stream.collect(Collectors.toCollection(ArrayList::new));
                } catch (IOException e) {
                    e.printStackTrace();
                    // TODO: Handle exception
                }
                FileHandler.currentFile = path;
            } else {
                createFile(path);
            }
        }
    }

    private static void createFile(Path path) {
        try {
            FileHandler.currentFile = Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }

    private static void createFile() {
        try {
            FileHandler.currentFile = Files.createTempFile("file", null);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }
}
