package org.jvim.domain.Editor;

import org.jvim.domain.Terminal.UnixTerminal;

public class Editor {
    public static int rows = 10;
    public static int columns = 10;
    public static final Terminal terminal = new UnixTerminal();

    public static String statusMessage;
}
