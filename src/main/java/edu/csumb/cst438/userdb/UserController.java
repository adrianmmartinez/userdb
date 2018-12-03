package edu.csumb.cst438.userdb;

import edu.csumb.cst438.userdb.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    IUserRepository userRepo;

    @CrossOrigin()
    @GetMapping("/balance/{id}")
    public int getBalance(@PathVariable String id) {
        User user = userRepo.findByRepoId(id);
        int amount = user.getBalance();
        return amount;
    }

    @CrossOrigin()
    @GetMapping("/verify/funds/{id}/{amount}")
    public boolean verifyFunds(@PathVariable String id, @PathVariable int amount) {
        User user = userRepo.findByRepoId(id);
        int funds = user.getBalance();
        return funds >= amount;
    }

    @CrossOrigin()
    @PostMapping("/login")
    public String signIn(@RequestParam(value = "username") String username) {
        User user = userRepo.findByUsername(username);
        String id = user.getId();
        if (user == null) {
            return null;
        }
        return id;
    }

    @CrossOrigin()
    @PostMapping("/purchase")
    public boolean purchase (@RequestParam(value = "id") String id, @RequestParam(value = "amount") int amount) {
        User user = userRepo.findByRepoId(id);
        if(user == null) {
            System.out.println("error: cant find item with id = " + id);
            return false;
        }
        boolean purchased = user.purchase(amount);
        userRepo.save(user);
        return purchased;
    }

}
