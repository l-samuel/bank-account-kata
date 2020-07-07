package org.sg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sg.model.Transaction;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;


public class AccountServiceTest {


    private DateTime dateTime = mock(DateTime.class);

    private AccountService accountService;
    private LocalDateTime timestamp = LocalDateTime.now();
    private Printer printer = mock(Printer.class);

    @BeforeEach
    void setUp() {
        accountService = new AccountService(initTransactions(new long[]{100}), dateTime, printer);
        when(dateTime.getTime()).thenReturn(timestamp);
    }

    @Test
    void should_return_all_transaction() {
        //given
        //when
        List<Transaction> transactions = accountService.getTransactions();
        //then
        List<Transaction> expectedTransaction = initTransactions(new long[]{100});
        assertThat(transactions).containsAll(expectedTransaction);
    }

    @Test
    void should_add_transaction_with_positive_amount_when_user_make_a_deposit() {
        //given
        long depositAmount = 1500;
        //when
        accountService.deposit(depositAmount);
        //then
        List<Transaction> expectedTransaction = initTransactions(new long[]{100, 1500});
        assertThat(accountService.getTransactions()).containsAll(expectedTransaction);
    }

    @Test
    void should_add_transaction_with_positive_amount_by_deposit() {
        //given
        //when
        accountService.deposit(1500);
        accountService.deposit(2000);
        //then
        List<Transaction> expectedTransaction = initTransactions(new long[]{100, 1500, 2000});
        assertThat(accountService.getTransactions()).containsAll(expectedTransaction);
    }

    @Test
    void should_add_transaction_with_negative_amount_when_user_make_a_withdrawal() {
        //given
        long withdrawalAmount = 400;
        //when
        accountService.withdrawal(withdrawalAmount);
        //then
        List<Transaction> expectedTransaction = initTransactions(new long[]{100, -400});
        assertThat(accountService.getTransactions()).containsAll(expectedTransaction);
    }

    @Test
    void should_add_transaction_with_negative_amount_by_withdrawal() {
        //given
        //when
        accountService.withdrawal(400);
        accountService.withdrawal(800);
        //then
        List<Transaction> expectedTransaction = initTransactions(new long[]{100, -400,-800});
        assertThat(accountService.getTransactions()).containsAll(expectedTransaction);
    }


    @Test
    void accountService_should_print_all_transactions_with_balance(){

        //given
        //when
        accountService.printStatements();
        //then
        then(printer).should(times(1)).printStatements();

    }

    private List<Transaction> initTransactions(final long amounts[]) {
        return Arrays.stream(amounts)
                .mapToObj(amount -> new Transaction(amount, timestamp))
                .collect(Collectors.toList());
    }
}
