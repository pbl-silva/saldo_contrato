package br.com.bradesco.imob.consultasaldodevedor.application.service.contract;

import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.consultasaldodevedor.application.port.out.contract.ContractDebtBalancePersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContractDebtBalanceServiceImpl
        implements ContractDebtBalanceService {

    private final ContractDebtBalancePersistence persistence;

    @Override
    public Optional<ContractDebtBalance> findByContractNumber(Long contractNumber) {
        return persistence.findByContractNumber(contractNumber);
    }
}