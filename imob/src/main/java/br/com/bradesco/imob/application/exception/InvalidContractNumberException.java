package br.com.bradesco.imob.application.exception;

public class InvalidContractNumberException extends RuntimeException {

    public InvalidContractNumberException() {
        super("Número do contrato inválido.");
    }
}