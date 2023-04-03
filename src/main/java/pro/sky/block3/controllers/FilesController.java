package pro.sky.block3.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.block3.services.FileService;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@Tag(name = "Файлы", description = "Операции с файлами рецептов и ингридиентов")
public class FilesController {

    private final FileService fileService;

    private final RecieptsServices recieptsServices;

    private final IngridientServices ingridientServices;

    public FilesController(FileService fileService, RecieptsServices recieptsServices, IngridientServices ingridientServices) {
        this.fileService = fileService;
        this.recieptsServices = recieptsServices;
        this.ingridientServices = ingridientServices;
        System.out.println(recieptsServices.toTextMap());
        System.out.println(ingridientServices.toTextMap());
    }

    @GetMapping("/export/reciepts")
    public ResponseEntity<InputStreamResource> downloadFile1() throws FileNotFoundException {
        return downloadFile((byte) 1);
    }


    @GetMapping("/export/ingridients")
    public ResponseEntity<InputStreamResource> downloadFile0() throws FileNotFoundException {
        return downloadFile((byte) 0);
    }


    public ResponseEntity<InputStreamResource> downloadFile(byte classType) throws FileNotFoundException {
        File file = fileService.getNewFile(classType);
        if (!file.exists()) {
            return ResponseEntity.noContent().build();
        } else {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filemame =\"lululu.json\"").contentLength(file.length()).body(resource); //не сработало название файла
        }
    }

  //  @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> importFile(byte classType, MultipartFile file) {
        fileService.cleanFile(classType);
        File datafile = fileService.getNewFile(classType);

        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(datafile));) {
            byte[] buffer = new byte[512];
            while (bis.read(buffer) > 0) {
                bos.write(buffer);
                // IOUtils.copy(file.getInputStream(), new FileOutputStream(datafile)); // оставил на потом
            }
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/import/reciepts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> importFileOfReciepts(@RequestParam MultipartFile file) {
return importFile((byte) 1, file);
    }

    @PostMapping(value = "/import/ingridients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> importFileOfIngridients(@RequestParam MultipartFile file) {
        return importFile((byte) 0, file);
    }

    @GetMapping("/text/reciepts")
    public ResponseEntity<Object> getTextFileOfReciepts () {
try {
    Path path = fileService.writeToFileReciepts(recieptsServices.toTextMap());
    if (Files.size(path) == 0) {return ResponseEntity.noContent().build();}
    InputStreamResource resource = new InputStreamResource(new FileInputStream (path.toFile()));
    return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).contentLength(Files.size(path)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filemame =\"ahahaha.text\"").body(resource);
} catch (IOException e) {
    e.printStackTrace();
    return ResponseEntity.internalServerError().body(e.toString());
}
    }
    @GetMapping("/text/ingridients")
    public ResponseEntity<Object> getTextFileOfIngridients () {
        try {
            Path path = fileService.writeToFileIngridients(ingridientServices.toTextMap());
            if (Files.size(path) == 0) {return ResponseEntity.noContent().build();}
            InputStreamResource resource = new InputStreamResource(new FileInputStream (path.toFile()));
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).contentLength(Files.size(path)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filemame =\"hohohoho.text\"").body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}