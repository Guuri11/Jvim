package org.jvim.application.FileHandler;

import org.jvim.domain.FileHandler.FileHandler;

import static org.jvim.domain.Cursor.Cursor.cursorX;
import static org.jvim.domain.Cursor.Cursor.cursorY;

public class InsertChar {
    public static void insert(int c) {
        if (cursorY == FileHandler.content.size()) {
            // append row
            insertRowAt(FileHandler.content.size(), "");
        }
        insertCharIntoRow(cursorY, cursorX, c);
        cursorX++;
    }

    private static void insertRowAt(int at, String rowContent) {
        if (at < 0 || at > FileHandler.content.size()) return;

        FileHandler.content.add(at, rowContent);
    }

    private static void insertCharIntoRow(int row, int at, int c) {
        if (at < 0 || at > FileHandler.content.get(row).length()) at = FileHandler.content.get(row).length();
        String editedLine = new StringBuilder(FileHandler.content.get(row)).insert(at, (char) c).toString();
        FileHandler.content.set(row, editedLine);
    }

}
