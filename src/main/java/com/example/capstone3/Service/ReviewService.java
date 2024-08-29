package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Instructor;
import com.example.capstone3.Model.Reservation;
import com.example.capstone3.Model.Review;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.InstructorRepository;
import com.example.capstone3.Repository.ReservationRepository;
import com.example.capstone3.Repository.ReviewRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final InstructorRepository instructorRepository;
    private final ReservationRepository reservationRepository;

    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }

    public void addReview(int userID, int instructorID, int reservationID,  Review review) {
        User user = userRepository.findUserById(userID);
        Instructor instructor = instructorRepository.findInstructorById(instructorID);
        Reservation reservation = reservationRepository.findReservationById(reservationID);

        if (user == null || instructor == null || reservation == null || user.getReservations().isEmpty() ) {
            throw new ApiException("Can't add review");
        }
        if (!user.getReservations().contains(reservation)) {
            throw new ApiException("Can't add review");
        }
        review.setUser(user);
        review.setInstructor(instructor);
        userRepository.save(user);
        instructorRepository.save(instructor);
        reviewRepository.save(review);
    }

    public void updateReview(Integer id,Review review) {
        Review review1=reviewRepository.findReviewById(id);
        if (review1 == null) {
            throw new ApiException("Review not found");
        }
        review1.setComment(review.getComment());
        review1.setRating(review.getRating());
        reviewRepository.save(review1);
    }

    public void deleteReview(int id) {
        Review review1=reviewRepository.findReviewById(id);
        if (review1 == null) {
            throw new ApiException("Review not found");
        }
        reviewRepository.delete(review1);
    }

}
