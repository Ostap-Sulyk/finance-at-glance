package com.financeatglance.financeatglance.service;

import com.financeatglance.financeatglance.entities.Customer;
import com.financeatglance.financeatglance.entities.Dividend;
import com.financeatglance.financeatglance.repository.CustomerRepository;
import com.financeatglance.financeatglance.repository.DividendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DividendServiceImpl implements DividendService {
    private final DividendRepository dividendRepository;
    private final CustomerRepository customerRepository;

    public DividendServiceImpl(DividendRepository dividendRepository, CustomerRepository customerRepository) {
        this.dividendRepository = dividendRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public Dividend saveDividend(Dividend dividend, String customerEmail) {
       Customer customer =  customerRepository.findCustomerByEmail(customerEmail).orElseThrow();
       customer.getDividends().add(dividend);
        return dividendRepository.save(dividend);


    }

    @Override
    public Dividend saveDividend(Dividend dividend) {
        return dividendRepository.save(dividend);
    }

    @Override
    public Optional<Dividend> getDividendById(String id) {
        return dividendRepository.findById(id);
    }

    @Override
    public Dividend updateDividend(Dividend dividend) {
        return dividendRepository.save(dividend);
    }

    @Override
    public void deleteDividend(String id) {
        dividendRepository.deleteById(id);
    }
}
