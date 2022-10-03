package uz.pdp.appwerehouse.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwerehouse.entity.Attachment;
import uz.pdp.appwerehouse.payload.Result;
import uz.pdp.appwerehouse.service.AttachmentService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    final
    AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    // CREAT
    @PostMapping("/uploadFile")
    public Result uploadFile(MultipartHttpServletRequest request){
        return attachmentService.uploadFile(request);
    }

    // GET ALL
    @GetMapping("/allAttachment")
    public List<Attachment> allAttachment(){
        return attachmentService.allAttachment();
    }

    // GET BY ID
    @GetMapping("/downloadFile/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFile(id, response);
    }

    // DELETE BY ID
    @DeleteMapping("/deleteFile/{id}")
    public Result deleteFile(@PathVariable Integer id){
        return attachmentService.deleteFile(id);
    }

    // UPDATE BY ID
    @PutMapping("/editFile/{id}")
    public Result editFile(@PathVariable Integer id, MultipartHttpServletRequest request){
        return attachmentService.editFile(id, request);
    }
}
