package one.digitalinnovation.computerstock.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.computerstock.dto.ComputerDTO;
import one.digitalinnovation.computerstock.entity.Computer;
import one.digitalinnovation.computerstock.exception.ComputerAlreadyRegisteredException;
import one.digitalinnovation.computerstock.exception.ComputerNotFoundException;
import one.digitalinnovation.computerstock.mapper.ComputerMapper;
import one.digitalinnovation.computerstock.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ComputerService {

    private final ComputerRepository computerRepository;
    private final ComputerMapper computerMapper = ComputerMapper.INSTANCE;

    public ComputerDTO createComputer(ComputerDTO computerDTO) throws ComputerAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(computerDTO.getName());
        Computer computer = computerMapper.toModel(computerDTO);
        Computer savedComputer = computerRepository.save(computer);
        return computerMapper.toDTO(savedComputer);
    }

    public ComputerDTO findByName(String name) throws ComputerNotFoundException {
        Computer foundComputer = computerRepository.findByName(name)
                .orElseThrow(() -> new ComputerNotFoundException(name));
        return computerMapper.toDTO(foundComputer);
    }

    public List<ComputerDTO> listAll() {
        return computerRepository.findAll()
                .stream()
                .map(computerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws ComputerNotFoundException {
        verifyIfExists(id);
        computerRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws ComputerAlreadyRegisteredException {
        Optional<Computer> optSavedComputer = computerRepository.findByName(name);
        if (optSavedComputer.isPresent()) {
            throw new ComputerAlreadyRegisteredException(name);
        }
    }

    private Computer verifyIfExists(Long id) throws ComputerNotFoundException {
        return computerRepository.findById(id)
                .orElseThrow(() -> new ComputerNotFoundException(id));
    }
}
