package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.dto.ContactDto;
import com.hunzaconsulting.catererservice.domain.Contact;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ContactToContactDto implements Converter<Contact, ContactDto>  {

    @Synchronized
    @Nullable
    @Override
    public ContactDto convert(Contact contact) {
        if (null == contact){
            return null;
        }
        final ContactDto contactCommand = new ContactDto();
        contactCommand.setPhoneNumber(contact.getPhoneNumber());
        contactCommand.setMobileNumber(contact.getMobileNumber());
        contactCommand.setEmail(contact.getEmail());
        return contactCommand;
    }
}
