package one.digitalinnovation.computerstock.service;

import one.digitalinnovation.computerstock.builder.ComputerDTOBuilder;
import one.digitalinnovation.computerstock.dto.ComputerDTO;
import one.digitalinnovation.computerstock.entity.Computer;
import one.digitalinnovation.computerstock.exception.ComputerAlreadyRegisteredException;
import one.digitalinnovation.computerstock.exception.ComputerNotFoundException;
import one.digitalinnovation.computerstock.mapper.ComputerMapper;
import one.digitalinnovation.computerstock.repository.ComputerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComputerServiceTest {

    private static final long INVALID_COMPUTER_ID = 1L;

    @Mock
    private ComputerRepository computerRepository;

    private ComputerMapper computerMapper = ComputerMapper.INSTANCE;

    @InjectMocks
    private ComputerService computerService;

    @Test
    void whenComputerInformedThenItShouldBeCreated() throws ComputerAlreadyRegisteredException {
        // given
        ComputerDTO expectedComputerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();
        Computer expectedSavedComputer = computerMapper.toModel(expectedComputerDTO);

        // when
        when(computerRepository.findByName(expectedComputerDTO.getName())).thenReturn(Optional.empty());
        when(computerRepository.save(expectedSavedComputer)).thenReturn(expectedSavedComputer);

        //then
        ComputerDTO createdComputerDTO = computerService.createComputer(expectedComputerDTO);

        assertThat(createdComputerDTO.getId(), is(equalTo(expectedComputerDTO.getId())));
        assertThat(createdComputerDTO.getName(), is(equalTo(expectedComputerDTO.getName())));
        assertThat(createdComputerDTO.getQuantity(), is(equalTo(expectedComputerDTO.getQuantity())));
    }

    @Test
    void whenAlreadyRegisteredComputerInformedThenAnExceptionShouldBeThrown() {
        // given
        ComputerDTO expectedComputerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();
        Computer duplicatedComputer = computerMapper.toModel(expectedComputerDTO);

        // when
        when(computerRepository.findByName(expectedComputerDTO.getName())).thenReturn(Optional.of(duplicatedComputer));

        // then
        assertThrows(ComputerAlreadyRegisteredException.class, () -> computerService.createComputer(expectedComputerDTO));
    }

    @Test
    void whenValidComputerNameIsGivenThenReturnAComputer() throws ComputerNotFoundException {
        // given
        ComputerDTO expectedFoundComputerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();
        Computer expectedFoundComputer = computerMapper.toModel(expectedFoundComputerDTO);

        // when
        when(computerRepository.findByName(expectedFoundComputer.getName())).thenReturn(Optional.of(expectedFoundComputer));

        // then
        ComputerDTO foundComputerDTO = computerService.findByName(expectedFoundComputerDTO.getName());

        assertThat(foundComputerDTO, is(equalTo(expectedFoundComputerDTO)));
    }

    @Test
    void whenNotRegisteredComputerNameIsGivenThenThrowAnException() {
        // given
        ComputerDTO expectedFoundComputerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();

        // when
        when(computerRepository.findByName(expectedFoundComputerDTO.getName())).thenReturn(Optional.empty());

        // then
        assertThrows(ComputerNotFoundException.class, () -> computerService.findByName(expectedFoundComputerDTO.getName()));
    }

}
