package ecommerce.com.BrandPrices.integration;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ecommerce.com.BrandPrices.application.mapper.PriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PriceIntegrationTest {

    private static final String APPLICATION_DATE_PARAMETER = "applicationDate";
    private static final String BRAND_ID_PARAMETER = "brandId";
    private static final String URL_PRICES = "/api/prices/";
    public static final int PRODUCT_ID = 35455;
    public static final int BRAND_ID = 1;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenScenery1ThenReturnDataResponse() throws Exception {

        String applicationDate = "2020-06-14T10:00:00";

        MvcResult result = getMvcResult(applicationDate);

        PriceResponse response = getPriceResponse(result);
        assertNotNull(response);
        assertEquals(PRODUCT_ID, response.getProductId());
        assertEquals(BRAND_ID, response.getBrandId());
        assertEquals(LocalDate.parse("2020-06-14"), response.getStartDate());
        assertEquals(LocalTime.parse("00:00"), response.getStartDateHour());
        assertEquals(LocalDate.parse("2020-12-31"), response.getEndDate());
        assertEquals(LocalTime.parse("23:59:59"), response.getEndDateHour());
        assertEquals(BRAND_ID, response.getPriceList());
        assertEquals("35.50", response.getPrice().toString());
    }

    @Test
    public void givenScenery2ThenReturnDataResponse() throws Exception {

        String applicationDate = "2020-06-14T16:00:00";

        MvcResult result = getMvcResult(applicationDate);

        PriceResponse response = getPriceResponse(result);
        assertNotNull(response);
        assertEquals(PRODUCT_ID, response.getProductId());
        assertEquals(BRAND_ID, response.getBrandId());
        assertEquals(LocalDate.parse("2020-06-14"), response.getStartDate());
        assertEquals(LocalTime.parse("15:00"), response.getStartDateHour());
        assertEquals(LocalDate.parse("2020-06-14"), response.getEndDate());
        assertEquals(LocalTime.parse("18:30"), response.getEndDateHour());
        assertEquals(2, response.getPriceList());
        assertEquals("25.45", response.getPrice().toString());
    }



    @Test
    public void givenScenery3ThenReturnDataResponse() throws Exception {

        String applicationDate = "2020-06-14T21:00:00";

        MvcResult result = getMvcResult(applicationDate);

        PriceResponse response = getPriceResponse(result);
        assertNotNull(response);
        assertEquals(PRODUCT_ID, response.getProductId());
        assertEquals(BRAND_ID, response.getBrandId());
        assertEquals(LocalDate.parse("2020-06-14"), response.getStartDate());
        assertEquals(LocalTime.parse("00:00"), response.getStartDateHour());
        assertEquals(LocalDate.parse("2020-12-31"), response.getEndDate());
        assertEquals(LocalTime.parse("23:59:59"), response.getEndDateHour());
        assertEquals(BRAND_ID, response.getPriceList());
        assertEquals("35.50", response.getPrice().toString());
    }

    private static String PRODUCT_ID_PARAMETER() {
        return "productId";
    }

    @Test
    public void givenScenery4ThenReturnDataResponse() throws Exception {

        String applicationDate = "2020-06-15T10:00:00";

        MvcResult result = getMvcResult(applicationDate);

        PriceResponse response = getPriceResponse(result);
        assertNotNull(response);
        assertEquals(PRODUCT_ID, response.getProductId());
        assertEquals(BRAND_ID, response.getBrandId());
        assertEquals(LocalDate.parse("2020-06-15"), response.getStartDate());
        assertEquals(LocalTime.parse("00:00"), response.getStartDateHour());
        assertEquals(LocalDate.parse("2020-06-15"), response.getEndDate());
        assertEquals(LocalTime.parse("11:00"), response.getEndDateHour());
        assertEquals(3, response.getPriceList());
        assertEquals("30.50", response.getPrice().toString());
    }


    @Test
    public void givenScenery5ThenReturnDataResponse() throws Exception {

        String applicationDate = "2020-06-16T21:00:00";
        int productId = PRODUCT_ID;
        int brandId = BRAND_ID;

        mockMvc.perform(MockMvcRequestBuilders.get(URL_PRICES)
                        .param(APPLICATION_DATE_PARAMETER, applicationDate)
                        .param(PRODUCT_ID_PARAMETER(), String.valueOf(productId))
                        .param(BRAND_ID_PARAMETER, String.valueOf(brandId)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    private MvcResult getMvcResult(String applicationDate) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(URL_PRICES)
                        .param(APPLICATION_DATE_PARAMETER, applicationDate)
                        .param(PRODUCT_ID_PARAMETER(), String.valueOf(PRODUCT_ID))
                        .param(BRAND_ID_PARAMETER, String.valueOf(BRAND_ID)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    private static PriceResponse getPriceResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PriceResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), PriceResponse.class);
        return response;
    }

}
