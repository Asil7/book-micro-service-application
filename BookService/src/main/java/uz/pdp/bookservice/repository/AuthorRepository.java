package uz.pdp.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bookservice.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
