import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Flight {
    String flightName;
    String flightNumber;
    String origin;
    String destination;
    int availableSeats;
    double ticketPrice;
    boolean isBooked;
    String getPassengerName;

    public Flight(string flightName, String flightNumber, String origin, String destination, int availableSeats, double ticketPrice) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.isBooked = false;
        this.passengerName = null;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void bookTicket(String passengerName) {
        if (availableSeats > 0) {
            availableSeats--;
            isBooked = true;
            this.passengerName = passengerName;
        }
    }
}

class PaymentGateway {
    public static void processPayment(Flight flight) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (validatePayment(cardNumber, pin)) {
            System.out.println("Payment of INR" + flight.getTicketPrice() + "Ticket confirmed successfully.");
            flight.bookTicket("John Doe");
            displayTicketDetails(flight);
        } else {
            System.out.println("Payment failed. Invalid card number or PIN.");
        }
    }

    private static boolean validatePayment(String cardNumber, String pin) {
        // Simulate payment validation logic here.
        // In a real-world application, this would involve secure validation with a payment gateway.
        return cardNumber.length() == 16 && pin.length() == 4;
    }

    private static void displayTicketDetails(Flight flight) {
        System.out.println("Ticket Details:");
        System.out.println("Flight Number: " + flight.getFlightNumber());
        System.out.println("Origin: " + flight.getOrigin());
        System.out.println("Destination: " + flight.getDestination());
        System.out.println("Passenger Name: " + flight.getPassengerName());
        System.out.println("Ticket Price: $" + flight.getTicketPrice());
    }
}


class AirlineReservationSystem {
    List<Flight> flights;

    public AirlineReservationSystem() {
        flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void displayAvailableFlights() {
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.println("flightName:" + flight.getflightName());
            System.out.println("Flight Number: " + flight.getflightNumber());
            System.out.println("Origin: " + flight.getOrigin());
            System.out.println("Destination: " + flight.getDestination());
            System.out.println("Available Seats: " + flight.getAvailableSeats());
            System.out.println("Ticket Price: INR" + flight.getTicketPrice());
            System.out.println();
        }
    }

    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getflightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        AirlineReservationSystem airlineSystem = new AirlineReservationSystem();

        Flight flight1 = new Flight("indiGo", "AA123", "andhra pradesh", "Hydrabad", 100, 2500.0);
        Flight flight2 = new Flight("vistara", "UA456", "Hydrabad", "Tamil Nadu", 120, 3500.0);
        Flight flight3 = new Flight("Air India", "A0501", "Tamil Nadu", "Mumbai", 80, 8000.0);

        airlineSystem.addFlight(flight1);
        airlineSystem.addFlight(flight2);
        airlineSystem.addFlight(flight3);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Available Flights");
            System.out.println("2. Book a Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    airlineSystem.displayAvailableFlights();
                    break;
                case 2:
                    System.out.print("Enter the flight number: ");
                    String flightNumber = scanner.next();
                    Flight flight = airlineSystem.findFlight(flightNumber);
                    if (flight != null) {
                        flight.bookTicket();
                    } else {
                        System.out.println("Flight not found.");
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the Airline Reservation System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
