package com.ticketBooking;

import com.ticketBooking.entites.Train;
import com.ticketBooking.entites.User;
import com.ticketBooking.service.UserBookingService;
import com.ticketBooking.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Running Train Booking System");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        UserBookingService UserBookingService;
        try{
            UserBookingService = new UserBookingService();
        } catch (IOException e) {
            //if user not loaded then just return;
            System.out.println("Error: " + e.getMessage());
            return;
        }

        while(option != 7){
            System.out.println("Choose an option");
            System.out.println("1 - Sign Up");
            System.out.println("2 - Sign In");
            System.out.println("3 - Fetch Bookings");
            System.out.println("4 - Search Trains");
            System.out.println("5 - Book a Seat");
            System.out.println("6 - Cancel Booking");
            System.out.println("7 - Exit the application");

            option = sc.nextInt();

            Train trainSelectedForBooking = new Train();

            switch (option){
                case 1:
                    System.out.println("Enter the username to signup");
                    String nameToSignUp = sc.next();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = sc.next();
                    User userToSignup = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    UserBookingService.signUp(userToSignup);
                    break;
                case 2:
                    System.out.println("Enter the username to Login");
                    String nameToLogin = sc.next();
                    System.out.println("Enter the password to login");
                    String passwordToLogin = sc.next();
                    User userToLogin = new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                    try{
                        UserBookingService = new UserBookingService(userToLogin);
                    }catch (IOException ex){
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    UserBookingService.fetchBooking();
                    break;
                case 4:
                    System.out.println("Type your source station");
                    String source = sc.next();
                    System.out.println("Type your destination station");
                    String dest = sc.next();
                    List<Train> trains = UserBookingService.getTrains(source, dest);
                    int index = 1;
                    for (Train t: trains){
                        System.out.println(index+" Train id : "+t.getTrainId());
                        for (Map.Entry<String, String> entry: t.getStationTime().entrySet()){
                            System.out.println("station "+entry.getKey()+" time: "+entry.getValue());
                        }
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    trainSelectedForBooking = trains.get(sc.nextInt());
                    break;
                case 5:
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = UserBookingService.fetchSeats(trainSelectedForBooking);
                    for (List<Integer> row: seats){
                        for (Integer val: row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = sc.nextInt();
                    System.out.println("Enter the column");
                    int col = sc.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = UserBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked! Enjoy your journey");
                    }else{
                        System.out.println("Can't book this seat");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}