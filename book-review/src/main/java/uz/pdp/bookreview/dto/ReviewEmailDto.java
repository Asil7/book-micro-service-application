package uz.pdp.bookreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEmailDto {
    private String receiverEmail;
    private String reviewAuthorName;
    private String subject;
    private String bookTitle;
    private String reviewBody;
    private String acceptUrl;
    private String rejectUrl;

}
