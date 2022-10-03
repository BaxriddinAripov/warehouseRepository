package uz.pdp.appwerehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwerehouse.entity.Warehouse;

import java.util.List;
import java.util.Set;


public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    boolean existsByName(String name);
    List<Warehouse> findAllByIdIn(Set<Integer> ids);

}
