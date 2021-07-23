package one.digitalinnovation.computerstock.controller;

import one.digitalinnovation.computerstock.builder.ComputerDTOBuilder;
import one.digitalinnovation.computerstock.dto.ComputerDTO;
import one.digitalinnovation.computerstock.dto.QuantityDTO;
import one.digitalinnovation.computerstock.exception.ComputerNotFoundException;
import one.digitalinnovation.computerstock.service.ComputerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static one.digitalinnovation.computerstock.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ComputerControllerTest {

    private static final String COMPUTER_API_URL_PATH = "/api/v1/computers";
    private static final long VALID_COMPUTER_ID = 1L;
    private static final long INVALID_COMPUTER_ID = 2l;
    private static final String COMPUTER_API_SUBPATH_INCREMENT_URL = "/increment";
    private static final String COMPUTER_API_SUBPATH_DECREMENT_URL = "/decrement";

    private MockMvc mockMvc;

    @Mock
    private ComputerService computerService;

    @InjectMocks
    private ComputerController computerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(computerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAComputerIsCreated() throws Exception {
        // given
        ComputerDTO computerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();

        // when
        when(computerService.createComputer(computerDTO)).thenReturn(computerDTO);

        // then
        mockMvc.perform(post(COMPUTER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(computerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(computerDTO.getName())))
                .andExpect(jsonPath("$.brand", is(computerDTO.getBrand())))
                .andExpect(jsonPath("$.type", is(computerDTO.getType().toString())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        // given
        ComputerDTO computerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();
        computerDTO.setBrand(null);

        // then
        mockMvc.perform(post(COMPUTER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(computerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {
        // given
        ComputerDTO computerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();

        //when
        when(computerService.findByName(computerDTO.getName())).thenReturn(computerDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(COMPUTER_API_URL_PATH + "/" + computerDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(computerDTO.getName())))
                .andExpect(jsonPath("$.brand", is(computerDTO.getBrand())))
                .andExpect(jsonPath("$.type", is(computerDTO.getType().toString())));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredNameThenNotFoundStatusIsReturned() throws Exception {
        // given
        ComputerDTO computerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();

        //when
        when(computerService.findByName(computerDTO.getName())).thenThrow(ComputerNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(COMPUTER_API_URL_PATH + "/" + computerDTO.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithComputersIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        ComputerDTO computerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();

        //when
        when(computerService.listAll()).thenReturn(Collections.singletonList(computerDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(COMPUTER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(computerDTO.getName())))
                .andExpect(jsonPath("$[0].brand", is(computerDTO.getBrand())))
                .andExpect(jsonPath("$[0].type", is(computerDTO.getType().toString())));
    }

    @Test
    void whenGETListWithoutComputersIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        ComputerDTO computerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();

        //when
        when(computerService.listAll()).thenReturn(Collections.singletonList(computerDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(COMPUTER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        ComputerDTO computerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();

        //when
        doNothing().when(computerService).deleteById(computerDTO.getId());

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(COMPUTER_API_URL_PATH + "/" + computerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        //when
        doThrow(ComputerNotFoundException.class).when(computerService).deleteById(INVALID_COMPUTER_ID);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(COMPUTER_API_URL_PATH + "/" + INVALID_COMPUTER_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPATCHIsCalledToIncrementDiscountThenOKStatusIsReturned() throws Exception {
        QuantityDTO quantityDTO = QuantityDTO.builder()
                .quantity(10)
                .build();

        ComputerDTO computerDTO = ComputerDTOBuilder.builder().build().toComputerDTO();
        computerDTO.setQuantity(computerDTO.getQuantity() + quantityDTO.getQuantity());

        when(computerService.increment(VALID_COMPUTER_ID, quantityDTO.getQuantity())).thenReturn(computerDTO);

        mockMvc.perform(MockMvcRequestBuilders.patch(COMPUTER_API_URL_PATH + "/" + VALID_COMPUTER_ID + COMPUTER_API_SUBPATH_INCREMENT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(quantityDTO))).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(computerDTO.getName())))
                .andExpect(jsonPath("$.brand", is(computerDTO.getBrand())))
                .andExpect(jsonPath("$.type", is(computerDTO.getType().toString())))
                .andExpect(jsonPath("$.quantity", is(computerDTO.getQuantity())));
    }
}
