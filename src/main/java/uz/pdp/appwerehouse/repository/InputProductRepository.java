package uz.pdp.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwerehouse.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

    boolean existsByProduct_IdAndInput_Id(Integer product_id, Integer input_id);
}
