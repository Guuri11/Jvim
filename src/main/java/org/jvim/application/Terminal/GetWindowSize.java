package org.jvim.application.Terminal;

import org.jvim.domain.Native.LibC;
import org.jvim.domain.Native.WindowsSize;
import org.jvim.domain.Terminal.WindowSize;

public class GetWindowSize {
    public static WindowSize get() {
        final WindowsSize winsize = new WindowsSize();

        final int rc = LibC.INSTANCE.ioctl(LibC.SYSTEM_OUT_FD, LibC.INSTANCE.TIOCGWINSZ, winsize);

        if (rc != 0) {
            System.err.println("ioctl failed with return code[={}]" + rc);
            System.exit(1);
        }
        return new WindowSize(winsize.ws_row, winsize.ws_col);
    }
}
