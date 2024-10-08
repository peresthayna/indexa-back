package br.edu.fema.indexa.contato.service;

import br.edu.fema.indexa.contato.Contato;
import br.edu.fema.indexa.contato.dto.ContatoCadastroDTO;
import br.edu.fema.indexa.contato.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }

    public Contato findById(Long id) {
        Optional optional = contatoRepository.findById(id);
        if(optional.isPresent()) {
            return (Contato) optional.get();
        }
        throw new RuntimeException("Contato não encontrado");
    }

    public List<Contato> findAllByLetter(String prefixo) {
        if(prefixo == null || prefixo == "") {
            throw new RuntimeException("Letra não pode estar vazia/nula");
        }
        return contatoRepository.findAllByNomeStartingWith(prefixo);
    }

    public List<Contato> findAllByNameSearch(String nome) {
        if(nome == null || nome == "") {
            throw new RuntimeException("Busca não pode estar vazia/nula");
        }
        nome = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        nome = nome.toLowerCase();
        return contatoRepository.findAllByNomeContainingIgnoreCaseAndAccent(nome);
    }

    public Contato save(Contato contato) {
        this.validarCampos(contato);
        if(contatoRepository.findByTelefone(contato.getTelefone()).isPresent()) {
            throw new RuntimeException("Este telefone já foi cadastrado");
        } if(contatoRepository.findByEmail(contato.getEmail()).isPresent()) {
            throw new RuntimeException("Este email já foi cadastrado");
        }
        return contatoRepository.save(contato);
    }

    public Contato update(Contato contato, Long id) {
        if(id != null || contatoRepository.findById(id).isPresent()) {
            contato.setId(id);
        }
        this.validarCampos(contato);
        return contatoRepository.save(contato);
    }

    public void delete(Long id) {
        if(contatoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Contato não encontrado");
        }
        contatoRepository.delete(contatoRepository.findById(id).get());
    }

    public static boolean validate(String emailStr) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr).find();
    }

    public void validarCampos(Contato contato) {
        if(contato.getNome() == null || contato.getNome().isEmpty()) {
            throw new RuntimeException("Nome precisa ser informado");
        } if(contato.getTelefone() == null || contato.getTelefone().isEmpty()) {
            throw new RuntimeException("Telefone precisa ser informado");
        } if(contato.getEmail() == null || contato.getEmail().isEmpty()) {
            throw new RuntimeException("Email precisa ser informado");
        } if(!ContatoService.validate(contato.getEmail())) {
            throw new IllegalArgumentException("Email invalido");
        }
    }
}
