package org.jvim.application.SearchEngine;

import org.jvim.domain.Cursor.Cursor;
import org.jvim.domain.FileHandler.FileHandler;
import org.jvim.domain.Keyboard.Keyboard;
import org.jvim.domain.SearchEngine.SearchDirection;
import org.jvim.domain.SearchEngine.SearchEngine;

import java.util.function.BiConsumer;

public class SearchText {
    /**
     * this is equivalent to editorFind in legacy
     */
    public static void search() {
        prompt("Search: %s (Use ESC/Arrows/Enter)", (query, key) -> {
            if (query == null || query.isBlank()) {
                SearchEngine.lastMatch = -1;
                SearchEngine.searchDirection = SearchDirection.FORWARDS;
                return;
            }
            if (key == Keyboard.ARROW_RIGHT || key == Keyboard.ARROW_DOWN) {
                SearchEngine.searchDirection = SearchDirection.FORWARDS;
            } else if (key == Keyboard.ARROW_LEFT || key == Keyboard.ARROW_UP) {
                SearchEngine.searchDirection = SearchDirection.BACKWARDS;
            } else {
                SearchEngine.lastMatch = -1;
                SearchEngine.searchDirection = SearchDirection.FORWARDS;
            }


            if (SearchEngine.lastMatch == -1) SearchEngine.searchDirection = SearchDirection.FORWARDS;
            int current = SearchEngine.lastMatch;

            for (int i = 0; i < FileHandler.content.size(); i++) {
                current += SearchEngine.searchDirection == SearchDirection.FORWARDS ? 1 : -1;
                if (current == -1) {
                    current = FileHandler.content.size() - 1;
                } else if (current == FileHandler.content.size()) {
                    current = 0;
                }

                String line = FileHandler.content.get(current);

                int match = line.indexOf(query);

                // TODO: review this
                if (match != -1) {
                    SearchEngine.lastMatch = current;
                    Cursor.cursorY = current;
                    Cursor.cursorX = match;
                    Cursor.offsetY = FileHandler.content.size();
                    return;
                }
            }
        });
    }

    /**
     * Review if this block can be extracted in other layer
     * @param initialMessage
     * @param callback
     */
    private static void prompt(String initialMessage, BiConsumer<String, Integer> callback) {
    }

}
