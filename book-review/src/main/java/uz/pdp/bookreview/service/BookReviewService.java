package uz.pdp.bookreview.service;

//Asilbek Fayzullayev 21.04.2022 11:48   

import javafx.scene.control.IndexRange;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.bookreview.common.ApiResponse;
import uz.pdp.bookreview.dto.ReviewEmailDto;
import uz.pdp.bookreview.entity.BookReview;
import uz.pdp.bookreview.entity.enums.Status;
import uz.pdp.bookreview.repository.BookReviewRepository;
import uz.pdp.clients.book.BookClient;
import uz.pdp.clients.bookReview.BookReviewDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookReviewService {
    private final BookReviewRepository bookReviewRepository;
    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate;
    private final BookClient bookClient;

    @Value("${spring.rabbitmq.exchange}")
    String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    String routingKey;

    public ResponseEntity<?> getAllBookReview() {
        List<BookReview> all = bookReviewRepository.findAll();
        return ResponseEntity.ok(all);
    }

    public List<BookReviewDto> getBookReviewByBookId(Integer bookId) {
        List<BookReview> byBookId = bookReviewRepository.findAllByBookIdAndStatus(bookId, Status.ACCEPTED);

        List<BookReviewDto> bookReviewDtoList = new ArrayList<>();
        for (BookReview bookReview : byBookId) {
            BookReviewDto bookReviewDto = new BookReviewDto(bookReview.getId(), bookReview.getBookId(), bookReview.getUserId(),
                    bookReview.getReviewBody(), bookReview.getRate(), bookReview.getCreatedAt());
            bookReviewDtoList.add(bookReviewDto);
        }
        return bookReviewDtoList;
    }

    public ApiResponse saveBookReview(BookReview bookReview) {
        bookReview.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        bookReview.setStatus(Status.NEW);
        bookReview.setUserId(1);
        BookReview savedBookReview = bookReviewRepository.save(bookReview);

//        String bookUrl = "http://BOOK-SERVICE/api/book-service/view/" + bookReview.getBookId();
//        Map<String, Object> bookMap = restTemplate.getForObject(bookUrl, Map.class);
        Map<String, Object> bookMap = bookClient.getBookInfo(bookReview.getBookId());


        ReviewEmailDto reviewEmailDto = new ReviewEmailDto();
        reviewEmailDto.setBookTitle((String) bookMap.get("bookTitle"));
        reviewEmailDto.setReviewBody(bookReview.getReviewBody());
        reviewEmailDto.setReviewAuthorName("Nodirbek Nurqulov");
        reviewEmailDto.setReceiverEmail("abdulaziz2000go@gmail.com");
        reviewEmailDto.setAcceptUrl("http://localhost:8082/api/book-review-service/review?isAccepted=true&reviewId=" + savedBookReview.getId());
        reviewEmailDto.setRejectUrl("http://localhost:8082/api/book-review-service/review?isAccepted=false&reviewId=" + savedBookReview.getId());
        reviewEmailDto.setSubject("New book review for your Book");
        sendEmailToBookCreator(reviewEmailDto);

        return new ApiResponse("Review successfully saved", true, bookReview);
    }

    public void sendEmailToBookCreator(ReviewEmailDto reviewEmailDto){
        rabbitTemplate.convertAndSend(exchange, routingKey, reviewEmailDto);
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

    public ApiResponse setReviewStatus(boolean isAccepted, Integer reviewId) {
        Optional<BookReview> optionalBookReview = bookReviewRepository.findById(reviewId);
        if (!optionalBookReview.isPresent()) return new ApiResponse("Not found", false);
        BookReview bookReview = optionalBookReview.get();

        if (isAccepted){
            bookReview.setStatus(Status.ACCEPTED);
        } else {
            bookReview.setStatus(Status.REJECTED);
        }

        bookReviewRepository.save(bookReview);
        return new ApiResponse(isAccepted ? "Review is Accepted" : "Review is rejected", true);
    }
}
