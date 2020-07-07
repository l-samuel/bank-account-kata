package org.sg;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.sg.model.Transaction;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;

class PrinterTest {

    private OutputConsole console;
    private Printer printer;

    @BeforeEach
    void setUp() {
        console = Mockito.mock(OutputConsole.class);
        printer = new Printer(console);
    }

    @Test
    void should_print_header_when_no_transaction() {
        //given
        //when
        printer.printStatements(Lists.emptyList());
        //then
        BDDMockito.then(console).should(times(1)).print("DATE | AMOUNT | BALANCE");
    }

    @Test
    void should_print_all_transactions_with_header_one_transaction() {
        //given
        //when
        Transaction deposit = new Transaction(100, LocalDateTime.of(2020, 7, 7, 0, 0));
        printer.printStatements(Lists.list(deposit));
        //then
        BDDMockito.then(console).should(times(1)).print("DATE | AMOUNT | BALANCE");
        BDDMockito.then(console).should(times(1)).print("2020-07-07|1.00|1.00");
    }

    @Test
    void should_print_all_transactions_by_descending_order_several_transactions() {
        //given
        //when
        Transaction deposit = new Transaction(10000, LocalDateTime.of(2020, 7, 6, 0, 0));
        Transaction deposit2 = new Transaction(15000, LocalDateTime.of(2020, 7, 7, 0, 0));
        Transaction withdrawal = new Transaction(-5000, LocalDateTime.of(2020, 7, 7, 12, 0));
        printer.printStatements(Lists.list(deposit, deposit2, withdrawal));
        //then
        BDDMockito.then(console).should().print("DATE | AMOUNT | BALANCE");
        BDDMockito.then(console).should().print("2020-07-07|-50.00|200.00");
        BDDMockito.then(console).should().print("2020-07-07|150.00|250.00");
        BDDMockito.then(console).should().print("2020-07-06|100.00|100.00");
    }
}