package one.digitalinnovation.computerstock.repository;

import one.digitalinnovation.computerstock.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

    Optional<Computer> findByName(String name);
}
