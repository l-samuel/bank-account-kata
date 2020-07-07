package org.sg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

class PrinterTest {


    private OutputConsole console ;
    private Printer printer ;

    @BeforeEach
    void setUp() {
        console = Mockito.mock(OutputConsole.class);
        printer = new Printer(console);
    }

    @Test
    void should_print_header_when_no_transaction() {
        //given
        //when
        printer.printStatements();
        //then
        BDDMockito.then(console).should(times(1)).print("DATE | AMOUNT | BALANCE");
    }
}