package pos.Pinterest.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pos.Pinterest.model.User;

import java.util.List;

@Repository
public class UserRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public UserRepository(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public List<User> getAllUsers(){
        return mongoTemplate.findAll(User.class);
    }

    public User findByUsername(String username) {

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        return mongoTemplate.findOne(query, User.class);
    }

    public User insertOne(User user){
        return mongoTemplate.save(user);
    }


}
