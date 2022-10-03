package uz.pdp.appwerehouse.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwerehouse.entity.Attachment;
import uz.pdp.appwerehouse.entity.AttachmentContent;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.repository.AttachmentContentRepository;
import uz.pdp.appwerehouse.repository.AttachmentRepository;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    final
    AttachmentRepository attachmentRepository;
    final
    AttachmentContentRepository attachmentContentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    // CREAT
    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        //
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        //
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("Fail saqlandi", true, savedAttachment.getId());
    }

    // GET ALL
    public List<Attachment> allAttachment() {
        return attachmentRepository.findAll();
    }

    // GET BY ID
    public void getFile(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();

            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
            if (optionalAttachmentContent.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }
        }
    }

    // DELETE BY UI
    public Result deleteFile(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Bunday fayl mavjud emas", false);
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
        if (!optionalAttachmentContent.isPresent())
            return new Result("Bunday fayl mavjud emas", false);
        attachmentRepository.deleteById(id);
        attachmentContentRepository.deleteById(id);
        return new Result("Fayl muvaffaqiyatli o'chirildi", true);
    }

    // UPDATE BY ID
    @SneakyThrows
    public Result editFile(Integer id, MultipartHttpServletRequest request){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Bunday fayl mavjud emas", false);
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
        if (!optionalAttachmentContent.isPresent())
            return new Result("Bunday fayl mavjud emas", false);
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        //
        Attachment attachment = optionalAttachment.get();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment saved = attachmentRepository.save(attachment);
        //
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(saved);
        attachmentContentRepository.save(attachmentContent);
        return new Result("Fayl muvaffaqiyatli saqlandi", true, saved.getId());
    }
}