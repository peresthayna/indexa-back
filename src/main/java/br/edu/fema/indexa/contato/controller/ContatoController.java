package br.edu.fema.indexa.contato.controller;

import br.edu.fema.indexa.contato.Contato;
import br.edu.fema.indexa.contato.dto.ContatoCadastroDTO;
import br.edu.fema.indexa.contato.dto.ContatoConsultaDTO;
import br.edu.fema.indexa.contato.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "contato")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public List<ContatoConsultaDTO> findAll() {
        return ContatoConsultaDTO.converterParaListDTO(contatoService.findAll());
    }

    @GetMapping("/{id}")
    public ContatoConsultaDTO findById(@PathVariable Long id) {
        return new ContatoConsultaDTO(contatoService.findById(id));
    }

    @GetMapping("/busca/{busca}")
    public List<ContatoConsultaDTO> findByBusca(@PathVariable String busca) {
        return ContatoConsultaDTO.converterParaListDTO(contatoService.findAllByNameSearch(busca));
    }

    @GetMapping("/letra/{letra}")
    public List<ContatoConsultaDTO> findByLetra(@PathVariable String letra) {
        return ContatoConsultaDTO.converterParaListDTO(contatoService.findAllByLetter(letra));
    }

    @PostMapping
    public ContatoCadastroDTO save(@RequestBody ContatoCadastroDTO contatoCadastroDTO) {
        return new ContatoCadastroDTO(contatoService.save(new Contato(contatoCadastroDTO)));
    }

    @PutMapping("/{id}")
    public ContatoConsultaDTO update(@PathVariable Long id, @RequestBody ContatoConsultaDTO contato) {
        return new ContatoConsultaDTO(contatoService.update(new Contato(contato), id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
