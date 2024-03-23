package org.jvim.infrastructure;

import org.jvim.application.Editor.Initializer;
import org.jvim.application.FileHandler.OpenFile;
import org.jvim.application.Keyboard.ReadKey;

import java.io.IOException;

import static org.jvim.application.Editor.EditorService.handleKey;
import static org.jvim.application.Editor.EditorService.refreshScreen;

public class Application {

    public static void main(String[] args) {
        runJVim(args);
    }

    private static void runJVim(String[] args) {
        try {
            OpenFile.open(args);
            Initializer.init();

            while (true) {
                refreshScreen();
                int key = ReadKey.read();
                handleKey(key);
            }
        } catch (IOException e) {
            System.err.println("Error while opening file: " + e.getMessage());
        }
    }
}
