package pos.Pinterest.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pos.Pinterest.model.UserPhoto;

import java.util.List;

@Repository
public class UserPhotoRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public UserPhotoRepository(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public List<UserPhoto> getAllUsersPhoto(){
        return mongoTemplate.findAll(UserPhoto.class);
    }

    public UserPhoto insertOne(UserPhoto userPhoto){
        return mongoTemplate.save(userPhoto);
    }

    public UserPhoto findByUsername(String username){

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        return mongoTemplate.findOne(query, UserPhoto.class);
    }

}
