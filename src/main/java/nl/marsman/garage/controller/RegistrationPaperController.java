package nl.marsman.garage.controller;

import nl.marsman.garage.model.RegistrationPaper;
import nl.marsman.garage.model.FileUploadResponse;
import nl.marsman.garage.repository.RegistrationPaperRepository;
import nl.marsman.garage.service.RegistrationPaperService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@CrossOrigin
@RestController
public class RegistrationPaperController {

    private RegistrationPaperRepository registrationPaperRepository;
    private RegistrationPaperService databaseService;

    public RegistrationPaperController(RegistrationPaperRepository registrationPaperRepository, RegistrationPaperService databaseService) {
        this.databaseService = databaseService;
        this.registrationPaperRepository = registrationPaperRepository;
    }

    @PostMapping("registration-papers/upload")
    FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String name = StringUtils.cleanPath(file.getOriginalFilename());
        RegistrationPaper registrationPaper = new RegistrationPaper();
        registrationPaper.setFileName(name);
        registrationPaper.setDocFile(file.getBytes());

        registrationPaperRepository.save(registrationPaper);

        // next line makes url. example "http://localhost:8080/download/naam.jpg"
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/registration-papers/download/").path(name).toUriString();

        String contentType = file.getContentType();

        FileUploadResponse response = new FileUploadResponse(name, contentType, url );

        return response;
    }

    //    get for single download
    @GetMapping("/registration-papers/download/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        RegistrationPaper doc = registrationPaperRepository.findByFileName(fileName);

//        this mediaType decides witch type you accept if you only accept 1 type
//        MediaType contentType = MediaType.IMAGE_JPEG;
//        this is going to accept multiple types

        String mimeType = request.getServletContext().getMimeType(doc.getFileName());

//        for download attachment use next line
//        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + resource.getFilename()).body(resource);
//        for showing image in browser
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + doc.getFileName()).body(doc.getDocFile());
    }

    //    post for multiple uploads to database
    @PostMapping("/registration-papers/multi-upload")
    List<FileUploadResponse> multipleUpload(@RequestParam("files") MultipartFile [] files) {

        if(files.length > 7) {
            throw new RuntimeException("too many files selected");
        }

        List<FileUploadResponse> uploadResponseList = new ArrayList<>();
        Arrays.asList(files).stream().forEach(file -> {

            String name = StringUtils.cleanPath(file.getOriginalFilename());
            RegistrationPaper registrationPaper = new RegistrationPaper();
            registrationPaper.setFileName(name);
            try {
                registrationPaper.setDocFile(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            registrationPaperRepository.save(registrationPaper);

//            next line makes url. example "http://localhost:8080/download/naam.jpg"
            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(name).toUriString();

            String contentType = file.getContentType();

            FileUploadResponse response = new FileUploadResponse(name, contentType, url);

            uploadResponseList.add(response);
        });

        return uploadResponseList;

    }

    @GetMapping("registration-papers/zipDownload/")
    public void zipDownload(@RequestParam("fileName") String[] files, HttpServletResponse response) throws IOException {


        try(ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())){
            Arrays.asList(files).stream().forEach(file -> {
                Resource resource = databaseService.downLoadFileDatabase(file);
                ZipEntry zipEntry = new ZipEntry(resource.getFilename());
                try {
                    zipEntry.setSize(resource.contentLength());
                    zos.putNextEntry(zipEntry);

                    StreamUtils.copy(resource.getInputStream(), zos);

                    zos.closeEntry();
                } catch (IOException e) {
                    System.out.println("some exception while zipping");
                }
            });
            zos.finish();
        }

        response.setStatus(200);
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=zipfile");
    }

    @GetMapping("/registration-papers/getAll")
    public Collection<RegistrationPaper> getAllFromDB(){
        return databaseService.getALlFromDB();
    }
}