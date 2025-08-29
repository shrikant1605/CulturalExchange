package com.culture.exchange.web;

import com.culture.exchange.domain.AppUser;
import com.culture.exchange.domain.Post;
import com.culture.exchange.repository.PostRepository;
import com.culture.exchange.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Validated
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public record PostForm(@NotBlank @Size(min = 3, max = 200) String title,
                           @NotBlank String content,
                           String country) {}

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts/list";
    }

    @GetMapping("/posts/new")
    public String newPost(Model model) {
        model.addAttribute("form", new PostForm("", "", ""));
        return "posts/new";
    }

    @PostMapping("/posts")
    public String create(@ModelAttribute("form") PostForm form, @AuthenticationPrincipal User principal) {
        AppUser author = userRepository.findByEmail(principal.getUsername()).orElseThrow();
        Post p = new Post();
        p.setTitle(form.title());
        p.setContent(form.content());
        p.setCountry(form.country());
        p.setAuthor(author);
        postRepository.save(p);
        return "redirect:/";
    }

    @GetMapping("/posts/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "posts/detail";
    }
}

