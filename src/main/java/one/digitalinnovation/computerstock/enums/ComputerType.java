package one.digitalinnovation.computerstock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ComputerType {

    HOME("Home"),
    BUSINESS("Business"),
    GAMER("Gamer");

    private final String description;
}
