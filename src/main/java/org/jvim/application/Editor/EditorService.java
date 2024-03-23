package org.jvim.application.Editor;

import org.jvim.application.Cursor.CursorAt;
import org.jvim.application.FileHandler.DeleteChar;
import org.jvim.application.FileHandler.InsertChar;
import org.jvim.application.FileHandler.InsertNewLine;
import org.jvim.application.FileHandler.Save;
import org.jvim.application.SearchEngine.SearchText;
import org.jvim.application.Terminal.DisableRaw;
import org.jvim.domain.Editor.Editor;
import org.jvim.domain.FileHandler.FileHandler;
import org.jvim.domain.Keyboard.Keyboard;

import java.util.List;

import static org.jvim.domain.Cursor.Cursor.*;
import static org.jvim.domain.Keyboard.Keyboard.ctrl_key;

public class EditorService {
    private static void exit() {
        System.out.print(Keyboard.ESCAPE_ASCII_CODE + "[2J");
        System.out.print(Keyboard.ESCAPE_ASCII_CODE + "[H");
        DisableRaw.disable();
        System.exit(0);
    }

    public static void refreshScreen() {
        scroll();
        StringBuilder builder = new StringBuilder();

        drawCursorInTopLeft(builder);
        drawContent(builder);
        drawStatusBar(builder);
        drawCursor(builder);
        System.out.print(builder);
    }

    public static void setStatusMessage(String message) {
        Editor.statusMessage = message;
    }

    public static void clearStatusMessage() {
        Editor.statusMessage = null;
    }

    private static void drawCursor(StringBuilder builder) {
        builder.append(String.format(Keyboard.ESCAPE_ASCII_CODE + "[%d;%dH", cursorY - offsetY + 1, cursorX - offsetX + 1));
    }

    public static void handleKey(int key) {
        if (key == ctrl_key('q')) {
            exit();
        } else if (key == '\r') {
            InsertNewLine.insert();
        }
        else if (key == ctrl_key('f')) {
            SearchText.search();
        } else if (key == ctrl_key('s')) {
            Save.editorSave();
        } else if (List.of(Keyboard.BACKSPACE, ctrl_key('h'), Keyboard.DEL).contains(key)) {
            DeleteChar.delete();
        } else if (List.of(Keyboard.ARROW_UP, Keyboard.ARROW_DOWN, Keyboard.ARROW_LEFT, Keyboard.ARROW_RIGHT,
                Keyboard.HOME, Keyboard.END, Keyboard.PAGE_UP, Keyboard.PAGE_DOWN).contains(key)) {
            CursorAt.position(key);
        } else {
            InsertChar.insert(key);
        }
    }

    private static void drawContent(StringBuilder builder) {
        for (int i = 0; i < Editor.rows; i++) {
            int fileI = offsetY + i;
            if (fileI >= FileHandler.content.size()) {
                builder.append("~");
            } else {
                String line = FileHandler.content.get(fileI);
                int lengthToDraw = line.length() - offsetX;

                if (lengthToDraw < 0) {
                    lengthToDraw = 0;
                }
                if (lengthToDraw > Editor.columns) {
                    lengthToDraw = Editor.columns;
                }

                if (lengthToDraw > 0) {
                    builder.append(line, offsetX, offsetX + lengthToDraw);
                }

            }
            builder.append(Keyboard.ESCAPE_ASCII_CODE + "[K\r\n");
        }
    }

    private static void drawStatusBar(StringBuilder builder) {
        String toDraw = Editor.statusMessage != null ? Editor.statusMessage : ("File: " + FileHandler.currentFile.getFileName() + " Rows: " + Editor.rows + " X:" + cursorX + " Y: " + cursorY);

        builder.append(Keyboard.ESCAPE_ASCII_CODE + "[7m")
                .append(toDraw)
                .append(" ".repeat(Math.max(0, Editor.columns - toDraw.length())))
                .append(Keyboard.ESCAPE_ASCII_CODE + "[0m");
    }

    public static void scroll() {
        if (cursorY >= Editor.rows + offsetY) {
            offsetY = cursorY - Editor.rows + 1;
        } else if (cursorY < offsetY) {
            offsetY = cursorY;
        }

        if (cursorX >= Editor.columns + offsetX) {
            offsetX = cursorX - Editor.columns + 1;
        } else if (cursorX < offsetX) {
            offsetX = cursorX;
        }
    }

    private static void drawCursorInTopLeft(StringBuilder builder) {
        builder.append(Keyboard.ESCAPE_ASCII_CODE + "[H");
    }
}
