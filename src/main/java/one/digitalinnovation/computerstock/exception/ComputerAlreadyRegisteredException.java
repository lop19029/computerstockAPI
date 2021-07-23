package one.digitalinnovation.computerstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ComputerAlreadyRegisteredException extends Exception{

    public ComputerAlreadyRegisteredException(String computerName) {
        super(String.format("Computer with name %s already registered in the system.", computerName));
    }
}
