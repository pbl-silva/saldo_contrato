package br.com.bradesco.imob.application.usecase.contract;

import br.com.bradesco.imob.application.exception.InvalidContractNumberException;
import br.com.bradesco.imob.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.application.port.out.contract.ContractDebtBalanceFinder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class GetContractDebtBalanceUseCaseImplTest {

    private final ContractDebtBalanceFinder finder =
            mock(ContractDebtBalanceFinder.class);

    private final GetContractDebtBalanceUseCaseImpl useCase =
            new GetContractDebtBalanceUseCaseImpl(finder);

    @Test
    void shouldReturnContractWhenFound() {
        var contract = contractDebtBalance();

        when(finder.findByContractNumber(1001L))
                .thenReturn(Optional.of(contract));

        var result = useCase.execute(1001L);

        assertThat(result).contains(contract);
    }

    @Test
    void shouldReturnEmptyWhenContractDoesNotExist() {
        when(finder.findByContractNumber(9999L))
                .thenReturn(Optional.empty());

        assertThat(useCase.execute(9999L)).isEmpty();
    }

    @Test
    void shouldReturnEmptyWhenContractNumberIsZero() {
        when(finder.findByContractNumber(0L))
                .thenReturn(Optional.empty());

        assertThat(useCase.execute(0L)).isEmpty();
    }

    @Test
    void shouldRejectNegativeContractNumber() {
        assertThatThrownBy(() -> useCase.execute(-1L))
                .isInstanceOf(InvalidContractNumberException.class)
                .hasMessage("Número do contrato inválido.");

        verifyNoInteractions(finder);
    }

    @Test
    void shouldRejectNullContractNumber() {
        assertThatThrownBy(() -> useCase.execute(null))
                .isInstanceOf(InvalidContractNumberException.class)
                .hasMessage("Número do contrato inválido.");

        verifyNoInteractions(finder);
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