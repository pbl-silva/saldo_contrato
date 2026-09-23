package br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.repository.contract;

import br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.data.ContractDebtBalanceData;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContractDebtBalanceRepositoryImpl
        implements ContractDebtBalanceRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<ContractDebtBalanceData> findByContractNumber(
            Long contractNumber
    ) {
        return Optional.ofNullable(
                entityManager.find(
                        ContractDebtBalanceData.class,
                        contractNumber
                )
        );
    }
}