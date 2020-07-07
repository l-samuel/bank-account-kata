import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sg.AccountService;
import org.sg.DateTime;
import org.sg.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountServiceTest {


    private DateTime dateTime = Mockito.mock(DateTime.class);

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(initTransactions(), dateTime);
    }

    @Test
    void should_return_all_transaction() {
        //given
        //when
        List<Transaction> transactions = accountService.getTransactions();
        //then
        List<Transaction> expectedTransaction = initTransactions();
        assertThat(transactions).containsAll(expectedTransaction);
    }

    @Test
    void should_add_transaction_with_positive_amount_when_user_make_a_deposit() {
        //given
        long depositAmount = 1500;
        LocalDateTime timestamp = LocalDateTime.now();
        Mockito.when(dateTime.getTime()).thenReturn(timestamp);
        //when
        accountService.deposit(depositAmount);
        //then
        List<Transaction> expectedTransaction = initTransactions();
        expectedTransaction.add(new Transaction(1500, timestamp));
        assertThat(accountService.getTransactions()).containsAll(expectedTransaction);
    }

    private List<Transaction> initTransactions() {
        return Lists.list(
                new Transaction(100, LocalDateTime.of(2020, 7, 7, 0, 0)));
    }
}
