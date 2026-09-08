package br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contract_debt_balance")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ContractDebtBalanceData {

    @Id
    @Column(name = "contract_number")
    private Long contractNumber;

    @Column(name = "nominal_interest", nullable = false, precision = 12, scale = 3)
    private BigDecimal nominalInterest;

    @Column(name = "debt_balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal debtBalance;

    @Column(name = "economic_index", nullable = false, precision = 12, scale = 3)
    private BigDecimal economicIndex;

    @Column(name = "amortization_type", nullable = false, length = 30)
    private String amortizationType;

    @Column(name = "previous_interest", nullable = false, precision = 12, scale = 3)
    private BigDecimal previousInterest;

    @Column(name = "installment_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal installmentAmount;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
}
