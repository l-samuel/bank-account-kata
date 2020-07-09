package org.sg;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.*;


public class BankAccountTest {

    private DateTime dateTime = mock(DateTime.class);
    private BankAccount bankAccount;
    private LocalDateTime timestamp = LocalDateTime.now();
    private Printer printer = mock(Printer.class);
    private TransactionConnector transactionConnector;

    @BeforeEach
    void setUp() {
        transactionConnector = mock(TransactionConnector.class);
        bankAccount = new BankAccount(transactionConnector, dateTime, printer);
        when(dateTime.getTime()).thenReturn(timestamp);
    }

    @Test
    void should_add_transaction_with_positive_amount_when_user_make_a_deposit() {
        //given
        long depositAmount = 1500;
        //when
        bankAccount.deposit(depositAmount);
        //then
        Transaction expectedTransaction = new Transaction(new Amount(BigDecimal.valueOf(depositAmount, 2)), timestamp);
        verify(transactionConnector).addTransaction(expectedTransaction);
    }

    @Test
    void should_add_transaction_with_positive_amount_by_deposit() {
        //given
        //when
        bankAccount.deposit(1500);
        bankAccount.deposit(2000);
        //then
        Transaction expectedTransaction1 = new Transaction(new Amount(BigDecimal.valueOf(1500, 2)), timestamp);
        Transaction expectedTransaction2 = new Transaction(new Amount(BigDecimal.valueOf(2000, 2)), timestamp);
        verify(transactionConnector).addTransaction(expectedTransaction1);
        verify(transactionConnector).addTransaction(expectedTransaction2);
    }

    @Test
    void should_add_transaction_with_negative_amount_when_user_make_a_withdrawal() {
        //given
        long withdrawalAmount = 400;
        //when
        bankAccount.withdrawal(withdrawalAmount);
        //then
        Transaction expectedTransaction = new Transaction(new Amount(BigDecimal.valueOf(-400, 2)), timestamp);
        verify(transactionConnector).addTransaction(expectedTransaction);
    }

    @Test
    void should_add_transaction_with_negative_amount_by_withdrawal() {
        //given
        //when
        bankAccount.withdrawal(400);
        bankAccount.withdrawal(800);
        //then
        Transaction expectedTransaction1 = new Transaction(new Amount(BigDecimal.valueOf(-400, 2)), timestamp);
        Transaction expectedTransaction2 = new Transaction(new Amount(BigDecimal.valueOf(-800, 2)), timestamp);
        verify(transactionConnector).addTransaction(expectedTransaction1);
        verify(transactionConnector).addTransaction(expectedTransaction2);
    }


    @Test
    void should_print_all_transactions(){
        //given
        List<Transaction> transactions = Lists.newArrayList(new Transaction(new Amount(BigDecimal.valueOf(100,2)), timestamp));
        when(transactionConnector.getTransactions()).thenReturn(transactions);
        //when
        bankAccount.printStatements();
        //then
        verify(printer).printStatements(transactions);
    }
}
