package com.financeatglance.financeatglance.service;

import com.financeatglance.financeatglance.entities.Dividend;

import java.util.List;
import java.util.Optional;

public interface DividendService {
    Dividend saveDividend(Dividend dividend, String customerEmail);

    Dividend saveDividend(Dividend dividend);

    Optional<Dividend> getDividendById(String id);
    Dividend updateDividend(Dividend dividend);
    void deleteDividend(String id);
}
