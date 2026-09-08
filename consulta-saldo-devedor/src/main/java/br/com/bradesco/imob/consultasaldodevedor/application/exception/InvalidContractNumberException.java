package br.com.bradesco.imob.consultasaldodevedor.application.exception;

public class InvalidContractNumberException extends RuntimeException {

    public InvalidContractNumberException() {
        super("Número do contrato inválido.");
    }
}
