package com.hunzaconsulting.catererservice.controller;

import com.hunzaconsulting.catererservice.command.CatererCommand;
import com.hunzaconsulting.catererservice.service.CatererService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/caterers")
@AllArgsConstructor
public class CatererController {

    private CatererService catererService;

    @PostMapping
    public CatererCommand saveCaterer(@RequestBody CatererCommand catererCommand) {
        return catererService.save(catererCommand);
    }

    @GetMapping
    public List<CatererCommand> fetchAllCaterers () {
        List<CatererCommand> catererList = catererService.getAllCaterers();
        catererList.stream()
                .forEach(caterer -> caterer.add(linkTo(CatererController.class).slash(caterer.getName()).withSelfRel()));
        return catererList;
    }

    @GetMapping(path = "/{name}")
    public CatererCommand fetchCaterer (@PathVariable("name") String name) {
        CatererCommand caterer = catererService.getCaterer(name);
        caterer.add(linkTo(CatererController.class).slash(caterer.getId()).withSelfRel());
        return caterer;
    }
}
