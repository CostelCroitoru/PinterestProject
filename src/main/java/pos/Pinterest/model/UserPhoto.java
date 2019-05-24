package pos.Pinterest.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "users_photos")
public class UserPhoto {
    @Id
    private ObjectId _id;

    @Indexed(unique = true)
    @Field(value = "username")
    private String username;

    @Field(value = "photos")
    @DBRef
    private List<Photo> photos;

    public UserPhoto(String username, List<Photo> photos) {
        this.username = username;
        this.photos = photos;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
