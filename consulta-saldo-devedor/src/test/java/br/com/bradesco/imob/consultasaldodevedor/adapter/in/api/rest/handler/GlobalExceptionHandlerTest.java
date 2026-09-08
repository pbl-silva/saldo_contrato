package br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.handler;

import br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.controller.contract.v1.ContractController;
import br.com.bradesco.imob.consultasaldodevedor.adapter.in.api.rest.mapper.ContractDebtBalanceRestMapper;
import br.com.bradesco.imob.consultasaldodevedor.application.exception.InvalidContractNumberException;
import br.com.bradesco.imob.consultasaldodevedor.application.port.in.contract.GetContractDebtBalanceUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContractController.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetContractDebtBalanceUseCase useCase;

    @MockitoBean
    private ContractDebtBalanceRestMapper mapper;

    @Test
    void shouldNotExposeInternalDetailsOnUnexpectedFailure() throws Exception {
        when(useCase.execute(1001L)).thenThrow(new IllegalStateException("sensitive-database-details"));

        mockMvc.perform(get("/api/v1/contratos/1001/saldo-devedor"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.title").value("Erro interno"))
                .andExpect(jsonPath("$.detail").value("Não foi possível concluir a solicitação."))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(content().string(not(containsString("sensitive-database-details"))));
    }

    @Test
    void shouldTranslateApplicationValidationException() throws Exception {
        when(useCase.execute(1001L)).thenThrow(new InvalidContractNumberException());

        mockMvc.perform(get("/api/v1/contratos/1001/saldo-devedor"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("Número do contrato inválido."))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }
}
