package br.com.bradesco.imob.application.usecase.contract;

import br.com.bradesco.imob.application.exception.InvalidContractNumberException;
import br.com.bradesco.imob.application.model.contract.ContractDebtBalance;
import br.com.bradesco.imob.application.port.in.contract.GetContractDebtBalanceUseCase;
import br.com.bradesco.imob.application.port.out.contract.ContractDebtBalanceFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetContractDebtBalanceUseCaseImpl
        implements GetContractDebtBalanceUseCase {

    private final ContractDebtBalanceFinder contractDebtBalanceFinder;

    @Override
    public Optional<ContractDebtBalance> execute(Long contractNumber) {
        if (contractNumber == null || contractNumber < 0) {
            throw new InvalidContractNumberException();
        }

        return contractDebtBalanceFinder.findByContractNumber(contractNumber);
    }
}