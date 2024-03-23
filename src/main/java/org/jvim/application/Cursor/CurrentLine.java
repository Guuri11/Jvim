package org.jvim.application.Cursor;

import org.jvim.domain.FileHandler.FileHandler;

import static org.jvim.domain.Cursor.Cursor.cursorY;

public class CurrentLine {
    public static String get() {
        return cursorY < FileHandler.content.size() ? FileHandler.content.get(cursorY) : null;
    }
}
