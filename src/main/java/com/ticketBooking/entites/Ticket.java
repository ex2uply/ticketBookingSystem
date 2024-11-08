package com.ticketBooking.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private  String destination;
    @JsonProperty("date_of_travel")
    private Date dateOfTravel;
    private Train train;

    public String getTicketInfo() {
        return String.format("TicketId: %s Source: %s Destination: %s Date Of Travel %s Train %s",ticketId,source,destination,dateOfTravel,train.getTrainId());
    }
}
