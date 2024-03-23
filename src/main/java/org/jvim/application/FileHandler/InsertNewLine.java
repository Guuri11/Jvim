package org.jvim.application.FileHandler;

import org.jvim.domain.FileHandler.FileHandler;

import static org.jvim.domain.Cursor.Cursor.cursorX;
import static org.jvim.domain.Cursor.Cursor.cursorY;

public class InsertNewLine {
    public static void insert() {
        if (cursorX == 0) {
            insertRowAt(cursorY, "");
        } else {
            insertRowAt(cursorY + 1, FileHandler.content.get(cursorY).substring(cursorX));
            FileHandler.content.set(cursorY, FileHandler.content.get(cursorY).substring(0, cursorX));
        }
        cursorY++;
        cursorX = 0;
    }

    // TODO: this is duplicated at InsertChart.java
    private static void insertRowAt(int at, String rowContent) {
        if (at < 0 || at > FileHandler.content.size()) return;

        FileHandler.content.add(at, rowContent);
    }
}
