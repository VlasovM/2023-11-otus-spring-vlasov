package ru.javlasov.springwebflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String startPage() {
        return "homePage";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("id", id);
        return "edit";
    }

}
