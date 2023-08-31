package com.bitespeed.assignment.dao;

import com.bitespeed.assignment.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public interface IdentityRepository extends JpaRepository<Contact, Integer> {


    @Query(value="select * from contact where phone_number = ?1 order by created_at", nativeQuery = true)
    List<Contact> findByPhoneNumber(String phone);

    @Query(value="select * from contact where email = ?1 order by created_at", nativeQuery = true)
    List<Contact> findByEmail(String email);

    List<Contact> findByLinkedId(int linkedId);

    @Query(value="select * from contact where id = ?1 or linked_id = ?1 order by created_at", nativeQuery = true)
    List<Contact> findByLinkedIdOrId(int linkedId);

    @Query(value="select * from contact where phone_number = ?1 and email = ?2 order by created_at", nativeQuery = true)
    Contact findByPhoneNumberAndEmail(String phoneNumber, String email);

    @Query(value="select * from contact where phone_number = ?1 or email = ?2 order by created_at", nativeQuery = true)
    List<Contact> findByPhoneNumberOrEmail(String phoneNumber, String email);
}
