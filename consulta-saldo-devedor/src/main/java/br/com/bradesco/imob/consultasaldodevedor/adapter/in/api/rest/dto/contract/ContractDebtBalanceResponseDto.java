package br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.dto.contract;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ContractDebtBalanceResponseDto(
        @JsonProperty("numeroContrato")
        Long contractNumber,

        @JsonProperty("jurosNominal")
        BigDecimal nominalInterest,

        @JsonProperty("saldoDevedor")
        BigDecimal debtBalance,

        @JsonProperty("indiceEconomico")
        BigDecimal economicIndex,

        @JsonProperty("tipoAmortizacao")
        String amortizationType,

        @JsonProperty("jurosAnterior")
        BigDecimal previousInterest,

        @JsonProperty("valorPrestacao")
        BigDecimal installmentAmount,

        @JsonProperty("dataVencimento")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dueDate
) {
    public ContractDebtBalanceResponseDto {
        nominalInterest = withScale(nominalInterest, 3);
        debtBalance = withScale(debtBalance, 2);
        economicIndex = withScale(economicIndex, 3);
        previousInterest = withScale(previousInterest, 3);
        installmentAmount = withScale(installmentAmount, 2);
    }

    public static ContractDebtBalanceResponseDto empty() {
        return new ContractDebtBalanceResponseDto(null, null, null, null, null, null, null, null);
    }

    private static BigDecimal withScale(BigDecimal value, int scale) {
        // Não arredonda silenciosamente valores recebidos da persistência.
        return value == null ? null : value.setScale(scale, RoundingMode.UNNECESSARY);
    }
}
