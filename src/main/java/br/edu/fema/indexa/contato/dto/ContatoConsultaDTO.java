package br.edu.fema.indexa.contato.dto;

import br.edu.fema.indexa.contato.Contato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContatoConsultaDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String avatar;
    private String redes;
    private String observacoes;

    public ContatoConsultaDTO(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.telefone = contato.getTelefone();
        this.email = contato.getEmail();
        this.avatar = contato.getAvatar();
        this.redes = contato.getRedes();
        this.observacoes = contato.getObservacoes();
    }

    public static List<ContatoConsultaDTO> converterParaListDTO(List<Contato> contatos) {
        return contatos.stream().map(contato -> new ContatoConsultaDTO(contato)).toList();
    }
}
