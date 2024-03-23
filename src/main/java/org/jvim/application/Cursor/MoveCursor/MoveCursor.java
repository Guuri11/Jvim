package org.jvim.application.Cursor.MoveCursor;

import org.jvim.domain.Editor.Editor;
import org.jvim.domain.FileHandler.FileHandler;

import static org.jvim.domain.Cursor.Cursor.offsetY;
import static org.jvim.domain.Cursor.Cursor.cursorY;

public class MoveCursor {
    // TODO: redo this to allow move it to any
    public static void to(Side side) {
        if (side.equals(Side.TOP_OFF_SCREEN)) {
            cursorY = offsetY;
        }
        if (side.equals(Side.BOTTOM_OF_SCREEN)) {
            cursorY = offsetY + Editor.rows - 1;
            if (cursorY > FileHandler.content.size()) cursorY = FileHandler.content.size();
        }
    }
}
