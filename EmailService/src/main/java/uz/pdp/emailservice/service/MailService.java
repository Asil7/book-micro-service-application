package uz.pdp.emailservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import uz.pdp.emailservice.dto.ReviewEmailDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;


    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void sendEmailAboutBookReview(ReviewEmailDto reviewEmailDto)
            throws MessagingException {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("bookTitle", reviewEmailDto.getBookTitle());
        templateModel.put("reviewAuthor", reviewEmailDto.getReviewAuthorName());
        templateModel.put("reviewBody", reviewEmailDto.getReviewBody());
        templateModel.put("acceptUrl", reviewEmailDto.getAcceptUrl());
        templateModel.put("rejectUrl", reviewEmailDto.getRejectUrl());

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = "";
        try {
            htmlBody = thymeleafTemplateEngine.process("ReviewEmailTemplate.html", thymeleafContext);
        } catch (Exception e){
            e.printStackTrace();
        }

        sendHtmlMessage(reviewEmailDto.getReceiverEmail(), reviewEmailDto.getSubject(), htmlBody);
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        mailSender.send(message);
    }

}
