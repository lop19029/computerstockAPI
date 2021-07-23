package one.digitalinnovation.computerstock.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.computerstock.dto.ComputerDTO;
import one.digitalinnovation.computerstock.dto.QuantityDTO;
import one.digitalinnovation.computerstock.exception.ComputerAlreadyRegisteredException;
import one.digitalinnovation.computerstock.exception.ComputerNotFoundException;
import one.digitalinnovation.computerstock.exception.ComputerStockExceededException;
import one.digitalinnovation.computerstock.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/computers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ComputerController implements ComputerControllerDocs {

    private final ComputerService computerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComputerDTO createComputer(@RequestBody @Valid ComputerDTO computerDTO) throws ComputerAlreadyRegisteredException {
        return computerService.createComputer(computerDTO);
    }

    @GetMapping("/{name}")
    public ComputerDTO findByName(@PathVariable String name) throws ComputerNotFoundException {
        return computerService.findByName(name);
    }

    @GetMapping
    public List<ComputerDTO> listComputers() {
        return computerService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ComputerNotFoundException {
        computerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public ComputerDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws ComputerNotFoundException, ComputerStockExceededException {
        return computerService.increment(id, quantityDTO.getQuantity());
    }

}
