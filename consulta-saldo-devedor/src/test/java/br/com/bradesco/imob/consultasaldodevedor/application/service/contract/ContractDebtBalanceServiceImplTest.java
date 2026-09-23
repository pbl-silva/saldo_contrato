package br.com.bradesco.imob.consultasaldodevedor.application.service.contract;

import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.consultasaldodevedor.application.port.out.contract.ContractDebtBalancePersistence;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ContractDebtBalanceServiceImplTest {

    private final ContractDebtBalancePersistence persistence =
            mock(ContractDebtBalancePersistence.class);

    private final ContractDebtBalanceServiceImpl service =
            new ContractDebtBalanceServiceImpl(persistence);

    @Test
    void shouldReturnContractDebtBalanceWhenContractExists() {
        var contractDebtBalance = contractDebtBalance();

        when(persistence.findByContractNumber(1001L))
                .thenReturn(Optional.of(contractDebtBalance));

        var result = service.findByContractNumber(1001L);

        assertThat(result)
                .contains(contractDebtBalance);

        verify(persistence)
                .findByContractNumber(1001L);
    }

    @Test
    void shouldReturnEmptyWhenContractDoesNotExist() {
        when(persistence.findByContractNumber(9999L))
                .thenReturn(Optional.empty());

        var result = service.findByContractNumber(9999L);

        assertThat(result)
                .isEmpty();

        verify(persistence)
                .findByContractNumber(9999L);
    }

    private ContractDebtBalance contractDebtBalance() {
        return new ContractDebtBalance(
                1001L,
                new BigDecimal("8.750"),
                new BigDecimal("150000.00"),
                new BigDecimal("1.025"),
                "SAC",
                new BigDecimal("8.500"),
                new BigDecimal("2450.30"),
                LocalDate.of(2026, 9, 15)
        );
    }
}