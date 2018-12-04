package edu.csumb.cst438.userdb;

import edu.csumb.cst438.userdb.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    IUserRepository userRepo;

    @CrossOrigin
    @PostMapping("/login")
    public boolean signIn(@RequestParam(value = "username") String username) {
        System.out.println(username);
        User user = userRepo.findByUsername(username);
        System.out.println(user.getId());
        System.out.println(userRepo.findAll());
        if (user == null) {
            return false;
        }
        return true;
    }

    @CrossOrigin
    @PostMapping("/purchase")
    public boolean purchase (@RequestParam String id, @RequestParam int amount) {
        User user = userRepo.findByRepoId(id);
        if(user == null) {
            System.out.println("error: cant find item with id = " + id);
            return false;
        }
        boolean purchased = user.purchase(amount);
        System.out.print(user.getBalance());
        System.out.println("purchased = " + purchased);
        System.out.print(user.getBalance());
        userRepo.save(user);
        return purchased;
    }

}
