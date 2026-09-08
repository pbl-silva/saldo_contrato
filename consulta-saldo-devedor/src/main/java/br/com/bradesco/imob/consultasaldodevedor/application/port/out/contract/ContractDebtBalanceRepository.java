package br.com.bradesco.imob.consultasaldodevedor.application.port.out.contract;

import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;

import java.util.Optional;

public interface ContractDebtBalanceRepository {

    Optional<ContractDebtBalance> findByContractNumber(Long contractNumber);
}
