package uz.versatile.handbook_demo.services;

import org.springframework.data.domain.Page;
import uz.versatile.handbook_demo.dtos.BookDto;
import uz.versatile.handbook_demo.dtos.queries.BookQuery;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    boolean createBook(BookDto dto);

    boolean editBook(BookDto dto);

    Page<BookQuery> getAllWithPageable(String search, int page, int size);

    void exportBooks(HttpServletResponse response) throws IOException;
}
