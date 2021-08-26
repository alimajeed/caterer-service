package com.hunzaconsulting.catererservice.service;

import com.hunzaconsulting.catererservice.command.CatererCommand;
import com.hunzaconsulting.catererservice.converter.CatererCommandToCaterer;
import com.hunzaconsulting.catererservice.converter.CatererToCatererCommand;
import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.repository.CatererRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CatererService {

    private CatererRepository catererRepository;
    private CatererToCatererCommand catererToCatererCommand;
    private CatererCommandToCaterer catererCommandToCaterer;

    public List<CatererCommand> getAllCaterers (){
        List<CatererCommand> catererList = catererRepository.findAll()
                .stream()
                .map(caterer -> catererToCatererCommand.convert(caterer))
                .collect(Collectors.toList());
        return catererList;
    }

    public CatererCommand getCaterer(String name) {
        return catererToCatererCommand.convert(catererRepository.findCatererByName(name));
    }

    public CatererCommand save(CatererCommand catererCommand) {
        Caterer detachedCaterer = catererCommandToCaterer.convert(catererCommand);
        Caterer savedCaterer = catererRepository.save(detachedCaterer);
        log.debug("Saved Caterer:" + savedCaterer.getName());
        return catererToCatererCommand.convert(savedCaterer);
    }
}
