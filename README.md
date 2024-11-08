# IRCTC-Style Ticket Booking System

An **Train ticket booking system** built purely in Java! This project is a complete, console-based system that lets users register, search for trains, book seats, view their tickets, and even cancel bookings, all while practicing core Object-Oriented Programming principles.

---

## 🛠️ Features

- **User Registration & Authentication** – Create new accounts, log in with existing credentials, and keep track of user-specific ticket history.
- **Train Search** – Find available trains based on a source and destination.
- **Seat Booking** – Reserve seats by specifying seat number and row.
- **Ticket Management** – View all booked tickets or cancel them as needed.
- **Admin Console (Optional)** – Option to add or modify train information for setup and testing.

## 📚 Project Structure and Classes

### 1. **User**
   - Holds user data: `name`, `userId`, `password`, and `ticketsBooked`.
   - Key methods:
     - **registerUser()** - Registers a new user.
     - **loginUser()** - Logs in an existing user.
     - **printTickets()** - Displays all tickets booked by the user.

### 2. **Train**
   - Defines a train with `trainId`, `trainNo`, `stations`, and seat layout.
   - Key methods:
     - **getSeats()** - Returns the seating layout.
     - **isValidRoute()** - Validates if a source-destination route is serviced.

### 3. **Ticket**
   - Represents individual tickets with attributes like `ticketId`, `source`, `destination`, `train`, and `date_of_travel`.
   - Key methods:
     - **getTicketInfo()** - Returns ticket details for easy reference.

### 4. **UserBookingService**
   - Manages user services like registration, login, ticket booking, and cancellation.
   - Key methods:
     - **signUp()** - Registers a new user in the system.
     - **loginUser()** - Checks login credentials.
     - **cancelBooking()** - Cancels a specific ticket.
     - **fetchBooking()** - Lists booked tickets for the user.

### 5. **TrainService**
   - Handles operations related to train data like adding, searching, and updating train details.
   - Key methods:
     - **searchTrains()** - Finds trains matching source-destination pairs.
     - **addTrain()** - Adds a train to the database.
     - **updateTrain()** - Updates train information if needed.

## 💡 Key OOP Principles in Use

- **Encapsulation**: Data like user details, train information, and tickets are kept private to their respective classes, with specific methods to interact with them.
- **Abstraction**: Classes define clear boundaries, allowing each to handle specific parts of the booking system.
- **Polymorphism**: Allows for potential user extensions and flexible interactions in future updates.
- **Inheritance**: Sets a foundation for possible expansion with more user roles.
