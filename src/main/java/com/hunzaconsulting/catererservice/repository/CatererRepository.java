package com.hunzaconsulting.catererservice.repository;

import com.hunzaconsulting.catererservice.domain.Caterer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CatererRepository extends MongoRepository<Caterer, String> {

    Caterer findCatererByName(String name);
}
