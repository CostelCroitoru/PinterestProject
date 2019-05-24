package pos.Pinterest.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "photos")
public class Photo {

    @Id
    private ObjectId _id;

    @Field(value = "name")
    private String name;

    @Indexed(unique = true)
    @Field(value = "filename")
    private String filename;

    @Field(value = "tags")
    private List<String> tags;

    public Photo(String name, String filename, List<String> tags) {
        this.name = name;
        this.filename = filename;
        this.tags = tags;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
