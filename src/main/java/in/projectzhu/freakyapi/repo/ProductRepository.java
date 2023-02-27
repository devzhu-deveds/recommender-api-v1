package in.projectzhu.freakyapi.repo;

import in.projectzhu.freakyapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
