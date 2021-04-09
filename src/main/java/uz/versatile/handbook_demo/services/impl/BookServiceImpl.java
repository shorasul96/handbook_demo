package uz.versatile.handbook_demo.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.versatile.handbook_demo.dtos.BookDto;
import uz.versatile.handbook_demo.entities.BookEntity;
import uz.versatile.handbook_demo.repositories.BookRepository;
import uz.versatile.handbook_demo.services.BookService;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(BookEntity::dto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean createBook(BookDto dto) {
        if (dto == null) return false;

        BookEntity entity = new BookEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        BookEntity saveAndFlush = bookRepository.saveAndFlush(entity);
        return saveAndFlush.getId() != null;
    }


    @Override
    @Transactional
    public boolean editBook(BookDto dto) {
        return false;
    }

}
