package uz.versatile.handbook_demo.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.versatile.handbook_demo.dtos.BookDto;
import uz.versatile.handbook_demo.dtos.queries.BookQuery;
import uz.versatile.handbook_demo.services.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("all")
     public List<BookDto> getAllDto(){
        return bookService.getAll();
    }

    @GetMapping("pageable")
    public Page<BookQuery> getAllVacancies(
            @RequestParam(value = "search", defaultValue = "", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        return bookService.getAllWithPageable(search, page, size);
    }

    @PostMapping("create")
    public boolean createBook(@RequestBody BookDto dto) {
        return bookService.createBook(dto);
    }

    @PutMapping("edit")
    public boolean editBook(@RequestBody BookDto dto) {
        return bookService.editBook(dto);
    }
}
