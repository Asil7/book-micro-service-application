package uz.pdp.bookreview.entity;

//Asilbek Fayzullayev 21.04.2022 11:27   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.bookreview.entity.enums.Status;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "book_reviews")
public class BookReview {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer bookId;

    @Column(nullable = false)
    private Integer userId;

    private String reviewBody;

    private Integer rate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;


}
