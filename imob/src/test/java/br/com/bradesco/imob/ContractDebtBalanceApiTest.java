package br.com.bradesco.imob;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContractDebtBalanceApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnContractDebtBalance() throws Exception {
        mockMvc.perform(get(
                        "/api/v1/contratos/1001/saldo-devedor"
                ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroContrato").value(1001))
                .andExpect(jsonPath("$.tipoAmortizacao").value("SAC"))
                .andExpect(jsonPath("$.dataVencimento").value("15/09/2026"))
                .andExpect(content().string(
                        containsString("\"jurosNominal\":8.750")
                ))
                .andExpect(content().string(
                        containsString("\"saldoDevedor\":150000.00")
                ))
                .andExpect(content().string(
                        containsString("\"indiceEconomico\":1.025")
                ))
                .andExpect(content().string(
                        containsString("\"jurosAnterior\":8.500")
                ))
                .andExpect(content().string(
                        containsString("\"valorPrestacao\":2450.30")
                ));
    }

    @Test
    void shouldReturnEmptyObjectWhenContractDoesNotExist()
            throws Exception {
        mockMvc.perform(get(
                        "/api/v1/contratos/9999/saldo-devedor"
                ))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void shouldReturnEmptyObjectWhenContractNumberIsZero()
            throws Exception {
        mockMvc.perform(get(
                        "/api/v1/contratos/0/saldo-devedor"
                ))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void shouldReturnBadRequestWhenContractNumberIsNegative()
            throws Exception {
        mockMvc.perform(get(
                        "/api/v1/contratos/-1/saldo-devedor"
                ))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenContractNumberIsNotNumeric()
            throws Exception {
        mockMvc.perform(get(
                        "/api/v1/contratos/abc/saldo-devedor"
                ))
                .andExpect(status().isBadRequest());
    }
}