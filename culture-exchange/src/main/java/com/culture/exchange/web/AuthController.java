package com.culture.exchange.web;

import com.culture.exchange.service.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Validated
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public record RegisterForm(
            @NotBlank @Size(min = 2, max = 100) String name,
            @Email @NotBlank String email,
            @NotBlank @Size(min = 8, max = 100) String password
    ) {}

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("form", new RegisterForm("", "", ""));
        return "auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("form") RegisterForm form, Model model) {
        if (userService.findByEmail(form.email()).isPresent()) {
            model.addAttribute("error", "Email already in use");
            return "auth/register";
        }
        userService.register(form.name(), form.email(), form.password());
        return "redirect:/login?registered";
    }
}

