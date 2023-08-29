package com.bitespeed.assignment.dao;

import com.bitespeed.assignment.model.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentityRepository extends JpaRepository<Identity, Integer> {

    Optional<List<Identity>> findByPhoneNumber(String phone);

    Optional<List<Identity>> findByEmail(String email);

    Optional<List<Identity>> findByLinkedId(String linkedId);

    Optional<List<Identity>> findByPhoneNumberAndEmail(String phoneNumber, String email);
}
