package ecommerce.com.BrandPrices.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "PRICES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

    @Column(name="START_DATE")
    private LocalDate startDate;

    @Column(name="START_DATE_HOUR")
    private LocalTime startDateHour;

    @Column(name="END_DATE")
    private LocalDate endDate;

    @Column(name="END_DATE_HOUR")
    private LocalTime endDateHour;

    @Column(name="PRICE_LIST")
    private int pricesList;

    @Column(name="PRODUCT_ID")
    private int productId;

    @Column(name="PRIORITY")
    private int priority;

    @Column(name="PRICE")
    private BigDecimal price;

    @Column(name="CURR")
    private String currency;

    @Column(name="TRANSACTION_DATE")
    private LocalDateTime transactionDate;


}
