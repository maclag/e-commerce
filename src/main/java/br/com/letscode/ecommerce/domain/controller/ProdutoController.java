package br.com.letscode.ecommerce.domain.controller;

import br.com.letscode.ecommerce.domain.model.exchange.ProdutoFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.ProdutoRequest;
import br.com.letscode.ecommerce.domain.model.entity.ProdutoEntity;
import br.com.letscode.ecommerce.domain.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@AllArgsConstructor
@RequestMapping("produtos")
@RestController
public class ProdutoController {

    private ProdutoService produtoService;

    // CADASTRAR
    @PostMapping
    public ResponseEntity<ProdutoEntity> create(
             @RequestBody ProdutoRequest request
    ){
        ProdutoEntity produto = produtoService.criar(request);
        return ResponseEntity.created(null).body(produto);
    }

    // ALTERAR
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> update(
            @PathVariable(name = "id") Long id,
            @RequestBody ProdutoRequest request
    ){
        ProdutoEntity produto = produtoService.alterar(id, request);
        return ResponseEntity.ok(produto);
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<Page<ProdutoEntity>> get(
            @RequestParam(name = "offset", required = false) Integer offset ,
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "valor_maximo", required = false) BigDecimal valorMaximo
    ){

        ProdutoFiltrosRequest filtros = new ProdutoFiltrosRequest();
        filtros.setNome(nome);
        filtros.setValor(valorMaximo);

        Page<ProdutoEntity> produtos = produtoService.buscarTodos(offset, limit, filtros);
        return ResponseEntity.ok(produtos);
    }

    // CONSULTAR
    // - CODIGO_BARRA
    @GetMapping("/codigo/{codigoBarra}")
    public ResponseEntity<ProdutoEntity> getByCodigoBarra(
            @PathVariable(name = "codigoBarra") String codigoBarra
    ){
        ProdutoEntity produto = produtoService.buscarPorCodigoBarra(codigoBarra);
        return ResponseEntity.ok(produto);
    }

    // CONSULTAR
    // - ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> getById(
            @PathVariable(name="id") Long id
    ){
        ProdutoEntity produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    // REMOVER
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoEntity> delete(
            @PathVariable(name = "id") Long id
    ){
        ProdutoEntity produto = produtoService.remover(id);
        return ResponseEntity.ok(produto);
    }

}
