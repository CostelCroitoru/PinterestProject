package pos.Pinterest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import pos.Pinterest.model.*;
import pos.Pinterest.services.PhotoService;
import pos.Pinterest.services.UserPhotoService;
import pos.Pinterest.services.UserService;


import java.util.Arrays;



@RestController
public class RegistrationController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserPhotoService userPhotoService;

    @Autowired
    private PhotoService photoService;


    public RegistrationController(){
        super();
    }

    @CrossOrigin
    @RequestMapping(value = "/perform_register", method = RequestMethod.POST)
    public JsonResponse performRegister(@RequestBody User userInfo){
        System.out.println("/perform_register");

        userInfo.setRoles(Arrays.asList("USER"));

        JsonResponse jsonResponse = new JsonResponse(false, ErrorType.NO_ERROR);

        try {
            UserPhoto userPhoto = new UserPhoto(userInfo.getUsername(), Arrays.asList(photoService.findByFilename("default_image.jpg")));

            userService.insertOne(userInfo);
            userPhotoService.insertOne(userPhoto);


        }catch (DuplicateKeyException duplicateKeyException){
                jsonResponse.setHasError(true);
                jsonResponse.setErrorType(ErrorType.DUPLICATE_KEY_EXCEPTION);

        }catch (Exception anotherException){
            jsonResponse.setHasError(true);

            System.out.println("/nAnother exception when insert user: " + userInfo.getUsername());
            anotherException.printStackTrace();
        }
        return jsonResponse;
    }

}
