package com.ticketBooking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketBooking.entites.Train;
import com.ticketBooking.entites.User;
import com.ticketBooking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserBookingService {
    private User user;
    private List<User> userList;
    //to deserialize data from the json file
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "src/main/java/com/ticketBooking/localDB/users.json";

    public UserBookingService() throws IOException {
       this.userList = getUserList();
    }

    public List<User> getUserList() throws IOException {
        File users = new File(USERS_PATH);

        return objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public UserBookingService(User user) throws IOException {
        this.user = user;
        this.userList = getUserList();
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1-> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user1.getPassword(),user.getPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId) throws IOException {
        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }

        // Attempt to remove the ticket with the matching ID

        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(ticketId));

        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            saveUserListToFile();
            return Boolean.TRUE;
        } else {
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersJson = new File(USERS_PATH);
        objectMapper.writeValue(usersJson, userList);
    }


    public List<Train> getTrains(String source, String destination) {
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}
