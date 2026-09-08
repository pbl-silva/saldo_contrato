package br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.dto.contract;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ContractDebtBalanceResponseDtoTest {

    @Test
    void shouldNormalizeNumericScalesWithoutChangingValues() {
        var dto = new ContractDebtBalanceResponseDto(1L,
                new BigDecimal("8.7"), new BigDecimal("150000"), new BigDecimal("1"),
                "SAC", new BigDecimal("8.50"), new BigDecimal("2450.3"), LocalDate.of(2026, 9, 15));

        assertThat(dto.nominalInterest()).isEqualTo(new BigDecimal("8.700"));
        assertThat(dto.debtBalance()).isEqualTo(new BigDecimal("150000.00"));
        assertThat(dto.economicIndex()).isEqualTo(new BigDecimal("1.000"));
        assertThat(dto.previousInterest()).isEqualTo(new BigDecimal("8.500"));
        assertThat(dto.installmentAmount()).isEqualTo(new BigDecimal("2450.30"));
    }

    @Test
    void shouldNotSilentlyRoundUnexpectedPrecision() {
        assertThatThrownBy(() -> new ContractDebtBalanceResponseDto(1L,
                new BigDecimal("8.1234"), null, null, null, null, null, null))
                .isInstanceOf(ArithmeticException.class);
    }
}
