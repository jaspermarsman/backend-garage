package nl.marsman.garage.service;

import nl.marsman.garage.model.RegistrationPaper;
import nl.marsman.garage.repository.RegistrationPaperRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.util.Collection;

@Service
public class RegistrationPaperService {
    private RegistrationPaperRepository doc;

    public RegistrationPaperService(RegistrationPaperRepository doc){
        this.doc = doc;
    }

    public Resource downLoadFileDatabase(String fileName) {


        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(fileName).toUriString();

        Resource resource;

        try {
            resource = new UrlResource(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("the file doesn't exist or not readable");
        }
    }

    public Collection<RegistrationPaper> getALlFromDB() {
        return doc.findAll();
    }
}
