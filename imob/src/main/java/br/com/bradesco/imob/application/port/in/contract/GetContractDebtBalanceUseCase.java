package br.com.bradesco.imob.application.port.in.contract;

import br.com.bradesco.imob.application.model.contract.ContractDebtBalance;

import java.util.Optional;

public interface GetContractDebtBalanceUseCase {

    Optional<ContractDebtBalance> execute(Long contractNumber);
}