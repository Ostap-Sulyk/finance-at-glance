package com.financeatglance.financeatglance.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// This class is used to wrap the error message in the response body when an exception is thrown from securit filter
@Getter
@Setter
@AllArgsConstructor
public class ErrorMessageWrapper {
    private String errorMessage;

}