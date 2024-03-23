package org.jvim.application.FileHandler;

import org.jvim.application.Editor.EditorService;
import org.jvim.domain.FileHandler.FileHandler;

import static org.jvim.domain.Cursor.Cursor.cursorX;
import static org.jvim.domain.Cursor.Cursor.cursorY;

public class DeleteChar {
    public static void delete() {
        if (cursorY == FileHandler.content.size()) {
            return;
        }

        if (cursorX == 0 && cursorY == 0) {
            return;
        }
        if (cursorX > 0 ) {
            if (FileHandler.renamingTmpFile) {
                deleteCharFromTmpFile();
                return;
            }
            deleteCharFromRow(cursorY, cursorX - 1);
            cursorX--;
        } else {
            cursorX = FileHandler.content.get(cursorY - 1).length();
            appendStringToRow(cursorY - 1, FileHandler.content.get(cursorY));
            deleteRow(cursorY);
            cursorY--;
        }
    }

    private static void deleteCharFromTmpFile() {
        FileHandler.fileNameForTmpFile = removeLastChar(FileHandler.fileNameForTmpFile);
        EditorService.setStatusMessage("File name: " + FileHandler.fileNameForTmpFile);
    }

    private static String removeLastChar(String s) {
        return (s == null || s.isEmpty())
                ? null
                : (s.substring(0, s.length() - 1));
    }

    private static void appendStringToRow(int at
            , String append) {
        FileHandler.content.set(at, FileHandler.content.get(at) + append);
    }

    private static void deleteCharFromRow(int row, int at) {
        if (at < 0 || at > FileHandler.content.get(row).length()) return;
        String editedLine = new StringBuilder(FileHandler.content.get(row)).deleteCharAt(at).toString();
        FileHandler.content.set(row, editedLine);
    }

    private static void deleteRow(int at) {
        if (at < 0 || at >= FileHandler.content.size()) return;
        FileHandler.content.remove(at);
    }

}
