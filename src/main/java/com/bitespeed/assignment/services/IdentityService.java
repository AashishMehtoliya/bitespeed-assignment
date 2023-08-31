package com.bitespeed.assignment.services;

import com.bitespeed.assignment.LinkPrecedence;
import com.bitespeed.assignment.dao.IdentityRepository;
import com.bitespeed.assignment.dto.ContactDto;
import com.bitespeed.assignment.dto.IdentityDto;
import com.bitespeed.assignment.dto.IdentityRequestDto;
import com.bitespeed.assignment.model.Contact;
import com.bitespeed.assignment.utils.IdentityMapper;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@Configuration
public class IdentityService {

    @Autowired
    private IdentityRepository identityRepository;


    public IdentityDto createIdentity(IdentityRequestDto identityRequestDto) {
        Contact toSave = IdentityMapper.toContactFromIdentity(identityRequestDto);
        String email = identityRequestDto.getEmail();
        Integer phone = identityRequestDto.getPhoneNumber();
        int primaryId;
        if(StringUtils.isNotBlank(email) && phone != null){
            List<Contact> listOfIdentityFromEmail = identityRepository.findByPhoneNumberOrEmail(String.valueOf(phone), email);
            listOfIdentityFromEmail.add(toSave);
            primaryId = processIdentityList(listOfIdentityFromEmail, toSave);
        }
        else if (StringUtils.isNotBlank(email)) {
            List<Contact> listOfIdentityFromEmail = identityRepository.findByEmail(email);
            primaryId = processIdentityList(listOfIdentityFromEmail, toSave);
        } else if (phone != null) {
            List<Contact> listOfIdentityFromPhone = identityRepository.findByPhoneNumber(String.valueOf(phone));
            primaryId = processIdentityList(listOfIdentityFromPhone, toSave);
        } else {
            toSave.setLinkedId(null);
            toSave.setLinkPrecedence(LinkPrecedence.PRIMARY.getName());
            toSave = identityRepository.save(toSave);
            primaryId = toSave.getId();
        }

        return createReturnIdentityDto(primaryId);
    }

    private int processIdentityList(List<Contact> listOfIdentity, Contact toSave) {
        int primaryId;

        if (!listOfIdentity.isEmpty()) {
            //if list is not empty, then we have to link the new contact and all the contact list to the primary one
            //primary contact is the first one in the list because we are getting the list ordered by created_at asc
            Contact firstContact = listOfIdentity.get(0);
            int linkedId = firstContact.getLinkedId() != null ? firstContact.getLinkedId() : firstContact.getId();

            for (Contact contact : listOfIdentity) {
                if (contact.getId() != firstContact.getId()) {
                    contact.setLinkedId(linkedId);
                    contact.setLinkPrecedence(LinkPrecedence.SECONDARY.getName());
                    identityRepository.save(contact);
                } else {
                    contact.setLinkedId(null);
                    contact.setLinkPrecedence(LinkPrecedence.PRIMARY.getName());
                    linkedId = identityRepository.save(contact).getId();
                }
            }

            primaryId = linkedId;
        } else {
            //if list is empty, then we have to create a new primary contact

            toSave.setLinkedId(null);
            toSave.setLinkPrecedence(LinkPrecedence.PRIMARY.getName());
            toSave = identityRepository.save(toSave);
            primaryId = toSave.getId();
        }

        return primaryId;
    }
    public IdentityDto createReturnIdentityDto(Integer linkedId) {
        //here we are creating the return object with primary contact id
        IdentityDto identityDto = new IdentityDto();
        ContactDto contactDto = new ContactDto();

        if(linkedId == 0){
            identityDto.setContact(contactDto);
            return identityDto;
        }

        List<Contact> contacts = identityRepository.findByLinkedIdOrId(linkedId);
        List<Integer> secondaryContactIds = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        List<String> phoneNumbers = new ArrayList<>();

        for (Contact contact: contacts){
            if(contact.getLinkPrecedence().equals(LinkPrecedence.PRIMARY.getName())){
                contactDto.setPrimaryContactId(contact.getId());
            }else{
                secondaryContactIds.add(contact.getId());
            }

            if(!emails.contains(contact.getEmail()))
                emails.add(contact.getEmail());

            if(!phoneNumbers.contains(contact.getPhoneNumber()))
                phoneNumbers.add(contact.getPhoneNumber());
        }
        contactDto.setEmails(emails);
        contactDto.setPhoneNumbers(phoneNumbers);
        contactDto.setSecondaryContactIds(secondaryContactIds);
        identityDto.setContact(contactDto);

        return identityDto;
    }
}
