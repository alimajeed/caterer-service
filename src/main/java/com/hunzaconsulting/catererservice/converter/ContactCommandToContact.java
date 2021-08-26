package com.hunzaconsulting.catererservice.converter;

import com.hunzaconsulting.catererservice.command.CapacityCommand;
import com.hunzaconsulting.catererservice.command.ContactCommand;
import com.hunzaconsulting.catererservice.domain.Capacity;
import com.hunzaconsulting.catererservice.domain.Contact;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ContactCommandToContact implements Converter<ContactCommand, Contact>  {

    @Synchronized
    @Nullable
    @Override
    public Contact convert(ContactCommand contactCommand) {
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
