package uz.pdp.clients.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.clients.bookReview.BookReviewDto;

import java.util.List;
import java.util.Map;

@FeignClient("book-service")
public interface BookClient {
    @GetMapping("/api/book-service/view/{bookId}")
    Map<String, Object> getBookInfo(@PathVariable Integer bookId);

}
