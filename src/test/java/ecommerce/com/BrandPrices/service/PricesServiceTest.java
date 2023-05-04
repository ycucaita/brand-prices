package ecommerce.com.BrandPrices.service;

import ecommerce.com.BrandPrices.application.mapper.PriceResponse;
import ecommerce.com.BrandPrices.domain.Brand;
import ecommerce.com.BrandPrices.domain.Price;
import ecommerce.com.BrandPrices.domain.error.InvalidDateFormatException;
import ecommerce.com.BrandPrices.infraestructure.PriceRepository;
import ecommerce.com.BrandPrices.ports.services.PricesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesServiceTest {

    public static final String EUR_CURRENCY = "EUR";
    public static final int PRODUCT_ID = 35455;
    @InjectMocks
    private PricesService pricesService;

    @Mock
    private PriceRepository priceRepository;

    @Test
    void givenInvalidApplicationDateThenReturnInvalidDateFormatException() {

        Exception exception = assertThrows(InvalidDateFormatException.class,()->
                pricesService.getPrice("invalidData", PRODUCT_ID,1));
        assertTrue(exception.getMessage().contains("Invalid date format"));
    }

    @Test
    void givenDataPricesThenReturnPricesDataFromDatabase() {

        LocalDateTime applicationDateFormat = LocalDateTime.of(2020, Month.JUNE,16,21,00);

        Price price  = new Price();
        price.setPrice(BigDecimal.ONE);
        price.setPricesList(1);
        price.setPriority(1);
        price.setCurrency(EUR_CURRENCY);
        price.setId(1);
        price.setEndDate(LocalDate.now());
        price.setBrand(new Brand(1,"ZARA",LocalDateTime.now()));
        price.setProductId(PRODUCT_ID);

        Optional<Price> priceEntity = Optional.of(price);

        when(priceRepository.getPrices(
            applicationDateFormat.toLocalDate(), applicationDateFormat.toLocalTime(), PRODUCT_ID, 1))
                .thenReturn(priceEntity);

        Optional<PriceResponse> result =
                pricesService.getPrice("2020-06-16T21:00:00", PRODUCT_ID, 1);

        PriceResponse priceResponse = result.get();

        assertEquals(PRODUCT_ID,priceResponse.getProductId());
        assertEquals(EUR_CURRENCY,priceResponse.getCurrency());
    }


}
