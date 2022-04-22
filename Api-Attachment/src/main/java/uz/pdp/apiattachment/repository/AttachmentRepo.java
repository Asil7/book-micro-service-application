package uz.pdp.apiattachment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apiattachment.entity.Attachment;

public interface AttachmentRepo extends JpaRepository<Attachment, Long> {
}
