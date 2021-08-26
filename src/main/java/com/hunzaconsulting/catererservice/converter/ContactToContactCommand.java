package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.command.ContactCommand;
import com.hunzaconsulting.catererservice.domain.Contact;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ContactToContactCommand implements Converter<Contact, ContactCommand>  {

    @Synchronized
    @Nullable
    @Override
    public ContactCommand convert(Contact contact) {
        if (null == contact){
            return null;
        }
        final ContactCommand contactCommand = new ContactCommand();
        contactCommand.setPhoneNumber(contact.getPhoneNumber());
        contactCommand.setMobileNumber(contact.getMobileNumber());
        contactCommand.setEmail(contact.getEmail());
        return contactCommand;
    }
}
