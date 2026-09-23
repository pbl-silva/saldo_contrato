package br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.repository.contract;

import br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.data.ContractDebtBalanceData;

import java.util.Optional;

public interface ContractDebtBalanceRepository {

    Optional<ContractDebtBalanceData> findByContractNumber(
            Long contractNumber
    );
}