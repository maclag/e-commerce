package br.com.letscode.ecommerce.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity(name = "usuario")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "DATA_CRIACAO")
    private ZonedDateTime dataCriacao;

    @Column(name = "DATA_ALTERACAO")
    private ZonedDateTime dataAlteracao;
}
