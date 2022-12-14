package uz.pdp.appwerehouse.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwerehouse.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends AbsEntity {

    @Column(unique = true, nullable = false)
    private String phoneNumber;
}
