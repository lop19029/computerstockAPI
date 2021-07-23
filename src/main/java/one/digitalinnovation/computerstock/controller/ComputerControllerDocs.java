package one.digitalinnovation.computerstock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import one.digitalinnovation.computerstock.dto.ComputerDTO;
import one.digitalinnovation.computerstock.exception.ComputerAlreadyRegisteredException;
import one.digitalinnovation.computerstock.exception.ComputerNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Manages computer stock")
public interface ComputerControllerDocs {

    @ApiOperation(value = "Computer creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success computer creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    ComputerDTO createComputer(ComputerDTO computerDTO) throws ComputerAlreadyRegisteredException;

    @ApiOperation(value = "Returns computer found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success computer found in the system"),
            @ApiResponse(code = 404, message = "Computer with given name not found.")
    })
    ComputerDTO findByName(@PathVariable String name) throws ComputerNotFoundException;

    @ApiOperation(value = "Returns a list of all computers registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all computers registered in the system"),
    })
    List<ComputerDTO> listComputers();

    @ApiOperation(value = "Delete a computer found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success computer deleted in the system"),
            @ApiResponse(code = 404, message = "computer with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws ComputerNotFoundException;
}
