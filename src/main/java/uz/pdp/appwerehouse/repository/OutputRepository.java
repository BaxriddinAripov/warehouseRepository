package uz.pdp.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwerehouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output, Integer> {

    boolean existsByFactureNameAndWarehouse_Id(String factureName, Integer warehouse_id);
}
