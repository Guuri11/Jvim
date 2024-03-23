package org.jvim.application.Keyboard;

import org.jvim.domain.Keyboard.Keyboard;

import java.io.IOException;

public class ReadKey {
    public static int read() throws IOException {
        int key = System.in.read();
        if (key != '\033') {
            return key;
        }

        int nextKey = System.in.read();
        if (nextKey != '[' && nextKey != 'O') {
            return nextKey;
        }

        int yetAnotherKey = System.in.read();

        return handleSpecialKey(nextKey, yetAnotherKey);
    }

    private static int handleSpecialKey(int nextKey, int yetAnotherKey) throws IOException {
        if (nextKey == '[') {
            return switch (yetAnotherKey) {
                case 'A' -> Keyboard.ARROW_UP;  // e.g. esc[A == arrow_up
                case 'B' -> Keyboard.ARROW_DOWN;
                case 'C' -> Keyboard.ARROW_RIGHT;
                case 'D' -> Keyboard.ARROW_LEFT;
                case 'H' -> Keyboard.HOME;
                case 'F' -> Keyboard.END;
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {  // e.g: esc[5~ == page_up
                    int yetYetAnotherChar = System.in.read();
                    if (yetYetAnotherChar != '~') {
                        yield yetYetAnotherChar;
                    }
                    switch (yetAnotherKey) {
                        case '1':
                        case '7':
                            yield Keyboard.HOME;
                        case '3':
                            yield Keyboard.DEL;
                        case '4':
                        case '8':
                            yield Keyboard.END;
                        case '5':
                            yield Keyboard.PAGE_UP;
                        case '6':
                            yield Keyboard.PAGE_DOWN;
                        default:
                            yield yetAnotherKey;
                    }
                }
                default -> yetAnotherKey;
            };
        } else { //if (nextKey == 'O') {  e.g. escpOH == HOME
            return switch (yetAnotherKey) {
                case 'H' -> Keyboard.HOME;
                case 'F' -> Keyboard.END;
                default -> yetAnotherKey;
            };
        }
    }
}
