package com.financeatglance.financeatglance.pojos;

import com.financeatglance.financeatglance.constants.ValidationMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public abstract class BaseFinancialEntity {
    @NotNull(message = ValidationMessages.BLANK_FIELD)
    @Positive(message = ValidationMessages.POSITIVE)
    private BigDecimal amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
