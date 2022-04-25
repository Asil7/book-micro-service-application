package uz.pdp.bookreview.controller;

//Asilbek Fayzullayev 21.04.2022 12:05   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookreview.common.ApiResponse;
import uz.pdp.bookreview.entity.BookReview;
import uz.pdp.bookreview.service.BookReviewService;
import uz.pdp.clients.bookReview.BookReviewDto;

import java.util.List;

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
    public List<BookReviewDto> getBookReviewsByBookId(@PathVariable Integer bookId) {
        return bookReviewService.getBookReviewByBookId(bookId);
    }

    @PostMapping
    public ResponseEntity<?> saveBookReview(@RequestBody BookReview bookReview) {
        ApiResponse apiResponse = bookReviewService.saveBookReview(bookReview);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse.getObject());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return bookReviewService.delete(id);
    }

    @GetMapping("/average-rating/{bookId}")
    public ResponseEntity<?> getAverageRate(@PathVariable Integer bookId) {
        return bookReviewService.getAverageRate(bookId);
    }

    @GetMapping("/review")
    public ResponseEntity<?> setReviewStatus(@RequestParam(name = "isAccepted") boolean isAccepted,
                                            @RequestParam(name = "reviewId") Integer reviewId) {
        ApiResponse apiResponse = bookReviewService.setReviewStatus(isAccepted, reviewId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

}
