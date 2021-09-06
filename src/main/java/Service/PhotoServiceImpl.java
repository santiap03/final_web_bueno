package Service;

import Data.PhotoRepository;

import Model.Photo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepo;

    public String addPhoto(int ClientId, MultipartFile file) throws IOException {
        Photo photo = new Photo(ClientId);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo);
        return photo.getId();

    }

    public Photo getPhoto(String id) {
        return photoRepo.findById(id).get();
    }

    @Override
    public Photo getPhotoByClientId(int ClientId) {

        return photoRepo.findPhotoByClientId(ClientId);

    }
}