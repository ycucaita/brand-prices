package ecommerce.com.BrandPrices.application.mapper;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponse {

    private int brandId;
    private LocalDate startDate;
    private LocalTime startDateHour;
    private LocalDate endDate;
    private LocalTime endDateHour;
    private int priceList;
    private int productId;
    private int priority;
    private BigDecimal price;
    private String currency;

}
