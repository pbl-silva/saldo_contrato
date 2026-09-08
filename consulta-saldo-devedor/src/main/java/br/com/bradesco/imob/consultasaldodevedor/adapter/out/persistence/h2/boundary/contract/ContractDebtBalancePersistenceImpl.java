package br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.boundary.contract;

import br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.mapper.ContractDebtBalancePersistenceMapper;
import br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.repository.contract.ContractDebtBalanceJpaRepository;
import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.consultasaldodevedor.application.port.out.contract.ContractDebtBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@ConditionalOnProperty(name = "imob.persistence", havingValue = "h2", matchIfMissing = true)
@RequiredArgsConstructor
public class ContractDebtBalancePersistenceImpl implements ContractDebtBalanceRepository {

    private final ContractDebtBalanceJpaRepository repository;
    private final ContractDebtBalancePersistenceMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<ContractDebtBalance> findByContractNumber(Long contractNumber) {
        return repository.findById(contractNumber).map(mapper::toDomain);
    }
}
