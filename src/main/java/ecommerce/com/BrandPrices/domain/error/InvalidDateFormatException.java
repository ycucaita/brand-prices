package ecommerce.com.BrandPrices.domain.error;

public class InvalidDateFormatException extends RuntimeException{

    public InvalidDateFormatException(String message) {
        super(message);
    }
}
