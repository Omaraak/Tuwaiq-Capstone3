package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Reservation;
import com.example.capstone3.Model.Review;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        User existingUser = userRepository.findUserById(user.getId());

        if (existingUser == null) {
            throw new ApiException("User not found");
        }

        existingUser.setName(user.getName());
        existingUser.setPhone(user.getPhone());
        existingUser.setEmail(user.getEmail());
        existingUser.setSubscription_type(user.getSubscription_type());
        existingUser.setUser_type(user.getUser_type());

        userRepository.save(existingUser);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new ApiException("User not found");
        }

        userRepository.delete(user);
    }

    public void subscribe(int userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }
        if (user.getSubscription_type().equals("subscribed")) {
            throw new ApiException("User is already subscribed");
        }
        user.setSubscription_type("subscribed");
        userRepository.save(user);
    }

    public void unsubscribe(int userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }
        if (user.getSubscription_type().equals("unsubscribed")) {
            throw new ApiException("User is already not subscribed");
        }
        user.setSubscription_type("unsubscribed");
        userRepository.save(user);
    }

    public List<User> getSubscribedUsers() {
        return userRepository.findUserBySubscription_type("subscribed");
    }

    public Set<Reservation> getUserReservations(int userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }
        return user.getReservations();
    }

    public Set<Review> getUserReviews(int userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }
        return user.getReviews();
    }

}
