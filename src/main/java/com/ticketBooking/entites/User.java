package com.ticketBooking.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private  String  password;

    private  String hashPassword;

    private List<Ticket> ticketsBooked;

    private String userId;

    public void printTickets(){
        for (Ticket ticket : ticketsBooked) {
            System.out.println(ticket.getTicketInfo());
        }

    }
}
