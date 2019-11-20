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
    public String addUser(String username, String password, Integer age, String role) throws DBException {
        service.addUser(username, password, age, role);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(@ModelAttribute User user, HttpServletRequest request) throws DBException {
        System.out.println(user);

        String newName = request.getParameter("newName");
        String newAge = request.getParameter("newAge");
        String newPassword = request.getParameter("newPassword");
        String newRole = request.getParameter("newRole");
        String newRole_Id = request.getParameter("newRole_Id");

        User newUser = new User(newName, newPassword, Integer.valueOf(newAge), newRole, Integer.valueOf(newRole_Id));

        service.updateUser(user, newUser);

        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(long id) throws DBException{
        service.deleteUserById(id);
        return "redirect:/users";
    }
}
