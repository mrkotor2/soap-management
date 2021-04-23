package com.soap.webservices.soapmanagement.soap.service;

import com.soap.webservices.soapmanagement.soap.bean.Book;
import com.something.books.Status;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class BookDetailsService {
//    public enum Status {
//        SUCCESS, FAILURE;
//    }
    private static List<Book> books = new ArrayList<>();

    static {
        Book book1 = new Book(1, "Book1", "des1");
        books.add(book1);
        Book book2 = new Book(2, "Book2", "des2");
        books.add(book2);
        Book book3 = new Book(3, "Book3", "des3");
        books.add(book3);
    }

    public Book findById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public List<Book> findAll() {
        return books;
    }

    public Status deleteById(int id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == id) {
                iterator.remove();
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
}
