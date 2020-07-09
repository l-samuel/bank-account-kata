package org.sg;

import org.sg.DateTime;

import java.time.LocalDateTime;

public class Date implements DateTime {

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
