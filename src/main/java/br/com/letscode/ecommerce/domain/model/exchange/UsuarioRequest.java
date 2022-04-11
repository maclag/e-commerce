package br.com.letscode.ecommerce.domain.model.exchange;

import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsuarioRequest {

    private String nome;
    private LocalDate dataNascimento;

    public UsuarioEntity toEntity() {
        return UsuarioEntity.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .dataCriacao(ZonedDateTime.now())
                .dataAlteracao(ZonedDateTime.now())
                .build();
    }
}
