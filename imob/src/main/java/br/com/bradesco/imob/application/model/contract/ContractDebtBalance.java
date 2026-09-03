package br.com.bradesco.imob.application.model.contract;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContractDebtBalance(
        Long contractNumber,
        BigDecimal nominalInterest,
        BigDecimal debtBalance,
        BigDecimal economicIndex,
        String amortizationType,
        BigDecimal previousInterest,
        BigDecimal installmentAmount,
        LocalDate dueDate
) {
}