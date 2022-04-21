package uz.pdp.bookreview.controller;

//Asilbek Fayzullayev 21.04.2022 12:05   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookreview.entity.BookReview;
import uz.pdp.bookreview.service.BookReviewService;

@RestController
@RequestMapping("/api/book-review-service")
public class BookReviewController {

    @Autowired
    BookReviewService bookReviewService;

    @GetMapping
    public ResponseEntity<?> getAllBookReview() {
        return bookReviewService.getAllBookReview();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookReviewsByBookId(@PathVariable Integer bookId) {
        return bookReviewService.getBookReviewByBookId(bookId);
    }

    @PostMapping
    public ResponseEntity<?> saveBookReview(@RequestBody BookReview bookReview) {
        return bookReviewService.saveBookReview(bookReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return bookReviewService.delete(id);
    }

    @GetMapping("/average-rating/{bookId}")
    public ResponseEntity<?> getAverageRate(@PathVariable Integer bookId){
        return bookReviewService.getAverageRate(bookId);
    }

}
