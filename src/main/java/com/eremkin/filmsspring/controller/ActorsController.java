package com.eremkin.filmsspring.controller;

import com.eremkin.filmsspring.model.Actor;
import com.eremkin.filmsspring.repository.ActorRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorsController {

    private final ActorRepository actorRepository;

    public ActorsController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping
    public String listActors(Model model) {
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("actor", new Actor());
        return "actors";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addActor(@Valid @ModelAttribute("actor") Actor actor,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("actors", actorRepository.findAll());
            return "actors"; // показываем форму с ошибками
        }
        actorRepository.save(actor);
        return "redirect:/actors";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteActor(@PathVariable Long id) {
        actorRepository.deleteById(id);
        return "redirect:/actors";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editActorForm(@PathVariable Long id, Model model) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный ID актёра: " + id));
        model.addAttribute("actor", actor);
        return "actor_edit"; // новая страница для редактирования
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String editActor(@PathVariable Long id,
                            @Valid @ModelAttribute("actor") Actor actor,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            return "actor_edit"; // показываем ошибки
        }
        actor.setId(id); // на всякий случай устанавливаем ID
        actorRepository.save(actor);
        return "redirect:/actors";
    }

}
