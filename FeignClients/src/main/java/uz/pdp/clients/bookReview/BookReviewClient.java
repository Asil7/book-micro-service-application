package uz.pdp.clients.bookReview;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.clients.continent.ContinentDto;

import java.util.List;

@FeignClient("book-review-service")
public interface BookReviewClient {
    @GetMapping("/api/book-review-service/{bookId}")
    List<BookReviewDto> getBookReviews(@PathVariable Integer bookId);

    @GetMapping("/api/book-review-service/average-rating/{bookId}")
    Double getBookAverageRating(@PathVariable Integer bookId);

}
