package org.sg.console;

import org.sg.OutputConsole;

public class Console implements OutputConsole {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
