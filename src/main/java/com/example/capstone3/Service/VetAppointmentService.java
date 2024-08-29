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
public class VetAppointmentService {

   private final VetAppointmentRepository vetAppointmentRepository;
   private final VetRepository vetRepository;
   private final StableRepository stableRepository;
   private final HorseRepository horseRepository;

    public List<VetAppointment> getAllVetAppointment() {
        return vetAppointmentRepository.findAll();
    }

    public void bookingAppointmentForHorse(Integer vet_id, Integer horse_id, Integer stable_id, VetAppointment vetAppointment) {
     Vet vet1=vetRepository.findVetById(vet_id);
     Horse horse1=horseRepository.findHorseById(horse_id);
     Stable stable1=stableRepository.findStableById(stable_id);
     if(vet1==null){
         throw new ApiException("Vet cannot Found");
     }
     if(horse1==null){
         throw new ApiException("Horse cannot Found");
     }
     if(stable1==null){
         throw new ApiException("Stable cannot Found");
     }
     vetAppointment.setVet(vet1);
     vetAppointment.setHorse(horse1);
     vetAppointment.setStable(stable1);
     vetAppointmentRepository.save(vetAppointment);
    }

    public void updateVetAppointment(Integer id,VetAppointment vetAppointment) throws ApiException {
        VetAppointment vetAppointment1= vetAppointmentRepository.findVetAppointmentById(id);
        if (vetAppointment1 == null) {
            throw new ApiException("VetAppointment not found");
        }
        vetAppointment1.setDate(vetAppointment.getDate());
        vetAppointment1.setStart_time(vetAppointment.getStart_time());
        vetAppointment1.setEnd_time(vetAppointment.getEnd_time());
        vetAppointment1.setStatus(vetAppointment.getStatus());
        vetAppointment1.setPrice(vetAppointment.getPrice());
        vetAppointmentRepository.save(vetAppointment1);
    }

    public void deleteVetAppointment(Integer id) throws ApiException {
        VetAppointment vetAppointment1= vetAppointmentRepository.findVetAppointmentById(id);
        if (vetAppointment1 == null) {
            throw new ApiException("VetAppointment not found");
        }
        vetAppointmentRepository.delete(vetAppointment1);
    }



}
