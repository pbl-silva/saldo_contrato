package br.com.bradesco.imob.consultasaldodevedor.application.validator.contract;

import br.com.bradesco.imob.consultasaldodevedor.application.exception.InvalidContractNumberException;

public final class ContractNumberValidator {

    private ContractNumberValidator() {
    }

    public static void validate(Long contractNumber) {
        if (contractNumber == null || contractNumber <= 0) {
            throw new InvalidContractNumberException();
        }
    }
}
