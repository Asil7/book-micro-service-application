package uz.pdp.clients.bookReview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class BookReviewDto {
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private String reviewBody;
    private Integer rate;
    private Timestamp createdAt;

}
