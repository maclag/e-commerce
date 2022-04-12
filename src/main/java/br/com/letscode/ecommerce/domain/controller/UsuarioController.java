package br.com.letscode.ecommerce.domain.controller;

import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioRequest;
import br.com.letscode.ecommerce.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("usuarios")
@RestController
public class UsuarioController {

    private UsuarioService usuarioService;

    // CRIAR
    @PostMapping
    public ResponseEntity<UsuarioEntity> create(
            @RequestBody UsuarioRequest request
    ){
        UsuarioEntity usuarioNovo = usuarioService.criar(request);
        return ResponseEntity.created(null).body(usuarioNovo);
    }

    // ALTERAR
    @PutMapping("{id}")
    public ResponseEntity<UsuarioEntity> update(
            @PathVariable("id") Long id,
            @RequestBody UsuarioRequest request
    ){
        UsuarioEntity usuarioAtualizado = usuarioService.alterar(id, request);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<Page<UsuarioEntity>> get (
            @RequestParam(name = "offset", required = false) Integer offset ,
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "nome", required = false) String nome
    ){

        UsuarioFiltrosRequest filtros = new UsuarioFiltrosRequest();
        filtros.setNome(nome);

        Page<UsuarioEntity> usuarios = usuarioService.buscarTodos(offset, limit, filtros);
        return ResponseEntity.ok(usuarios);
    }

    // CONSULTAR
    // - ID
    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioEntity> getById(
            @PathVariable(name = "id") Long id
    ){
        UsuarioEntity usuarioById = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarioById);
    }

    // CONSULTAR
    // - NOME
    @GetMapping("/usuario/{nome}")
    public ResponseEntity<UsuarioEntity> getByName(
            @PathVariable(name = "nome") String nome
    ){
        UsuarioEntity usuarioByNome = usuarioService.buscarPorNome(nome);
        return ResponseEntity.ok(usuarioByNome);
    }

    // REMOVER
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<UsuarioEntity> deleteUserById(
            @PathVariable(name = "id") Long id
    ){
        usuarioService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
