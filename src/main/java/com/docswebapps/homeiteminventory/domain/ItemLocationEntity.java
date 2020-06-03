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
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
public class ItemLocationEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @Builder
    public ItemLocationEntity(Long id, Timestamp createdAt, Timestamp lastModifiedAt, Long version, String name) {
        super(id, createdAt, lastModifiedAt, version);
        this.name = name;
    }
}
