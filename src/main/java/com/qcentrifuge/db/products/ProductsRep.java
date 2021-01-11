package com.qcentrifuge.db.products;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductsRep extends CrudRepository<Products, Long> {

    Optional<Products> findById(Long id);

}
