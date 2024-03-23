package org.jvim.application.Terminal;

import org.jvim.domain.Native.LibC;
import org.jvim.domain.Native.Termios;
import org.jvim.domain.Terminal.UnixTerminal;

public class EnableRaw {
    public static void enable() {
        Termios termios = new Termios();
        int rc = LibC.INSTANCE.tcgetattr(LibC.SYSTEM_OUT_FD, termios);

        if (rc != 0) {
            System.err.println("There was a problem calling tcgetattr");
            System.exit(rc);
        }

        UnixTerminal.originalAttributes = Termios.of(termios);

        termios.c_lflag &= ~(LibC.ECHO | LibC.ICANON | LibC.IEXTEN | LibC.ISIG);
        termios.c_iflag &= ~(LibC.IXON | LibC.ICRNL);
        termios.c_oflag &= ~(LibC.OPOST);

        LibC.INSTANCE.tcsetattr(LibC.SYSTEM_OUT_FD, LibC.TCSAFLUSH, termios);
    }
}
