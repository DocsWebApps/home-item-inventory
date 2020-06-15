package com.docswebapps.homeiteminventory.repository;

import com.docswebapps.homeiteminventory.domain.ItemOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOwnerRepository extends JpaRepository<ItemOwnerEntity, Long> {
}
