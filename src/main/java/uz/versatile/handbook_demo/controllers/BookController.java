package uz.versatile.handbook_demo.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.versatile.handbook_demo.dtos.BookDto;
import uz.versatile.handbook_demo.services.BookService;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("all")
     public List<BookDto> getAllDto(){
        return bookService.getAll();
    }

    @PostMapping("create")
    public boolean createBook(@RequestBody BookDto dto) {
        return bookService.createBook(dto);
    }

    @PutMapping("edit")
    public boolean editBook(@RequestBody BookDto dto) {
        return bookService.editBook(dto);
    }


//    public static void main(String[] args) {
//        Random random = new Random();
//        int i = random.nextInt(9999);
//
//        String.format("%04d", i);
//
//    }
}
