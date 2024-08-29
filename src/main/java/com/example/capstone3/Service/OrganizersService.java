package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Instructor;
import com.example.capstone3.Model.Organizers;
import com.example.capstone3.Repository.OrganizersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizersService {

    private final OrganizersRepository organizersRepository;

    public List<Organizers> getAllOrganizers() {
        return organizersRepository.findAll();
    }

    public void addOrganizers(Organizers organizers) {
        organizersRepository.save(organizers);
    }

    public void updateOrganizers(Integer id,Organizers organizers) {
        Organizers organizers1=organizersRepository.findOrganizersById(id);
        if (organizers1 == null) {
            throw new ApiException("Organizers not found");
        }

        organizers1.setName(organizers.getName());
        organizers1.setEmail(organizers.getEmail());
        organizers1.setNumber_of_tournament_hosted(organizers.getNumber_of_tournament_hosted());
        organizersRepository.save(organizers1);
    }

    public void deleteOrganizers(Integer id) {
        Organizers organizers1=organizersRepository.findOrganizersById(id);
        if (organizers1 == null) {
            throw new ApiException("Organizers not found");
        }
        organizersRepository.delete(organizers1);
    }
}
