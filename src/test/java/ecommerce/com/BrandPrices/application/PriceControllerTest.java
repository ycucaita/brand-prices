package ecommerce.com.BrandPrices.application;


import ecommerce.com.BrandPrices.application.mapper.PriceResponse;
import ecommerce.com.BrandPrices.ports.services.PricesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricesService pricesService;

    @Test
    public void testGetPricesInformation() throws Exception {

        when(pricesService.getPrice("2023-05-01T00:00:00", 35455, 1))
                .thenReturn(Optional.of(
                        new PriceResponse(1,
                                LocalDate.of(2020, Month.JUNE,14),
                                LocalTime.of(00,00,00),
                                LocalDate.of(2020,Month.JUNE,14),
                                LocalTime.of(00,00,00),
                                1,
                                1,1, BigDecimal.valueOf(20.0), "EUR")));

         mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/")
                        .param("applicationDate", "2023-05-01T00:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(20.0))
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andReturn();

    }

    @Test
    public void testGetPricesInformationNotFound() throws Exception {

        when(pricesService.getPrice(anyString(), anyInt(), anyInt()))
                .thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/")
                        .param("applicationDate", "2023-05-01T00:00:00")
                        .param("productId", "1")
                        .param("brandId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
