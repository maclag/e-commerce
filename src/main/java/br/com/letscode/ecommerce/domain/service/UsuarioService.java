package br.com.letscode.ecommerce.domain.service;

import br.com.letscode.ecommerce.domain.model.entity.UsuarioEntity;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioFiltrosRequest;
import br.com.letscode.ecommerce.domain.model.exchange.UsuarioRequest;
import br.com.letscode.ecommerce.domain.model.pagination.OffsetLimitPageable;
import br.com.letscode.ecommerce.domain.repository.UsuarioRepository;
import br.com.letscode.ecommerce.domain.specification.UsuarioSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    // CRIAR
    public UsuarioEntity criar (UsuarioRequest usuarioRequest) {
        return usuarioRepository.save(usuarioRequest.toEntity());
    }

    // ALTERAR
    public UsuarioEntity alterar (Long id, UsuarioRequest usuarioRequest) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);

        UsuarioEntity usuarioEntity = usuario.get();
        usuarioEntity.setNome(usuarioRequest.getNome());
        usuarioEntity.setDataNascimento(usuarioRequest.getDataNascimento());

        return usuarioRepository.save(usuarioEntity);
    }

    // LISTAR
    public Page<UsuarioEntity> buscarTodos (Integer offset,
                                            Integer limit,
                                            UsuarioFiltrosRequest filtros) {
        Pageable pageable = new OffsetLimitPageable(offset, limit);

        return usuarioRepository.findAll(
                where(UsuarioSpecifications.nomeContem(filtros.getNome())),
                pageable
        );
    }

    // CONSULTAR POR ID
    public UsuarioEntity buscarPorId (Long id) {
        return usuarioRepository.findById(id).get();
    }

    // CONSULTAR POR NOME
    public UsuarioEntity buscarPorNome (String nome) {
        return usuarioRepository.findByName(nome);
    }

    // REMOVER
    public void remover (Long id) {
        usuarioRepository.deleteById(id);
    }
}
