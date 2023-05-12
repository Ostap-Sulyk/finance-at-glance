package com.financeatglance.financeatglance.entities;

import com.financeatglance.financeatglance.pojos.BaseFinancialEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.financeatglance.financeatglance.constants.ValidationMessages.BLANK_FIELD;

@Data
@EqualsAndHashCode(callSuper = true)
//@Document(collection = "dividends")
@Entity
public class Dividend extends BaseFinancialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank(message = BLANK_FIELD)
    private String ticker;


    private Customer customer;

}
