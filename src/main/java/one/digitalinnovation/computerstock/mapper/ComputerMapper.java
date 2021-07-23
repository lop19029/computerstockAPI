package one.digitalinnovation.computerstock.mapper;

import one.digitalinnovation.computerstock.dto.ComputerDTO;
import one.digitalinnovation.computerstock.entity.Computer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComputerMapper {

    ComputerMapper INSTANCE = Mappers.getMapper(ComputerMapper.class);

    Computer toModel(ComputerDTO computerDTO);

    ComputerDTO toDTO(Computer computer);
}
