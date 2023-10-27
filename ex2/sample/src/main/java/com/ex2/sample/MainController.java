package com.ex2.sample;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class MainController {
@Autowired
Repo repo;
  @GetMapping("")
  public String mainPage() {
    return "index";
  }
  @GetMapping("/users_home")
  public String home(Model model) {
   List<User> users = (List<User>) repo.findAll();
    model.addAttribute("users", users);
    return "user1";
  }
  @GetMapping("/add_user")
  public String newUser(Model model) {
    model.addAttribute("user", new User());
    return "form";
  }
@PostMapping("/user_save")
  public String addUser(User user) {
    repo.save(user);
    return "redirect:/users_home";
  }
  @GetMapping("update/user{id}")
  public String updateUser(@PathVariable int id, Model model) {
    Optional<User> user = repo.findById(id);
    model.addAttribute("user", user.get());
    return "form";
  }
  @GetMapping("delete/user{id}")
  public String deleteUser(@PathVariable int id) {
    repo.deleteById(id);
    return "redirect:/users_home";
  }
}
