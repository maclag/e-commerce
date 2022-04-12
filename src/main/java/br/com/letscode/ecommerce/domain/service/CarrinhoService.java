package br.com.letscode.ecommerce.domain.service;

import br.com.letscode.ecommerce.domain.model.entity.CarrinhoEntity;
import br.com.letscode.ecommerce.domain.repository.CarrinhoRepository;

import java.util.List;

public class CarrinhoService {

    private CarrinhoRepository carrinhoRepository;

    // CRIAR
    public CarrinhoEntity criar (CarrinhoEntity carrinho) {
        return carrinhoRepository.save(carrinho);
    }

    // ALTERAR
    public CarrinhoEntity alterar (Long id, CarrinhoEntity carrinho) {
        CarrinhoEntity carrinhoEntity = buscarPorId(id);
        return carrinhoRepository.save(carrinhoEntity);
    }

    // LISTAR
    public List<CarrinhoEntity> buscarTodos() {
        return carrinhoRepository.findAll();
    }

    // CONSULTAR POR ID
    public CarrinhoEntity buscarPorId (Long id) {
        return carrinhoRepository.findById(id).orElse(null);
    }

    // REMOVER
    public void remover (Long id) {
        carrinhoRepository.deleteById(id);
    }
}
