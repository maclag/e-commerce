package br.com.letscode.ecommerce.domain.service;

import br.com.letscode.ecommerce.domain.model.pagination.OffsetLimitPageable;
import br.com.letscode.ecommerce.domain.model.entity.FabricanteEntity;
import br.com.letscode.ecommerce.domain.repository.FabricanteRepository;
import br.com.letscode.ecommerce.domain.repository.ProdutoRepository;
import br.com.letscode.ecommerce.domain.specification.ProdutoSpecifications;
import br.com.letscode.ecommerce.domain.model.entity.ProdutoEntity;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private FabricanteRepository fabricanteRepository;
    private EntityManager entityManager;

    // CADASTRAR
    public ProdutoEntity criar (ProdutoRequest produtoRequest) {
        Optional<FabricanteEntity> fabricanteEntity = fabricanteRepository.findById(produtoRequest.getIdFabricante());
        ProdutoEntity produtoEntity = toEntity(produtoRequest, fabricanteEntity.get());

        return produtoRepository.save(produtoEntity);
    }

    // ALTERAR
    public ProdutoEntity alterar (Long id, ProdutoRequest produtoRequest) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);

        ProdutoEntity produtoEntity = produto.get();
        produtoEntity.setNome(produtoRequest.getNome());
        produtoEntity.setDescricao(produtoRequest.getDescricao());
        produtoEntity.setValor(produtoRequest.getValor());
        produtoEntity.setCodigoBarra(produtoRequest.getCodigoBarra());
        produtoEntity.setPeso(produtoRequest.getPeso());
        produtoEntity.setPesoUnidadeMedida(produtoRequest.getPesoUnidadeMedida());

        return produtoRepository.save(produtoEntity);
    }

    // LISTAR
    public Page<ProdutoEntity> buscarTodos (Integer offset,
                                           Integer limit,
                                           ProdutoFiltrosRequest filtros) {

        Pageable pageable = new OffsetLimitPageable(offset, limit);

        return produtoRepository.findAll(
                where(ProdutoSpecifications.nomeContem(filtros.getNome()))
                 .and(ProdutoSpecifications.valorMenorQue(filtros.getValor())),
                pageable
        );
    }

    // CONSULTAR POR ID
    public ProdutoEntity buscarPorId (Long id) {
        return produtoRepository.findById(id).get();
    }

    // CONSULTAR POR CODIGO_BARRA
    public ProdutoEntity buscarPorCodigoBarra (String codigoBarra){
            return produtoRepository.findByCodigoBarra(codigoBarra);
    }

    // REMOVER
    public ProdutoEntity remover (Long id) {
        ProdutoEntity produtoEntity = buscarPorId(id);
        produtoRepository.delete(produtoEntity);
        return produtoEntity;
    }


    private ProdutoEntity toEntity (ProdutoRequest produtoRequest,
                                    FabricanteEntity fabricante) {
        return new ProdutoEntity(
                produtoRequest.getNome(),
                produtoRequest.getDescricao(),
                produtoRequest.getValor(),
                produtoRequest.getCodigoBarra(),
                fabricante,
                produtoRequest.getPeso(),
                produtoRequest.getPesoUnidadeMedida()
        );
    }
}
