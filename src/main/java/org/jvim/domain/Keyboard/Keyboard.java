package org.jvim.domain.Keyboard;


public class Keyboard {
    public static final int
            ARROW_UP = 1000,
            ARROW_DOWN = 1001,
            ARROW_LEFT = 1002,
            ARROW_RIGHT = 1003,
            HOME = 1004,
            END = 1005,
            PAGE_UP = 1006,
            PAGE_DOWN = 1007,
            DEL = 1008,

    BACKSPACE = 127;
    public static final char ESCAPE_ASCII_CODE = '\033';
    public static int ctrl_key(int key) {
        return key & 0x1f;
    }
}
