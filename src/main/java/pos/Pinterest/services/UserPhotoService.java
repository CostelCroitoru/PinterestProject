package pos.Pinterest.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.Pinterest.model.UserPhoto;
import pos.Pinterest.repositories.UserPhotoRepository;

import java.util.List;

@Service
public class UserPhotoService {

    @Autowired
    private UserPhotoRepository userPhotoRepository;

    public UserPhoto insertOne(UserPhoto userPhoto){
        return userPhotoRepository.insertOne(userPhoto);
    }

    public List<UserPhoto> getAllUsersPhoto(){
        return userPhotoRepository.getAllUsersPhoto();
    }

    public UserPhoto findByUsername(String username){
        return userPhotoRepository.findByUsername(username);
    }
}
