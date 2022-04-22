package uz.pdp.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookservice.common.ApiResponse;
import uz.pdp.bookservice.repository.BookRepository;
import uz.pdp.bookservice.service.BookService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book-service")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/view")
    public HttpEntity<?> getAllBooks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String search) {
        List<Map<String, Object>> bookMap = bookService.getAllBooks(page, size, search);
        return ResponseEntity.status(200).body(bookMap);
    }

    @GetMapping("/view/{bookId}")
    public HttpEntity<?> getBookById(@PathVariable Integer bookId) {
        ApiResponse apiResponse = bookService.getBookById(bookId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse.getObject());
    }

    @GetMapping("/user-collection/{userId}")
    public HttpEntity<?> getUserBookCollection(@PathVariable Integer userId,
                                               @RequestParam(defaultValue = "0") Integer page,
                                               @RequestParam(defaultValue = "10") Integer size) {
        ApiResponse apiResponse = bookService.getUserBookCollection(userId, page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse.getObject());
    }


}
