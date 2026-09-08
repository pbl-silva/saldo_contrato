package br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.repository.contract;

import br.com.bradesco.imob.consultasaldodevedor.adapter.out.persistence.h2.data.ContractDebtBalanceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractDebtBalanceJpaRepository extends JpaRepository<ContractDebtBalanceData, Long> {
}
