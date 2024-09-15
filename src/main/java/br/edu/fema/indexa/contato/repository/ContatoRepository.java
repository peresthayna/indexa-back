package br.edu.fema.indexa.contato.repository;

import br.edu.fema.indexa.contato.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findAllByNomeStartingWith(String prefixo);
    List<Contato> findByNomeContaining(String nome);
}
