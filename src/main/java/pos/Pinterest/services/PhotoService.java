package pos.Pinterest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.Pinterest.model.Photo;
import pos.Pinterest.repositories.PhotoRepository;
import pos.Pinterest.repositories.UserRepository;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo insertOne(Photo photo){
        return photoRepository.insertOne(photo);
    }

    public List<Photo> getAllPhotos(){
        return photoRepository.getAllPhotos();
    }

    public Photo findByFilename(String filename){
        return photoRepository.findByFilename(filename);
    }

}
