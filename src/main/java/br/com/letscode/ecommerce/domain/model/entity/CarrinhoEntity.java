package br.com.letscode.ecommerce.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "carrinho")
public class CarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private ZonedDateTime dataCriacao;

    @Column
    private ZonedDateTime dataAlteracao;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProdutoEntity> produtos = new ArrayList<>();
}
