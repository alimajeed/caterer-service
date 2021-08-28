package com.hunzaconsulting.catererservice.startup;

import com.hunzaconsulting.catererservice.converter.CatererToCatererDto;
import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.dto.CatererDto;
import com.hunzaconsulting.catererservice.repository.CatererRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CacheInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private CacheManager cacheManager;
    private CatererRepository catererRepository;
    private CatererToCatererDto catererToCatererDto;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //initializeCache();
    }

    private void initializeCache() {
        System.out.println("Cache Started");
        List<Caterer> caterers = catererRepository.findAll();
        Cache catererById = cacheManager.getCache("catererById");
        Cache caterersByName = cacheManager.getCache("catererByName");
        Cache caterersByCity = cacheManager.getCache("catererByCity");
        for (Caterer caterer : caterers){
            //CatererById
            CatererDto catererDto = catererToCatererDto.convert(caterer);
            catererById.put(caterer.getId(), catererDto);

            //CaterersByName
            if (null == caterersByName.get(caterer.getName())){
                caterersByName.put(caterer.getName(), new ArrayList<CatererDto>());
            }
            caterersByName.get(caterer.getName(), List.class).add(catererDto);

            //CaterersByCity
            /*if (null == caterersByCity.get(caterer.getAddress().getCityName())){
                caterersByName.put(caterer.getName(), new ArrayList<CatererDto>());
            }
            caterersByCity.get(caterer.getAddress().getCityName(), List.class).add(catererDto);*/
        }
    }
}
