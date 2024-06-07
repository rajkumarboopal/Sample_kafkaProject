package com.appsdeveloper.productKafka.error;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private Date date;
    private String message;
    private String detailMessage;
}
