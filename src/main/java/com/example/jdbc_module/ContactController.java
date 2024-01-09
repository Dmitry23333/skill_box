package com.example.jdbc_module;

import com.example.jdbc_module.service.ContactServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {
    private final ContactServiceImpl contactService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "index";
    }

    @GetMapping("/contact/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "create";
    }

    @PostMapping("/contact/create")
    public String createTask(@ModelAttribute Contact contact) {
        contactService.save(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.findById(id);
        if (contact != null) {
            model.addAttribute("contact", contact);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/contact/edit")
    public String editTask(@ModelAttribute Contact contact) {
        contactService.update(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        contactService.deleteById(id);
        return "redirect:/";
    }
}
