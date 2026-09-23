package br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.boundary.contract;

import br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.mapper.ContractDebtBalanceMapper;
import br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.repository.contract.ContractDebtBalanceRepository;
import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.consultasaldodevedor.application.port.out.contract.ContractDebtBalancePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@ConditionalOnProperty(
        name = "imob.persistence",
        havingValue = "h2",
        matchIfMissing = true
)
@RequiredArgsConstructor
public class ContractDebtBalancePersistenceImpl
        implements ContractDebtBalancePersistence {

    private final ContractDebtBalanceRepository repository;
    private final ContractDebtBalanceMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<ContractDebtBalance> findByContractNumber(
            Long contractNumber
    ) {
        return repository.findByContractNumber(contractNumber)
                .map(mapper::toDomain);
    }
}