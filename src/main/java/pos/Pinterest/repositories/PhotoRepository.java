package pos.Pinterest.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pos.Pinterest.model.Photo;

import java.util.List;

@Repository
public class PhotoRepository {
    private MongoTemplate mongoTemplate;

    @Autowired
    public PhotoRepository(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public List<Photo> getAllPhotos(){
        return mongoTemplate.findAll(Photo.class);
    }

    public Photo insertOne(Photo photo){

        return mongoTemplate.save(photo);
    }
    
    public Photo findByFilename(String filename){
        Query query = new Query();
        query.addCriteria(Criteria.where("filename").is(filename));

        return mongoTemplate.findOne(query, Photo.class);
    }

}
