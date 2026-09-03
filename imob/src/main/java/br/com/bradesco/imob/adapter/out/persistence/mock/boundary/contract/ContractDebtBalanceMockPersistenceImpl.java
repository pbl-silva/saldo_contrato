package br.com.bradesco.imob.adapter.out.persistence.mock.boundary.contract;

import br.com.bradesco.imob.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.application.port.out.contract.ContractDebtBalanceFinder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Component
@Profile("local")
public class ContractDebtBalanceMockPersistenceImpl
        implements ContractDebtBalanceFinder {

    private static final Map<Long, ContractDebtBalance> CONTRACTS = Map.of(
            1001L,
            new ContractDebtBalance(
                    1001L,
                    new BigDecimal("8.750"),
                    new BigDecimal("150000.00"),
                    new BigDecimal("1.025"),
                    "SAC",
                    new BigDecimal("8.500"),
                    new BigDecimal("2450.30"),
                    LocalDate.of(2026, 9, 15)
            )
    );

    @Override
    public Optional<ContractDebtBalance> findByContractNumber(
            Long contractNumber
    ) {
        return Optional.ofNullable(CONTRACTS.get(contractNumber));
    }
}