package br.com.bradesco.imob.application.port.out.contract;

import br.com.bradesco.imob.application.model.contract.ContractDebtBalance;

import java.util.Optional;

public interface ContractDebtBalanceFinder {

    Optional<ContractDebtBalance> findByContractNumber(Long contractNumber);
}