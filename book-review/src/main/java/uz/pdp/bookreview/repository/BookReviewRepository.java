package uz.pdp.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.bookreview.entity.BookReview;

import java.util.List;
import java.util.Optional;

public interface BookReviewRepository extends JpaRepository<BookReview, Integer> {

    @Query(nativeQuery = true,value = "select b.id,\n" +
            "       b.book_id,\n" +
            "       b.user_id,\n" +
            "       b.review_body,\n" +
            "       b.rate,\n" +
            "       b.created_at, \n" +
            "       b.status\n" +
            "from book_reviews b\n" +
            "where book_id = :bookId\n" +
            "and b.status = 'ACCEPTED'")
    List<BookReview> getByBookId(Integer bookId);


    @Query(nativeQuery = true,value = "select\n" +
            "       avg(b.rate) as rate\n" +
            "from book_reviews b\n" +
            "where book_id = :bookId")
    Double getAverage(Integer bookId);
}
