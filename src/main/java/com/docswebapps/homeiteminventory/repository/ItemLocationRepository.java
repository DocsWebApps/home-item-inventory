package com.docswebapps.homeiteminventory.repository;

import com.docswebapps.homeiteminventory.domain.ItemLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemLocationRepository extends JpaRepository<ItemLocationEntity, Long> {
}
