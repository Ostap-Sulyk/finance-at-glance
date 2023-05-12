package com.financeatglance.financeatglance.repository;

import com.financeatglance.financeatglance.entities.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DividendRepository extends JpaRepository<Dividend, String> {
}
