package com.infoshare.junit.$7_mocks;

import com.infoshare.junit.banking.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Observer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class BankTest {

    private TransferBank bank;
    private Account sourceAccount;
    private Account targetAccount;

    @Test
    public void account_monitors_are_notified_about_transactions() throws Exception {
        // given
        bank = new GenericBank();
        Observer accountMonitor = Mockito.mock(Observer.class);
        LoggingActivityMonitor loggingMonitor = new LoggingActivityMonitor();

        sourceAccount = bank.getAccountFor("Kent Beck");
        sourceAccount.addObserver(accountMonitor);
        sourceAccount.addObserver(loggingMonitor);

        targetAccount = bank.getAccountFor("Dan North");
        targetAccount.addObserver(accountMonitor);
        targetAccount.addObserver(loggingMonitor);

        sourceAccount.register(new Transaction(BigDecimal.valueOf(10000), LocalDateTime.now()));
        targetAccount.register(new Transaction(BigDecimal.valueOf(10000), LocalDateTime.now()));

        //
        assertThat(sourceAccount.countObservers()).isEqualTo(2);
        assertThat(targetAccount.countObservers()).isEqualTo(2);
        verify(accountMonitor, times(2)).update(any(), any());
    }

    @Test
    public void bank_registers_transactions_on_both_accounts() throws Exception {
        // given
        TransferBank bank = new GenericBank();
        Account sourceAccount = mock(Account.class);
        Account targetAccount = mock(Account.class);
        LocalDateTime transactionDate = LocalDateTime.of(2016, Month.OCTOBER, 2, 0, 0);

        // when
        when(sourceAccount.transferTo(isA(Account.class), isA(BigDecimal.class), isA(LocalDateTime.class)))
                .thenReturn( new Transaction(BigDecimal.valueOf(500), transactionDate, sourceAccount, targetAccount));
        when(targetAccount.transferTo(isA(Account.class), isA(BigDecimal.class), isA(LocalDateTime.class)))
                .thenReturn( new Transaction(BigDecimal.valueOf(500), transactionDate, targetAccount, sourceAccount));

        Transaction t = sourceAccount.transferTo(targetAccount, BigDecimal.ZERO, LocalDateTime.now());
        bank.register(t);
        bank.process();

        t = targetAccount.transferTo(sourceAccount, BigDecimal.ZERO, LocalDateTime.now());
        bank.register(t);
        bank.process();

        // then
        assertThat(t.getAmount()).isEqualTo("500");
        verify(sourceAccount, times(2)).register(any());
        verify(targetAccount, times(2)).register(any());
    }

    @Test
    public void stub_failure_of_transfer_processing() {
        TransferBank bank = mock(GenericBank.class);
        doThrow(InvalidTransactionException.class).when(bank).process();

        assertThatThrownBy(bank::process).isInstanceOf(InvalidTransactionException.class);
    }

}
