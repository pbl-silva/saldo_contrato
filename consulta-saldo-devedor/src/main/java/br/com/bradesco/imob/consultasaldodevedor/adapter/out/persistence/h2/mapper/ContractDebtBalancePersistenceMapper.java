package br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.mapper;

import br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.data.ContractDebtBalanceData;
import br.com.bradesco.imob.consultasaldodevedor.application.model.contract.ContractDebtBalance;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ContractDebtBalancePersistenceMapper {

    ContractDebtBalance toDomain(ContractDebtBalanceData data);
}
