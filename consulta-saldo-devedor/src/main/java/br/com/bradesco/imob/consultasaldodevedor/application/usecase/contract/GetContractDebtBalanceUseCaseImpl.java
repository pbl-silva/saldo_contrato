package br.com.bradesco.imob.consultasaldodevedor.application.usecase.contract;

import br.com.bradesco.imob.consultasaldodevedor.application.validator.contract.ContractNumberValidator;
import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.consultasaldodevedor.application.port.in.contract.GetContractDebtBalanceUseCase;
import br.com.bradesco.imob.consultasaldodevedor.application.port.out.contract.ContractDebtBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetContractDebtBalanceUseCaseImpl
        implements GetContractDebtBalanceUseCase {

    private final ContractDebtBalanceRepository repository;

    @Override
    public Optional<ContractDebtBalance> execute(Long contractNumber) {
        ContractNumberValidator.validate(contractNumber);

        return repository.findByContractNumber(contractNumber);
    }
}
