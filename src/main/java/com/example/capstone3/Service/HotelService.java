package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Horse;
import com.example.capstone3.Model.Hotel;
import com.example.capstone3.Model.Stable;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.HorseRepository;
import com.example.capstone3.Repository.HotelRepository;
import com.example.capstone3.Repository.StableRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final StableRepository stableRepository;
    private final HorseRepository horseRepository;

    public void addHotel(int userID, int stableID, int horseID, Hotel hotel) {
        User user = userRepository.findUserById(userID);
        Stable stable = stableRepository.findStableById(stableID);
        Horse horse = horseRepository.findHorseById(horseID);

        if (user == null || stable == null || horse == null) {
            throw new ApiException("User/Stable or Horse not found");
        }
        Hotel newHotel = new Hotel(null, hotel.getStartDate(), hotel.getEndDate(), hotel.getPrice(), user, null, stable);
        horse.setHotel(newHotel);
        userRepository.save(user);
        stableRepository.save(stable);
        horseRepository.save(horse);
        hotelRepository.save(hotel);
    }
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }
    public void updateHotel (int id, Hotel hotel) throws ApiException {
        Hotel hotelToBeUpdated = hotelRepository.findHotelById(id);
        if (hotelToBeUpdated == null) {
            throw new ApiException("hotel not found");
        }
        hotelToBeUpdated.setStartDate(hotel.getStartDate());
        hotelToBeUpdated.setEndDate(hotel.getEndDate());
        hotelToBeUpdated.setPrice(hotel.getPrice());
    }
    public void deleteHotel(int id) throws ApiException {
        Hotel hotel = hotelRepository.findHotelById(id);
        if (hotel == null) {
            throw new ApiException("hotel not found");
        }
        hotelRepository.delete(hotel);
    }

}
