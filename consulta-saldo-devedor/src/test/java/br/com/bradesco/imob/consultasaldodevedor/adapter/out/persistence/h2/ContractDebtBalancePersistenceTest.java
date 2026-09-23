package br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2;

import br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.data.ContractDebtBalanceData;
import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.consultasaldodevedor.application.port.out.contract.ContractDebtBalancePersistence;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ContractDebtBalancePersistenceTest {

    @Autowired
    private ContractDebtBalancePersistence persistence;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldReadPersistedDataAndMapAllFieldsToDomain() {
        var expected = new ContractDebtBalance(
                2002L,
                new BigDecimal("9.125"),
                new BigDecimal("320000.45"),
                new BigDecimal("1.123"),
                "PRICE",
                new BigDecimal("8.999"),
                new BigDecimal("3500.67"),
                LocalDate.of(2027, 1, 2)
        );

        var data = new ContractDebtBalanceData(
                expected.contractNumber(),
                expected.nominalInterest(),
                expected.debtBalance(),
                expected.economicIndex(),
                expected.amortizationType(),
                expected.previousInterest(),
                expected.installmentAmount(),
                expected.dueDate()
        );

        entityManager.persist(data);
        entityManager.flush();
        entityManager.clear();

        assertThat(
                persistence.findByContractNumber(2002L)
        ).contains(expected);
    }

    @Test
    void shouldReturnEmptyWhenNoRowExists() {
        assertThat(
                persistence.findByContractNumber(9999L)
        ).isEmpty();
    }
}