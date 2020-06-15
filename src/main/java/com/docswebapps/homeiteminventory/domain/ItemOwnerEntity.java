package com.docswebapps.homeiteminventory.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="owners")
@NoArgsConstructor
@Getter
@Setter
public class ItemOwnerEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Builder
    public ItemOwnerEntity(Long id, Timestamp createdAt, Timestamp lastModifedAt, Long version,String name) {
        super(id, createdAt, lastModifedAt, version);
        this.name = name;
    }
}
