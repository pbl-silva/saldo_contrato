package br.com.bradesco.imob.adapter.in.api.rest.mapper;

import br.com.bradesco.imob.adapter.in.api.rest.dto.ContractDebtBalanceDto;
import br.com.bradesco.imob.application.model.contract.ContractDebtBalance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractDebtBalanceRestMapper {

    ContractDebtBalanceDto toDto(ContractDebtBalance model);
}