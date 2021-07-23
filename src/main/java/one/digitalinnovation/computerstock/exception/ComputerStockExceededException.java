package one.digitalinnovation.computerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ComputerStockExceededException extends Exception {

    public ComputerStockExceededException(Long id, int quantityToIncrement) {
        super(String.format("Computers with %s ID to increment informed exceeds the max stock capacity: %s", id, quantityToIncrement));
    }
}
