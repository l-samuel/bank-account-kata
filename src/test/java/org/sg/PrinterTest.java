package org.sg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.util.Lists.emptyList;
import static org.assertj.core.util.Lists.list;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class PrinterTest {

    private Writer console;
    private Printer printer;
    private TransactionsFormatter transactionsFormatter;

    @BeforeEach
    void setUp() {
        transactionsFormatter = new TransactionsFormatter();
        console = Mockito.mock(Writer.class);
        printer = new Printer(console, transactionsFormatter);
    }

    @Test
    void should_print_header_when_no_transaction() {
        //given
        //when
        printer.printStatements(emptyList());
        //then
        then(console).should(times(1)).print("DATE | AMOUNT | BALANCE");
    }

    @Test
    void should_print_all_transactions_with_header_one_transaction() {
        //given
        Transaction deposit = new Transaction(new Amount(BigDecimal.valueOf(100,2)), LocalDateTime.of(2020, 7, 7, 0, 0));
        //when
        printer.printStatements(list(deposit));
        //then
        then(console).should(times(1)).print("DATE | AMOUNT | BALANCE");
        then(console).should(times(1)).print("2020-07-07|1.00|1.00");
    }

    @Test
    void should_print_all_transactions_with_header_by_descending_order_several_transactions() {
        //given
        Transaction deposit = new Transaction(new Amount(BigDecimal.valueOf(10000,2)), LocalDateTime.of(2020, 7, 6, 0, 0));
        Transaction deposit2 = new Transaction(new Amount(BigDecimal.valueOf(15000,2)), LocalDateTime.of(2020, 7, 7, 0, 0));
        Transaction withdrawal = new Transaction(new Amount(BigDecimal.valueOf(-5000,2)), LocalDateTime.of(2020, 7, 7, 12, 0));
        //when
        printer.printStatements(list(deposit, deposit2, withdrawal));
        //then
        then(console).should().print("DATE | AMOUNT | BALANCE");
        then(console).should().print("2020-07-07|-50.00|200.00");
        then(console).should().print("2020-07-07|150.00|250.00");
        then(console).should().print("2020-07-06|100.00|100.00");
    }
}