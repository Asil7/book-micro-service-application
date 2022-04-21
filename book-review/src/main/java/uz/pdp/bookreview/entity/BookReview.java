package uz.pdp.bookreview.entity;

//Asilbek Fayzullayev 21.04.2022 11:27   

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.bookreview.entity.enums.Status;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "book_reviews")
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(nullable = false)
    private Integer bookId;

    @Column(nullable = false)
    private Integer userId;

    private String reviewBody;

    private Integer rate = 0;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = " timestamp default now() ")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());


}
