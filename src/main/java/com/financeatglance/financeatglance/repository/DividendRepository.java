package com.financeatglance.financeatglance.repository;

import com.financeatglance.financeatglance.entities.Dividend;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DividendRepository extends MongoRepository<Dividend, String> {
}
