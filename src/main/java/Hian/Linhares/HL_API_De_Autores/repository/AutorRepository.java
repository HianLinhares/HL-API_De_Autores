package Hian.Linhares.HL_API_De_Autores.repository;

import Hian.Linhares.HL_API_De_Autores.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
