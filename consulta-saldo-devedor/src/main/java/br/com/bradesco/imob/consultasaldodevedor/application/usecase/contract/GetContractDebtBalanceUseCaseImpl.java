package br.com.bradesco.imob.consultasaldodevedor.application.usecase.contract;

import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.consultasaldodevedor.application.port.in.contract.GetContractDebtBalanceUseCase;
import br.com.bradesco.imob.consultasaldodevedor.application.service.contract.ContractDebtBalanceService;
import br.com.bradesco.imob.consultasaldodevedor.application.validator.contract.ContractNumberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetContractDebtBalanceUseCaseImpl
        implements GetContractDebtBalanceUseCase {

    private final ContractDebtBalanceService service;

    @Override
    public Optional<ContractDebtBalance> execute(Long contractNumber) {
        ContractNumberValidator.validate(contractNumber);

        return service.findByContractNumber(contractNumber);
    }
}