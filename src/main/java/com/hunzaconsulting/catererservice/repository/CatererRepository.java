package com.hunzaconsulting.catererservice.repository;

import com.hunzaconsulting.catererservice.domain.Caterer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CatererRepository extends MongoRepository<Caterer, String> {

    Optional<Caterer> findCatererById(String id);
    Page<Caterer> findCaterersByName(String name, Pageable pageable);
    Page<Caterer> findCaterersByAddressCityName(String cityName, Pageable pageable);

}
