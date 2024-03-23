package org.jvim.application.Terminal;

import org.jvim.domain.Native.LibC;
import org.jvim.domain.Terminal.UnixTerminal;

public class DisableRaw {
    public static void disable() {
        LibC.INSTANCE.tcsetattr(LibC.SYSTEM_OUT_FD, LibC.TCSAFLUSH, UnixTerminal.originalAttributes);
    }
}
