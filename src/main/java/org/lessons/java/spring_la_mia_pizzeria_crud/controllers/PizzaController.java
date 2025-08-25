package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model) {

        List<Pizza> pizzasList = repository.findAll();

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

}
