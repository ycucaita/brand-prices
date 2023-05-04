package ecommerce.com.BrandPrices.ports.services;

import ecommerce.com.BrandPrices.application.mapper.PriceResponse;
import ecommerce.com.BrandPrices.domain.Price;
import ecommerce.com.BrandPrices.domain.error.InvalidDateFormatException;
import ecommerce.com.BrandPrices.infraestructure.PriceRepository;
import ecommerce.com.BrandPrices.ports.usecases.PricesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;


@Service
@Transactional
public class PricesService implements PricesUseCase {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public Optional<PriceResponse> getPrice(String applicationDate, int productId , int brandId) {

        LocalDateTime applicationDateFormat;

        try{
             applicationDateFormat= LocalDateTime.parse(applicationDate);
        }  catch (DateTimeParseException ex) {
            throw new InvalidDateFormatException("Invalid date format: " + applicationDate);
        }

        Optional<Price> price = priceRepository.getPrices(
                applicationDateFormat.toLocalDate(), applicationDateFormat.toLocalTime(), productId, brandId);

        return price.map(PricesService::buildPricesResponse);
    }
    private static PriceResponse buildPricesResponse(Price price) {
        return PriceResponse.builder()
                .productId(price.getProductId())
                .price(price.getPrice())
                .priceList(price.getPricesList())
                .startDate(price.getStartDate())
                .startDateHour(price.getStartDateHour())
                .endDateHour(price.getEndDateHour())
                .endDate(price.getEndDate())
                .brandId(price.getBrand().getBrandId())
                .priority(price.getPriority())
                .currency(price.getCurrency())
                .build();
    }
}
