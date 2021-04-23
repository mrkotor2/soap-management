package com.soap.webservices.soapmanagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
//@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://something.com/books001_BOOK_NOT_FOUND}001_COURSE_NOT_FOUND")
public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4003674127327566745L;

    public BookNotFoundException(String message) {
        super(message);
    }

}
