package br.com.bradesco.imob.adapter.in.api.rest.controller.contract.v1;

import br.com.bradesco.imob.adapter.in.api.rest.mapper.ContractDebtBalanceRestMapper;
import br.com.bradesco.imob.application.port.in.contract.GetContractDebtBalanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/contratos")
@RequiredArgsConstructor
public class ContractController {

    private final GetContractDebtBalanceUseCase useCase;
    private final ContractDebtBalanceRestMapper mapper;

    @GetMapping("/{numeroContrato}/saldo-devedor")
    public ResponseEntity<?> getDebtBalance(
            @PathVariable("numeroContrato") Long contractNumber
    ) {
        var result = useCase.execute(contractNumber);

        if (result.isEmpty()) {
            return ResponseEntity.ok(Map.of());
        }

        return ResponseEntity.ok(mapper.toDto(result.get()));
    }
}