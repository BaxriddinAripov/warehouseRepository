package uz.pdp.appwerehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.appwerehouse.entity.template.AbsEntity;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Warehouse extends AbsEntity {
}
