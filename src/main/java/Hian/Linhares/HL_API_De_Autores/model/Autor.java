package Hian.Linhares.HL_API_De_Autores.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "autor")
@Getter
@Setter
public class Autor {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNacimento;

    @Column(name = "nacionalidade", nullable = false, length = 50)
    private String nacionalidade;

    @Deprecated
    public Autor() {
        //para uso do framweork é obrigatório o uso de um construtor
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNacimento=" + dataNacimento +
                ", nacionalidade='" + nacionalidade + '\'' +
                '}';
    }
}
