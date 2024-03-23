package org.jvim.application.Cursor;

import org.jvim.application.Cursor.MoveCursor.MoveCursor;
import org.jvim.application.Cursor.MoveCursor.Side;
import org.jvim.domain.Editor.Editor;
import org.jvim.domain.FileHandler.FileHandler;
import org.jvim.domain.Keyboard.Keyboard;

import static org.jvim.domain.Cursor.Cursor.cursorX;
import static org.jvim.domain.Cursor.Cursor.cursorY;

public class CursorAt {

    public static void position(int key) {
        String line = CurrentLine.get();
        switch (key) {
            case Keyboard.ARROW_UP -> {
                if (cursorY > 0) {
                    cursorY--;
                }
            }
            case Keyboard.ARROW_DOWN -> {
                if (cursorY < FileHandler.content.size()) {
                    cursorY++;
                }
            }
            case Keyboard.ARROW_LEFT -> {
                if (cursorX > 0) {
                    cursorX--;
                }
            }
            case Keyboard.ARROW_RIGHT -> {
                if (line != null && cursorX < line.length()) {
                    cursorX++;
                }
            }
            case Keyboard.PAGE_UP, Keyboard.PAGE_DOWN -> {

                if (key == Keyboard.PAGE_UP) {
                    MoveCursor.to(Side.TOP_OFF_SCREEN);
                }

                if (key == Keyboard.PAGE_DOWN) {
                    MoveCursor.to(Side.BOTTOM_OF_SCREEN);
                }

                for (int i = 0; i < Editor.rows; i++) {
                    position(key == Keyboard.PAGE_UP ? Keyboard.ARROW_UP : Keyboard.ARROW_DOWN);
                }

            }
            case Keyboard.HOME -> cursorX = 0;
            case Keyboard.END -> {
                if (line != null) {
                    cursorX = line.length();
                }
            }
        }

        String newLine = CurrentLine.get();
        if (newLine != null && cursorX > newLine.length()) {
            cursorX = newLine.length();
        }
    }
}
