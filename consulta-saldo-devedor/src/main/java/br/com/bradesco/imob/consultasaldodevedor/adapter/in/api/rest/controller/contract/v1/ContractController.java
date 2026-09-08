package br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.controller.contract.v1;

import br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.mapper.ContractDebtBalanceRestMapper;
import br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.dto.contract.ContractDebtBalanceResponseDto;
import br.com.bradesco.imob.consultasaldodevedor.application.port.in.contract.GetContractDebtBalanceUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contratos")
@RequiredArgsConstructor
public class ContractController {

    private final GetContractDebtBalanceUseCase useCase;
    private final ContractDebtBalanceRestMapper mapper;

    @GetMapping("/{numeroContrato}/saldo-devedor")
    @Operation(summary = "Consulta o saldo devedor de um contrato")
    @ApiResponse(responseCode = "200", description = "Contrato encontrado ou objeto {} se não encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ContractDebtBalanceResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Número de contrato inválido",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "500", description = "Erro interno",
            content = @Content(mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<ContractDebtBalanceResponseDto> getDebtBalance(
            @PathVariable("numeroContrato")
            @Positive(message = "O número do contrato deve ser maior que zero.")
            Long contractNumber
    ) {
        var response = useCase.execute(contractNumber)
                .map(mapper::toDto)
                .orElseGet(ContractDebtBalanceResponseDto::empty);
        return ResponseEntity.ok(response);
    }
}
