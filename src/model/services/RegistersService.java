package model.services;

import java.util.List;
import java.util.Scanner;
import model.entities.Event;
import model.entities.Participant;
import model.enums.CategoryOption;
import model.exceptions.DomainException;

public class RegistersService {

    public static void eventRegister(Scanner sc, List<Event> events) throws DomainException {

        System.out.print("How many events: ");
        int n = sc.nextInt();
        if (n <= 0) {
            throw new DomainException("The number of events cannot be less or equals to zero!");
        }

        sc.nextLine();

        System.out.println("");
        System.out.println("Events data: ");
        for (int i = 0; i < n; i++) {

            System.out.print("Event's name: ");
            String name = sc.nextLine();

            if (name.trim().isEmpty()) {
                throw new DomainException("The name cannot be empty!");
            }

            System.out.println("Event's category: ");
            System.out.println("- TECH");
            System.out.println("- BUSINESS");
            System.out.println("- EDUCATION");
            System.out.println("- ENTERTAINMENT");

            String category = sc.nextLine().toUpperCase();

            try {
                CategoryOption categoryOptions = CategoryOption.valueOf(category);
                events.add(new Event(name, categoryOptions));

            } catch (IllegalArgumentException e) {
                throw new DomainException("Invalid category");
            }
            System.out.println("");
        }
    }

    public static void participantRegister(Scanner sc, List<Event> events) throws DomainException {

        for (Event event : events) {

            int option;
            do {
                System.out.println("Add participant to " + event.getName());

                System.out.print("Name: ");
                String name = sc.nextLine();
                if (name.trim().isEmpty()) {
                    throw new DomainException("The name cannot be empty!");
                }

                System.out.print("Email: ");
                String email = sc.nextLine();
                if (email.trim().isEmpty()) {
                    throw new DomainException("The email cannot be empty!");
                }

                System.out.print("Age: ");
                int age = sc.nextInt();
                if (age <= 0) {
                    throw new DomainException("The age cannot be less or equals to zero!");
                }
                sc.nextLine();

                event.getParticipants().add(new Participant(name, email, age));

                System.out.print("More participants? (1-YES / 2-NO): ");
                option = sc.nextInt();
                if (option != 1 && option != 2) {
                    throw new DomainException("Option not found!");
                }
                sc.nextLine();

                System.out.println("");
            } while (option != 2);
        }

    }
}
