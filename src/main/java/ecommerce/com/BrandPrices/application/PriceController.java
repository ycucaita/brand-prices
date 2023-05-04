package ecommerce.com.BrandPrices.application;

import ecommerce.com.BrandPrices.application.mapper.PriceResponse;
import ecommerce.com.BrandPrices.ports.services.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices/")
@Validated
public class PriceController {

    @Autowired
    private PricesService pricesService;

    @GetMapping
    public ResponseEntity<PriceResponse> getPricesInformation(@RequestParam("applicationDate")  @Valid @NotBlank
                                                  String applicationDate,
                                                              @RequestParam("productId") int productId,
                                                              @RequestParam("brandId") int brandId) {

        Optional<PriceResponse> response = pricesService.getPrice(applicationDate, productId , brandId);
        return response.isPresent() ?
                new ResponseEntity<>(response.get(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }








}
