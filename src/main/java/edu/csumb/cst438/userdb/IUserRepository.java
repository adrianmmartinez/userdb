package edu.csumb.cst438.userdb;

import edu.csumb.cst438.userdb.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IUserRepository extends MongoRepository<User, String> {

    @Query (value = "{'username':?0}")
    User findByUsername(String username);

    @Query (value = "{'id':?0}")
    User findByRepoId(String id);
}
