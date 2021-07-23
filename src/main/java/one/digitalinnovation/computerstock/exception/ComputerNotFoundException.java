package one.digitalinnovation.computerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ComputerNotFoundException extends Exception {

    public ComputerNotFoundException(String computerName) {
        super(String.format("Computer with name %s not found in the system.", computerName));
    }

    public ComputerNotFoundException(Long id) {
        super(String.format("Computer with id %s not found in the system.", id));
    }
}
