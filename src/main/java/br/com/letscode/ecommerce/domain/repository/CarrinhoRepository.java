package br.com.letscode.ecommerce.domain.repository;

import br.com.letscode.ecommerce.domain.model.entity.CarrinhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {

}
