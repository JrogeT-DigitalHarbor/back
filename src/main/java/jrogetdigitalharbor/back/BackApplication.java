package jrogetdigitalharbor.back;

import jrogetdigitalharbor.back.config.FileStorageProperties;
import jrogetdigitalharbor.back.controllers.PatientController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin
@RestController
@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

    @GetMapping("")
    public String server() {
        return "Backend running...";
    }

    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

    //-----------------------UPLOAD FILE-------------------------

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/api/uploadFile/{id}")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        String fileName = fileStorageService.storeFile(file, id);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/api/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
