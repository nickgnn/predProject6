package ru.javamentor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.model.User;
import ru.javamentor.service.UserService;
import ru.javamentor.exception.DBException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UsersController {
    @Autowired
    @Qualifier("userServiceImpl")
    public UserService service;

    @GetMapping("/users")
    public String getListOfUsers(Model model) throws DBException{
        model.addAttribute("usersList", service.getAllUsers());
        return "usersList";
    }

    @GetMapping("/add")
    public String addUser(String name, int age, String password, String role) throws DBException {
        service.addUser(name, age, password, role);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(@ModelAttribute User user, HttpServletRequest request) throws DBException {
        String newName = request.getParameter("newName");
        String newAge = request.getParameter("newAge");
        String newPassword = request.getParameter("newPassword");
        String newRole = request.getParameter("newRole");

        if (newAge.equals("")) {
            newAge = String.valueOf(user.getAge());
        }

        User newUser = new User(newName, Integer.valueOf(newAge), newPassword, newRole);

        service.updateUser(user, newUser);

        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(long id) throws DBException{
        service.deleteUserById(id);
        return "redirect:/users";
    }
}
