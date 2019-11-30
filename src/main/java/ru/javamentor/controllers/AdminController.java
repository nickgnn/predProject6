package ru.javamentor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javamentor.exception.DBException;
import ru.javamentor.model.User;
import ru.javamentor.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class AdminController {
    public UserService service;

    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public String getListOfUsers(Model model) throws DBException{
        model.addAttribute("admin", service.getAllUsers());
        return "admin";
    }

    @GetMapping("/add")
    public String addUser(String username, String password, Integer age, String role) throws DBException {
        if (StringUtils.isEmpty(age)) {
            age = 0;
        }

        if (StringUtils.isEmpty(role)) {
            role = "user";
        }

        service.addUser(username, password, age, role);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUser(@ModelAttribute User user, HttpServletRequest request, ModelMap map) throws DBException {
        service.updateUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(long id) throws DBException{
        service.deleteUserById(id);
        return "redirect:/admin";
    }
}
