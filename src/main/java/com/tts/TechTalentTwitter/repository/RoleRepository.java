package com.tts.TechTalentTwitter.repository;

import java.util.List;
import javax.management.relation.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
    
   
}