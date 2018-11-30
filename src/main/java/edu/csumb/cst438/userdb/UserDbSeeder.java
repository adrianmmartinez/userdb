package edu.csumb.cst438.userdb;

import edu.csumb.cst438.userdb.IUserRepository;
import edu.csumb.cst438.userdb.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDbSeeder implements CommandLineRunner {

    @Autowired
    IUserRepository userRepo;

    @Override
    public void run(String... args) throws Exception {

        // Make Users here.
        User adrian = new User(null, "adrian", 500);
        User edith = new User(null, "edith", 500);
        User mayra = new User(null, "mayra", 500);
        User luis = new User(null, "luis", 500);

        userRepo.deleteAll();
        List<User> users = Arrays.asList(adrian, edith, mayra, luis);
        userRepo.saveAll(users);

    }
}
