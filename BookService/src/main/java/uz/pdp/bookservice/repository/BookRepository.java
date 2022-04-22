package uz.pdp.bookservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.bookservice.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = true, value = "select b.* from books b where lower(b.title) like lower(concat('%', :search, '%'))")
    List<Book> getAllBooks(Pageable pageable, String search);




}
