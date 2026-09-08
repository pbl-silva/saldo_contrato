package br.com.bradesco.imob.consultasaldodevedor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ContractDebtBalanceApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnContractDebtBalanceWithExactNumericScales() throws Exception {
        var body = mockMvc.perform(get("/api/v1/contratos/1001/saldo-devedor"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                          "numeroContrato": 1001,
                          "jurosNominal": 8.750,
                          "saldoDevedor": 150000.00,
                          "indiceEconomico": 1.025,
                          "tipoAmortizacao": "SAC",
                          "jurosAnterior": 8.500,
                          "valorPrestacao": 2450.30,
                          "dataVencimento": "15/09/2026"
                        }
                        """, JsonCompareMode.STRICT))
                .andExpect(jsonPath("$.jurosNominal").isNumber())
                .andExpect(jsonPath("$.saldoDevedor").isNumber())
                .andExpect(jsonPath("$.indiceEconomico").isNumber())
                .andExpect(jsonPath("$.jurosAnterior").isNumber())
                .andExpect(jsonPath("$.valorPrestacao").isNumber())
                .andReturn().getResponse().getContentAsString();

        assertNumericToken(body, "jurosNominal", "8.750");
        assertNumericToken(body, "saldoDevedor", "150000.00");
        assertNumericToken(body, "indiceEconomico", "1.025");
        assertNumericToken(body, "jurosAnterior", "8.500");
        assertNumericToken(body, "valorPrestacao", "2450.30");
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 9999, Long.MAX_VALUE})
    void shouldReturnExactlyEmptyObjectWhenPositiveContractDoesNotExist(long number) throws Exception {
        mockMvc.perform(get("/api/v1/contratos/{number}/saldo-devedor", number))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}", JsonCompareMode.STRICT));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "abc", "9223372036854775808"})
    void shouldReturnStructuredBadRequestWhenContractNumberIsInvalid(String number) throws Exception {
        mockMvc.perform(get("/api/v1/contratos/{number}/saldo-devedor", number))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.title").isNotEmpty())
                .andExpect(jsonPath("$.detail").isNotEmpty())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.instance").value("/api/v1/contratos/" + number + "/saldo-devedor"));
    }

    @Test
    void shouldPreserveNotFoundForUnknownRoute() throws Exception {
        mockMvc.perform(get("/rota-inexistente"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void shouldPreserveMethodNotAllowed() throws Exception {
        mockMvc.perform(post("/api/v1/contratos/1001/saldo-devedor"))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(header().string("Allow", containsString("GET")))
                .andExpect(jsonPath("$.status").value(405));
    }

    @Test
    void shouldPublishTypedOpenApiResponse() throws Exception {
        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.components.schemas.ContractDebtBalanceResponseDto.properties.numeroContrato").exists())
                .andExpect(jsonPath("$.paths['/api/v1/contratos/{numeroContrato}/saldo-devedor'].get.responses['200'].content['application/json'].schema['$ref']")
                        .value("#/components/schemas/ContractDebtBalanceResponseDto"))
                .andExpect(jsonPath("$.paths['/api/v1/contratos/{numeroContrato}/saldo-devedor'].get.responses['400']").exists());
    }

    @Test
    void shouldServeSwaggerUi() throws Exception {
        mockMvc.perform(get("/swagger-ui/index.html"))
                .andExpect(status().isOk());
    }

    private static void assertNumericToken(String body, String field, String expected) {
        assertThat(body).containsPattern("\"" + field + "\"\\s*:\\s*"
                + Pattern.quote(expected) + "(?=\\s*[,}])");
    }
}
