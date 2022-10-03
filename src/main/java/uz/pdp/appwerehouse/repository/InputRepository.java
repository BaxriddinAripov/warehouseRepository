package uz.pdp.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwerehouse.entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {

    boolean existsByFactureNameAndWarehouse_IdAndSupplier_Id(String factureName, Integer warehouse_id, Integer supplier_id);
}
