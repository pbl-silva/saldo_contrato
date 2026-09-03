package br.com.bradesco.imob.adapter.in.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContractDebtBalanceDto(
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
}