package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Horse;
import com.example.capstone3.Model.Stable;
import com.example.capstone3.Model.Vet;
import com.example.capstone3.Model.VetAppointment;
import com.example.capstone3.Repository.HorseRepository;
import com.example.capstone3.Repository.StableRepository;
import com.example.capstone3.Repository.VetAppointmentRepository;
import com.example.capstone3.Repository.VetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StableService {
    private final StableRepository stableRepository;
    private final VetRepository vetRepository;
   private final HorseRepository horseRepository;
    private final VetAppointmentRepository vetAppointmentRepository;

    public List<Stable> getAllStable() {
        return stableRepository.findAll();
    }

    public void addStable(Stable stable) {
        stableRepository.save(stable);
    }

    public void updateStable(Integer id, Stable stable) throws ApiException {
        Stable stable1 = stableRepository.findStableById(id);
        if (stable1 == null) {
            throw new ApiException("Stable not found");
        }
        stable1.setName(stable.getName());
        stable1.setCapacity(stable.getCapacity());
        stable1.setArea_size(stable.getArea_size());
        stable1.setNumber_of_houres(stable.getNumber_of_houres());
        stable1.setLocation(stable.getLocation());
        stableRepository.save(stable1);
    }

    public void deleteStable(Integer id) throws ApiException {
        Stable stable1 = stableRepository.findStableById(id);
        if (stable1 == null) {
            throw new ApiException("Stable not found");
        }
        stableRepository.delete(stable1);
    }


    public List<VetAppointment> horseAppointments(Integer horseId){
        List<VetAppointment> v=vetAppointmentRepository.findVetAppointmentByHorseId(horseId);
        if(v==null){
            throw new ApiException("No appointments found!");
        }
        return v;
    }

    public void cancelAppointmentForHorse(Integer vet_id, Integer horse_id, Integer stable_id,Integer vetAppointment_id) {
        Vet vet1 = vetRepository.findVetById(vet_id);
        Horse horse1 = horseRepository.findHorseById(horse_id);
        Stable stable1 = stableRepository.findStableById(stable_id);
        VetAppointment vetAppointment1 = vetAppointmentRepository.findVetAppointmentById(vetAppointment_id);
        if (vet1 == null) {
            throw new ApiException("vet not found");
        }
        if (horse1 == null) {
            throw new ApiException("horse not found");
        }
        if (stable1 == null) {
            throw new ApiException("stable not found");
        }
        if (vetAppointment1 == null) {
            throw new ApiException("vetAppointment not found");
        }
        if(vetAppointment1.getHorse()==horse1){
            vetAppointment1.setStatus("Cancelled");
            vetAppointmentRepository.save(vetAppointment1);
        }


    }







}
