package org.launchcode.controllers;

import org.launchcode.data.UserData;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping
    public String displayUserIndex(Model model){

       model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    @GetMapping("add")
    public String displayAddUserForm(){

        return "user/add";
    }

  @PostMapping("add")
  public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {

    if(verify.equals(user.getPassword())){
        UserData.add(user);
        return "redirect:/";
    }

    model.addAttribute("username", user.getUsername());
    model.addAttribute("email", user.getEmail());
    model.addAttribute("error", "ERROR: password and verify password need to match");

    return "user/add";

  }

  @GetMapping("details")
    public String getUserDetails(Model model, @RequestParam(required = false) Integer userId){

        model.addAttribute("user", UserData.getById(userId));

        return "user/details";

  }

}
