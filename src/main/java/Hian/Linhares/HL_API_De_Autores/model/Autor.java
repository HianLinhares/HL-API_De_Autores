package Hian.Linhares.HL_API_De_Autores.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "autor")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
//notação que vai ficar acompanhando todas as operações da classe


public class Autor {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;


    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;


    @Column(name = "id_usuario")
    private UUID idUsuario;




    @Deprecated
    public Autor() {
        //para uso do framweork é obrigatório o uso de um construtor
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNacimento=" + dataNascimento +
                ", nacionalidade='" + nacionalidade + '\'' +
                '}';
    }
}
