package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;
import java.util.ArrayList;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(@RequestParam(required = false) String pizzaName, Model model) {

        List<Pizza> pizzasList = new ArrayList<>();

        if (pizzaName == null || pizzaName.isEmpty()) {
            pizzasList = repository.findAll();
        } else {
            pizzasList = repository.findByNameStartingWith(pizzaName);
        }

        model.addAttribute("pizzasList", pizzasList);

        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int pizzaId, Model model) {

        List<Pizza> pizzasList = repository.findAll();

        for (Pizza currentPizza : pizzasList) {
            if (pizzaId == currentPizza.getId()) {
                model.addAttribute("pizza", currentPizza);
            }
        }

        return "pizzas/pizzaDetails";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());

        return "pizzas/createPizza";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "pizzas/createPizza";
        }

        repository.save(formPizza);

        return "redirect:/pizzas/index";
    }

}
