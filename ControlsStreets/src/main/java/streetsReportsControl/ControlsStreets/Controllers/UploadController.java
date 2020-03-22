package streetsReportsControl.ControlsStreets.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping({"/upload"})
public class UploadController {

    private final String FILE_URL="static/images/";


    @GetMapping(path = {"/{filaname}"}, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity getImage(@PathVariable String filaname) {
        try {
            ClassPathResource imgFile = new ClassPathResource(FILE_URL + filaname);
            byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity
               .badRequest().build();

    }

    @PostMapping
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //ClassPathResource imgFile = new ClassPathResource(FILE_URL + fileName);
        Path path = Paths.get("src","main","resources","static","images",fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("upload/")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }


}
