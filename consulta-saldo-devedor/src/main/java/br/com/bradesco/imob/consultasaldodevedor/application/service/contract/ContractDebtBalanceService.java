package br.com.bradesco.imob.consultasaldodevedor.application.service.contract;

import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;

import java.util.Optional;

public interface ContractDebtBalanceService {

    Optional<ContractDebtBalance> findByContractNumber(Long contractNumber);
}