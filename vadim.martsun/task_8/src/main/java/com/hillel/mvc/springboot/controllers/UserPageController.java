package com.hillel.mvc.springboot.controllers;

import com.hillel.mvc.springboot.model.requests.UserRequest;
import com.hillel.mvc.springboot.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserPageController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/usersList")
    public ModelAndView getUsersList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.setViewName("usersPage");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addUserForm(Model model) {
        model.addAttribute("userAttribute", new UserRequest());
        return "addUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@ModelAttribute("userAttribute") @Valid UserRequest userRequest,
                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "addUser";
        }
        userService.save(userRequest);
        return "redirect:/users/usersList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        userService.delete(userId);
        return "redirect:/users/usersList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{userId}")
    public String editUserPage(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("userAttribute", userService.getUserRequestById(userId));
        return "editUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/update")
    public String editUser(@ModelAttribute("userAttribute") @Valid UserRequest user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "editUser";
        }
        userService.update(user);
        return "redirect:/users/usersList";
    }
}
