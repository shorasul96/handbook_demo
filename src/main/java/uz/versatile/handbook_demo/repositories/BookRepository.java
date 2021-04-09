package uz.versatile.handbook_demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.versatile.handbook_demo.dtos.queries.BookQuery;
import uz.versatile.handbook_demo.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query(value = "SELECT id    AS id, " +
            "       title        AS title, " +
            "       created_date AS createdDate, " +
            "       parent_id    AS parentId, " +
            "       is_active    AS isActive " +
            "FROM books  WHERE title ILIKE ('%'||:search||'%') ORDER BY id desc",
            countQuery = "SELECT COUNT(*) FROM books WHERE title ILIKE ('%'||:search||'%')",
            nativeQuery = true)
    Page<BookQuery> findAllWithPaginationAndBookList(@Param("search") String search, Pageable pageable);

}
