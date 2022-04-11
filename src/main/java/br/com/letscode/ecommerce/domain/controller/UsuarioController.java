package br.com.letscode.ecommerce.domain.controller;

import br.com.letscode.ecommerce.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("usuarios")
@RestController
public class UsuarioController {

    private UsuarioService usuarioService;


}
