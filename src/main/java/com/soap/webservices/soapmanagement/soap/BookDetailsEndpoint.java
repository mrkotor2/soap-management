package com.soap.webservices.soapmanagement.soap;

import com.soap.webservices.soapmanagement.soap.bean.Book;
import com.soap.webservices.soapmanagement.soap.exception.BookNotFoundException;
import com.soap.webservices.soapmanagement.soap.service.BookDetailsService;
import com.something.books.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class BookDetailsEndpoint {

    @Autowired
    private BookDetailsService service;

    //    http://something.com/books
//    GetBookDetailsRequest
    @PayloadRoot(namespace = "http://something.com/books", localPart = "GetBookDetailsRequest")
    @ResponsePayload
    public GetBookDetailsResponse processBookDetailsRequest(@RequestPayload GetBookDetailsRequest request) {
        Book book = service.findById(request.getId());
        if (book == null) throw new BookNotFoundException("Invalid book id " + request.getId());
        return mapBookDetails(book);
    }

    @PayloadRoot(namespace = "http://something.com/books", localPart = "GetAllBookDetailsRequest")
    @ResponsePayload
    public GetAllBookDetailsResponse processAllBookDetailsRequest(@RequestPayload GetAllBookDetailsRequest request) {
        List<Book> books = service.findAll();
        return mapAllBookDetails(books);

    }

    @PayloadRoot(namespace = "http://something.com/books", localPart = "DeleteBookDetailsRequest")
    @ResponsePayload
    public DeleteBookDetailsResponse processDeleteBookDetailsRequest(@RequestPayload DeleteBookDetailsRequest request) {
        DeleteBookDetailsResponse response = new DeleteBookDetailsResponse();
        Status status = service.deleteById(request.getId());
        response.setStatus(status);
        return response;
    }


    private GetBookDetailsResponse mapBookDetails(Book book) {
        GetBookDetailsResponse response = new GetBookDetailsResponse();
        response.setBookDetails(mapBook(book));
        return response;
    }

    private GetAllBookDetailsResponse mapAllBookDetails(List<Book> books) {
        GetAllBookDetailsResponse response = new GetAllBookDetailsResponse();
        for (Book book : books) {
            BookDetails mapBook = mapBook(book);
            response.getBookDetails().add(mapBook);
        }
        return response;
    }

    private BookDetails mapBook(Book book) {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setId(book.getId());
        bookDetails.setName(book.getName());
        bookDetails.setDescription(book.getDescription());
        return bookDetails;
    }


}
