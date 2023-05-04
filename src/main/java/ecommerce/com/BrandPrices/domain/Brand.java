package ecommerce.com.BrandPrices.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "BRANDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int brandId;

    @Column(name="BRAND_NAME")
    private String brandName;

    @Column(name="TRANSACTION_DATE")
    private LocalDateTime transactionDate;

}
