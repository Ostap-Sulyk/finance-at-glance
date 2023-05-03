package com.financeatglance.financeatglance.entities;

import com.financeatglance.financeatglance.pojos.BaseFinancialEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.financeatglance.financeatglance.constants.ValidationMessages.BLANK_FIELD;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "dividends")
public class Dividend extends BaseFinancialEntity {
    @Id
    private String id;
    @NotBlank(message = BLANK_FIELD)
    private String ticker;
}
