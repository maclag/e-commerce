package br.com.letscode.ecommerce.domain.controller;

import br.com.letscode.ecommerce.domain.model.entity.CarrinhoEntity;
import br.com.letscode.ecommerce.domain.service.CarrinhoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("carrinho")
@RestController
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    // CRIAR
    @PostMapping()
    public ResponseEntity<CarrinhoEntity> criar(@RequestBody CarrinhoEntity carrinho) {
        return ResponseEntity.ok(carrinhoService.criar(carrinho));
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<CarrinhoEntity> atualizar(@PathVariable Long id, @RequestBody CarrinhoEntity carrinho) {
        return ResponseEntity.ok(carrinhoService.alterar(id, carrinho));
    }

    // LISTAR
    @GetMapping()
    public ResponseEntity<List<CarrinhoEntity>> get() {
        return ResponseEntity.ok(carrinhoService.buscarTodos());
    }

    // CONSULTAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(carrinhoService.buscarPorId(id));
    }

    // REMOVER
    @DeleteMapping("/{id}")
    public ResponseEntity<CarrinhoEntity> remover(@PathVariable Long id) {
        carrinhoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
