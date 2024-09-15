package br.edu.fema.indexa.contato.repository;

import br.edu.fema.indexa.contato.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findAllByNomeStartingWith(String prefixo);
    @Query(value = "SELECT * FROM contato WHERE unaccent(LOWER(nome)) LIKE unaccent(LOWER(CONCAT('%', :nome, '%')))", nativeQuery = true)
    List<Contato> findAllByNomeContainingIgnoreCaseAndAccent(@Param("nome") String nome);
    Optional<Contato> findByTelefone(String telefone);
    Optional<Contato> findByEmail(String email);
}
