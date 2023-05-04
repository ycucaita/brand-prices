package ecommerce.com.BrandPrices.infraestructure;

import ecommerce.com.BrandPrices.domain.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends CrudRepository<Price, Integer> {

    @Query(value="SELECT * FROM PRICES\n" +
            " WHERE START_DATE= ?1 \n" +
            " AND START_DATE_HOUR<= ?2 \n" +
            " AND END_DATE_HOUR> ?2 "+
            " AND PRODUCT_ID = ?3 \n" +
            " AND BRAND_ID = ?4 \n" +
            "AND PRIORITY =(SELECT MAX(PRIORITY ) FROM PRICES\n" +
            "  WHERE START_DATE= ?1 \n" +
            "  AND START_DATE_HOUR<= ?2 \n" +
            "  AND END_DATE_HOUR> ?2 );",
            nativeQuery = true)
    Optional<Price> getPrices(LocalDate startDate, LocalTime startHour,
                              int productId, int brandId);


}
