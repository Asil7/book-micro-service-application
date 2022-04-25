package uz.pdp.bookservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.bookservice.entity.Book;
import uz.pdp.bookservice.entity.BookCollection;

import java.util.List;

public interface BookCollectionRepository extends JpaRepository<BookCollection, Integer> {

    @Query(nativeQuery = true, value = "select b.id as id,\n" +
            "       b.title as title,\n" +
            "       b.description as description,\n" +
            "       b.user_id as userId,\n" +
            "       b.attachment_id as attachmentId\n" +
            "from book_collection bc\n" +
            "         join books b on b.id = bc.book_id\n" +
            "where bc.user_id =:userId limit :size offset :page")
    List<Book> getUserBookCollection(Integer userId, Integer size, Integer page);

    List<BookCollection> findAllByUserId(Pageable pageable, Integer userId);

    boolean existsByUserIdAndBookId(Integer userId, Integer book_id);

}
