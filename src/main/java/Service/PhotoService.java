package Service;

import Model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    String addPhoto(int title, MultipartFile file) throws IOException;
    Photo getPhoto(String id);
    Photo getPhotoByTitle(int title);

}
