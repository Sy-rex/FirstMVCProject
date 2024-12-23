package com.sobolev.spring.controllers;


import com.sobolev.spring.dao.PersonDAO;
import com.sobolev.spring.models.Person;
import com.sobolev.spring.services.ItemService;
import com.sobolev.spring.services.PeopleService;
import com.sobolev.spring.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

//    private final peopleService peopleService;
    private final PeopleService peopleService;
    private final ItemService itemService;
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PeopleService peopleService, ItemService itemService, PersonDAO personDAO) {
        this.peopleService = peopleService;
        this.itemService = itemService;
        this.personDAO = personDAO;
    }
//    private final PersonValidator personValidator;

//    @Autowired
//    public PeopleController(peopleService peopleService, PersonValidator personValidator) {
//        this.peopleService = peopleService;
//        this.personValidator = personValidator;
//    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());

        personDAO.testNPlus1();

        itemService.findByItemName("TV");
        itemService.findByOwner(peopleService.findAll().get(0));

        peopleService.test();

        return "people/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "people/new";
//        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("person",peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
//        personValidator.validate(person, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "people/edit";
//        }
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
