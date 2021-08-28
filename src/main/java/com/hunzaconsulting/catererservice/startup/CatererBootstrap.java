package com.hunzaconsulting.catererservice.startup;

import com.hunzaconsulting.catererservice.domain.Address;
import com.hunzaconsulting.catererservice.domain.Capacity;
import com.hunzaconsulting.catererservice.domain.Caterer;
import com.hunzaconsulting.catererservice.domain.Contact;
import com.hunzaconsulting.catererservice.repository.CatererRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CatererBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CatererRepository catererRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        catererRepository.deleteAll();
        loadCaterers();
    }

    private void loadCaterers() {
        saveCaterer("name1", "city1", "street1", 1, 10);
        saveCaterer("name1", "city2", "street2", 1, 10);
        saveCaterer("name1", "city3", "street3", 1, 10);
        saveCaterer("name2", "city1", "street4", 1, 10);
        saveCaterer("name3", "city1", "street5", 1, 10);
        saveCaterer("name4", "city1", "street5", 1, 10);
        saveCaterer("name5", "city1", "street5", 1, 10);
        saveCaterer("name5", "city2", "street4", 1, 10);
        saveCaterer("name5", "city1", "street3", 1, 10);
        saveCaterer("name5", "city3", "street2", 1, 10);

    }

    private void saveCaterer(String name, String city, String street, int minGuestNo, int maxGuestNo) {
        Caterer caterer = Caterer.builder()
                .name(name)
                .address(Address.builder().cityName(city).streetAddress(street).build())
                .capacity(Capacity.builder().minGuestNo(minGuestNo).maxGuestNo(maxGuestNo).build())
                .contact(Contact.builder().build())
                .build();
        catererRepository.save(caterer);
    }
}
