package br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.mapper;

import br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.dto.contract.ContractDebtBalanceResponseDto;
import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ContractDebtBalanceRestMapper {

    ContractDebtBalanceResponseDto toDto(ContractDebtBalance model);
}
