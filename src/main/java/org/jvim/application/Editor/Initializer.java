package org.jvim.application.Editor;

import org.jvim.application.Terminal.EnableRaw;
import org.jvim.application.Terminal.GetWindowSize;
import org.jvim.domain.Editor.Editor;
import org.jvim.domain.Terminal.WindowSize;

public class Initializer {

    public static void init() {
        EnableRaw.enable();
        WindowSize windowSize = GetWindowSize.get();
        Editor.columns = windowSize.columns();
        Editor.rows = windowSize.rows() - 1;
    }
}
