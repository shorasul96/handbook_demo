package uz.versatile.handbook_demo.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.versatile.handbook_demo.dtos.BookDto;
import uz.versatile.handbook_demo.dtos.queries.BookQuery;
import uz.versatile.handbook_demo.entities.BookEntity;
import uz.versatile.handbook_demo.repositories.BookRepository;
import uz.versatile.handbook_demo.services.BookService;

import java.util.Arrays;
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
    @Transactional(readOnly = true)
    public Page<BookQuery> getAllWithPageable(String search, int page, int size) {
        page = Math.max(page, 1);
        if (search == null || search.trim().isEmpty()) search = "";
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        return bookRepository.findAllWithPaginationAndBookList(search, pageable);
    }

    @Override
    @Transactional
    public boolean createBook(BookDto dto) {
        if (dto == null) return false;

        BookEntity entity = new BookEntity();
        BeanUtils.copyProperties(dto, entity, "id", "isActive");
        setParent(dto, entity);
        BookEntity saveAndFlush = bookRepository.saveAndFlush(entity);
        return saveAndFlush.getId() != null;
    }


    @Override
    @Transactional
    public boolean editBook(BookDto dto) {
        if (dto == null || dto.getId() == null) return false;
        BookEntity entity = bookRepository.findById(Long.parseLong(dto.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Not found book with given ID: " + dto.getId()));

        entity.setTitle(dto.getTitle());
        setParent(dto, entity);

        BookEntity saveAndFlush = bookRepository.saveAndFlush(entity);
        return saveAndFlush.getId().toString().equals(dto.getId());
    }


    private void setParent(BookDto dto, BookEntity entity) {
        if (dto.getParentId() != null) {
            List<String> strings = Arrays.stream(dto.getParentId().split("\\.")).collect(Collectors.toList());
            for (String s : strings)
                bookRepository.findById(Long.parseLong(s))
                        .orElseThrow(() -> new IllegalArgumentException("Not found book with given ID: " + s));
            entity.setParentId(dto.getParentId());
        } else {
            entity.setParentId(null);
        }
    }

}
