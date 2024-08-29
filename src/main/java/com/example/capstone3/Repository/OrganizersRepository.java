package com.example.capstone3.Repository;

import com.example.capstone3.Model.Organizers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizersRepository extends JpaRepository<Organizers,Integer> {

    Organizers findOrganizersById(Integer id);

}
