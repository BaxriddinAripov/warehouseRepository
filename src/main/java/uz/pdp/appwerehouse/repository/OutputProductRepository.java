package uz.pdp.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwerehouse.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {

    boolean existsByProduct_IdAndOutput_Id(Integer product_id, Integer output_id);
}
