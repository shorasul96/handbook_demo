package uz.versatile.handbook_demo.services;

import uz.versatile.handbook_demo.dtos.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    boolean createBook(BookDto dto);

    boolean editBook(BookDto dto);
}
