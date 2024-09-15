package br.edu.fema.indexa.contato;

import br.edu.fema.indexa.contato.dto.ContatoCadastroDTO;
import br.edu.fema.indexa.contato.dto.ContatoConsultaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "redes")
    private String redes;

    @Column(name = "observacoes")
    private String observacoes;

    public Contato(ContatoCadastroDTO contato) {
        this.nome = contato.getNome();
        this.telefone = contato.getTelefone();
        this.email = contato.getEmail();
        this.avatar = contato.getAvatar();
        this.redes = contato.getRedes();
        this.observacoes = contato.getObservacoes();
    }

    public Contato(ContatoConsultaDTO contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.telefone = contato.getTelefone();
        this.email = contato.getEmail();
        this.avatar = contato.getAvatar();
        this.redes = contato.getRedes();
        this.observacoes = contato.getObservacoes();
    }
}
