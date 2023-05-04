package ecommerce.com.BrandPrices.ports.usecases;

import ecommerce.com.BrandPrices.application.mapper.PriceResponse;


import java.util.Optional;

public interface PricesUseCase {

    Optional<PriceResponse> getPrice(String applicationDate, int productId , int brandId);


}
