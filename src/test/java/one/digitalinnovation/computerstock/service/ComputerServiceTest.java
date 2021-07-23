package one.digitalinnovation.computerstock.service;

import one.digitalinnovation.computerstock.mapper.ComputerMapper;
import one.digitalinnovation.computerstock.repository.ComputerRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ComputerServiceTest {

    private static final long INVALID_COMPUTER_ID = 1L;

    @Mock
    private ComputerRepository computerRepository;

    private ComputerMapper computerMapper = ComputerMapper.INSTANCE;

    @InjectMocks
    private ComputerService computerService;


//
//    @Test
//    void whenIncrementIsCalledThenIncrementBeerStock() throws ComputerNotFoundException, ComputerStockExceededException {
//        //given
//        ComputerDTO expectedComputerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();
//        Computer expectedComputer = computerMapper.toModel(expectedComputerDTO);
//
//        //when
//        when(computerRepository.findById(expectedComputerDTO.getId())).thenReturn(Optional.of(expectedComputer));
//        when(computerRepository.save(expectedComputer)).thenReturn(expectedComputer);
//
//        int quantityToIncrement = 10;
//        int expectedQuantityAfterIncrement = expectedComputerDTO.getQuantity() + quantityToIncrement;
//
//        // then
//        ComputerDTO incrementedComputerDTO = computerService.increment(expectedComputerDTO.getId(), quantityToIncrement);
//
//        assertThat(expectedQuantityAfterIncrement, equalTo(incrementedComputerDTO.getQuantity()));
//        assertThat(expectedQuantityAfterIncrement, lessThan(expectedComputerDTO.getMax()));
//    }
//
//    @Test
//    void whenIncrementIsGreatherThanMaxThenThrowException() {
//        ComputerDTO expectedComputerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();
//        Computer expectedComputer = computerMapper.toModel(expectedComputerDTO);
//
//        when(computerRepository.findById(expectedComputerDTO.getId())).thenReturn(Optional.of(expectedComputer));
//
//        int quantityToIncrement = 80;
//        assertThrows(ComputerStockExceededException.class, () -> computerService.increment(expectedComputerDTO.getId(), quantityToIncrement));
//    }
//
//    @Test
//    void whenIncrementAfterSumIsGreatherThanMaxThenThrowException() {
//        ComputerDTO expectedComputerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();
//        Computer expectedComputer = computerMapper.toModel(expectedComputerDTO);
//
//        when(computerRepository.findById(expectedComputerDTO.getId())).thenReturn(Optional.of(expectedComputer));
//
//        int quantityToIncrement = 45;
//        assertThrows(ComputerStockExceededException.class, () -> computerService.increment(expectedComputerDTO.getId(), quantityToIncrement));
//    }
//
//    @Test
//    void whenIncrementIsCalledWithInvalidIdThenThrowException() {
//        int quantityToIncrement = 10;
//
//        when(computerRepository.findById(INVALID_BEER_ID)).thenReturn(Optional.empty());
//
//        assertThrows(ComputerNotFoundException.class, () -> computerService.increment(INVALID_BEER_ID, quantityToIncrement));
//    }
//

//
//    @Test
//    void whenDecrementIsCalledThenDecrementBeerStock() throws BeerNotFoundException, BeerStockExceededException {
//        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
//
//        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
//        when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);
//
//        int quantityToDecrement = 5;
//        int expectedQuantityAfterDecrement = expectedBeerDTO.getQuantity() - quantityToDecrement;
//        BeerDTO incrementedBeerDTO = beerService.decrement(expectedBeerDTO.getId(), quantityToDecrement);
//
//        assertThat(expectedQuantityAfterDecrement, equalTo(incrementedBeerDTO.getQuantity()));
//        assertThat(expectedQuantityAfterDecrement, greaterThan(0));
//    }
//
//    @Test
//    void whenDecrementIsCalledToEmptyStockThenEmptyBeerStock() throws BeerNotFoundException, BeerStockExceededException {
//        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
//
//        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
//        when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);
//
//        int quantityToDecrement = 10;
//        int expectedQuantityAfterDecrement = expectedBeerDTO.getQuantity() - quantityToDecrement;
//        BeerDTO incrementedBeerDTO = beerService.decrement(expectedBeerDTO.getId(), quantityToDecrement);
//
//        assertThat(expectedQuantityAfterDecrement, equalTo(0));
//        assertThat(expectedQuantityAfterDecrement, equalTo(incrementedBeerDTO.getQuantity()));
//    }
//
//    @Test
//    void whenDecrementIsLowerThanZeroThenThrowException() {
//        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
//
//        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
//
//        int quantityToDecrement = 80;
//        assertThrows(BeerStockExceededException.class, () -> beerService.decrement(expectedBeerDTO.getId(), quantityToDecrement));
//    }
//
//    @Test
//    void whenDecrementIsCalledWithInvalidIdThenThrowException() {
//        int quantityToDecrement = 10;
//
//        when(beerRepository.findById(INVALID_BEER_ID)).thenReturn(Optional.empty());
//
//        assertThrows(BeerNotFoundException.class, () -> beerService.decrement(INVALID_BEER_ID, quantityToDecrement));
//    }
}
