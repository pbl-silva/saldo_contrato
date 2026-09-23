package br.com.bradesco.imob.consultasaldodevedor.application.usecase.contract;

import br.com.bradesco.imob.consultasaldodevedor.application.exception.InvalidContractNumberException;
import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.consultasaldodevedor.application.service.contract.ContractDebtBalanceService;
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

    private final ContractDebtBalanceService service =
            mock(ContractDebtBalanceService.class);

    private final GetContractDebtBalanceUseCaseImpl useCase =
            new GetContractDebtBalanceUseCaseImpl(service);

    @Test
    void shouldReturnContractWhenFound() {
        var contract = contractDebtBalance();

        when(service.findByContractNumber(1001L))
                .thenReturn(Optional.of(contract));

        var result = useCase.execute(1001L);

        assertThat(result).contains(contract);
    }

    @Test
    void shouldReturnEmptyWhenContractDoesNotExist() {
        when(service.findByContractNumber(9999L))
                .thenReturn(Optional.empty());

        assertThat(useCase.execute(9999L)).isEmpty();
    }

    @Test
    void shouldRejectZeroContractNumber() {
        assertThatThrownBy(() -> useCase.execute(0L))
                .isInstanceOf(InvalidContractNumberException.class)
                .hasMessage("Número do contrato inválido.");

        verifyNoInteractions(service);
    }

    @Test
    void shouldRejectNegativeContractNumber() {
        assertThatThrownBy(() -> useCase.execute(-1L))
                .isInstanceOf(InvalidContractNumberException.class)
                .hasMessage("Número do contrato inválido.");

        verifyNoInteractions(service);
    }

    @Test
    void shouldRejectNullContractNumber() {
        assertThatThrownBy(() -> useCase.execute(null))
                .isInstanceOf(InvalidContractNumberException.class)
                .hasMessage("Número do contrato inválido.");

        verifyNoInteractions(service);
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