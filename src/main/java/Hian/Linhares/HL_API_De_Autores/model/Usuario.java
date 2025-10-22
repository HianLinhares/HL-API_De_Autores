package Hian.Linhares.HL_API_De_Autores.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Type(ListArrayType.class)
    @Column(name = "roles", columnDefinition = "varchar[]")
    private List<String> roles;

    /*
    No banco de dados esse atributo foi criado como um varchar e aqui no projeto esta sendo utilizado como
    lista, foi adicionado uma dependência para fazer essa tratativa de adaptação, link da dependência
    https://github.com/vladmihalcea/hypersistence-utils versão 6.6 do hibernate e adicionado a notação @Type para
    sinalizar ao spring o tipo da atributo que deverá ser utilizado
     */



}
