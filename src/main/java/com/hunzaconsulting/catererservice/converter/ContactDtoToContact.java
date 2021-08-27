package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.dto.ContactDto;
import com.hunzaconsulting.catererservice.domain.Contact;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ContactDtoToContact implements Converter<ContactDto, Contact>  {

    @Synchronized
    @Nullable
    @Override
    public Contact convert(ContactDto contactCommand) {
        if (null == contactCommand){
            return null;
        }
        final Contact contact = new Contact();
        contact.setPhoneNumber(contactCommand.getPhoneNumber());
        contact.setMobileNumber(contactCommand.getMobileNumber());
        contact.setEmail(contactCommand.getEmail());
        return contact;
    }
}
