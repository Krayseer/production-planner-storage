package ru.anykeyers.productionplannerstorage.domain.session.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anykeyers.productionplannerstorage.domain.product.Product;

import java.util.Optional;

@Repository
public interface SessionOrderRepository extends JpaRepository<SessionOrder, Long> {

    Optional<SessionOrder> findByProduct(Product product);

}
