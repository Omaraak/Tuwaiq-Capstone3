package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Instructor;
import com.example.capstone3.Model.Review;
import com.example.capstone3.Repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public List<Instructor> getAllInstructor() {
        return instructorRepository.findAll();
    }

    public void addInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    public void updateInstructor(Integer id,Instructor instructor) {
        Instructor instructor1 = instructorRepository.findInstructorById(id);
        if (instructor1 == null) {
            throw new ApiException("Instructor not found");
        }
        instructor1.setName(instructor.getName());
        instructor1.setPhone(instructor.getPhone());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setExperience(instructor.getExperience());
        instructor1.setCertificates(instructor.getCertificates());
        instructor1.setSalary(instructor.getSalary());
        instructor1.setType(instructor.getType());
        instructor1.setTraits(instructor.getTraits());
        instructorRepository.save(instructor1);

    }

    public void deleteInstructor(Integer id) {
        Instructor instructor1 = instructorRepository.findInstructorById(id);
        if (instructor1 == null) {
            throw new ApiException("Instructor not found");
        }
        instructorRepository.delete(instructor1);
    }

    public Set<Review> getInstructorReviews(Integer instructorId) {
        Instructor instructor1 = instructorRepository.findInstructorById(instructorId);
        if (instructor1 == null) {
            throw new ApiException("Instructor not found");
        }

        return instructor1.getReviews();
    }


}
