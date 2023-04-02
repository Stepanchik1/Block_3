package pro.sky.block3.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.block3.services.FileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/files")
@Tag(name = "Файлы", description = "Операции с файлами рецептов и ингридиентов")
public class FilesController {

private final FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/export/reciepts")
    public ResponseEntity<InputStreamResource> downloadFile1 () throws FileNotFoundException {
        return downloadFile((byte) 1);
        }


    @GetMapping("/export/ingridients")
    public ResponseEntity<InputStreamResource> downloadFile0 () throws FileNotFoundException {
        return downloadFile((byte) 0);
        }


    public ResponseEntity<InputStreamResource> downloadFile (byte classType) throws FileNotFoundException {
        File file = fileService.getFile(classType);
        if (!file.exists()) {return ResponseEntity.noContent().build();}
        else {InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentLength(file.length()).body(resource);
        }
    }
}
