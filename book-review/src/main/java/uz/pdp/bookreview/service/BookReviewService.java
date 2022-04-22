package uz.pdp.bookreview.service;

//Asilbek Fayzullayev 21.04.2022 11:48   

import javafx.scene.control.IndexRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.bookreview.entity.BookReview;
import uz.pdp.bookreview.entity.enums.Status;
import uz.pdp.bookreview.repository.BookReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookReviewService {

    @Autowired
    BookReviewRepository bookReviewRepository;

    public ResponseEntity<?> getAllBookReview() {
        List<BookReview> all = bookReviewRepository.findAll();
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> getBookReviewByBookId(Integer bookId) {
        List<BookReview> byBookId = bookReviewRepository.findAllByBookIdAndStatus(bookId, Status.ACCEPTED);
        if (byBookId.size() != 0) {
            return new ResponseEntity<>(byBookId, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> saveBookReview(BookReview bookReview) {
        bookReviewRepository.save(bookReview);
        return new ResponseEntity<>("Successfully saved", HttpStatus.CREATED);
    }

    public ResponseEntity<?> delete(Integer id) {
        try {
            bookReviewRepository.deleteById(id);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getAverageRate(Integer bookId) {
        Double average = bookReviewRepository.getAverage(bookId);
        return ResponseEntity.ok(average);

    }
}
