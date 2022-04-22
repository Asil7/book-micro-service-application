package uz.pdp.apiattachment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apiattachment.entity.Attachment;
import uz.pdp.apiattachment.entity.AttachmentContent;

public interface AttachmentContentRepo extends JpaRepository<AttachmentContent, Long> {

    AttachmentContent findByAttachmentId(Long attachmentId);
}
