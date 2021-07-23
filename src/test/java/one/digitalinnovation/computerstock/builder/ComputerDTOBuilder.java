package one.digitalinnovation.computerstock.builder;

import lombok.Builder;
import one.digitalinnovation.computerstock.dto.ComputerDTO;
import one.digitalinnovation.computerstock.enums.ComputerType;

@Builder
public class ComputerDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Aspire 5";

    @Builder.Default
    private String brand = "Acer";

    @Builder.Default
    private int max = 50;

    @Builder.Default
    private int quantity = 10;

    @Builder.Default
    private ComputerType type = ComputerType.HOME;

    public ComputerDTO toComputerDTO() {
        return new ComputerDTO(id,
                name,
                brand,
                max,
                quantity,
                type);
    }
}
