package uz.versatile.handbook_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.versatile.handbook_demo.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, String> {

}
