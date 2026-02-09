package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import model.entities.Event;
import model.enums.CategoryOption;
import model.entities.Participant;
import model.exceptions.DomainException;
import model.services.EventService;
import model.services.RegistersService;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Event> events = new ArrayList<>();

        System.out.println("---Events registration---");

        try {
            RegistersService.eventRegister(sc, events);
        } catch (DomainException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("");
        System.out.println("---Participants registration---");

        try {
            RegistersService.participantRegister(sc, events);
        } catch (DomainException e) {
            System.out.println("Error: " + e.getMessage());
        }

        Set<Participant> allParticipants = EventService.totalUniqueParticipants(events);
        System.out.println("");
        System.out.println("Total unique participants: " + allParticipants.size());

        Map<CategoryOption, Integer> categoryMap = EventService.numberPerCategory(events);
        for (CategoryOption category : categoryMap.keySet()) {
            System.out.println(category + " - " + categoryMap.get(category));
        }

        try {
            Event mostPopularEvent = EventService.mostPopularEvent(events);
            System.out.println("");
            System.out.println("Most popular event: " + mostPopularEvent.getName() + " (" + mostPopularEvent.getParticipants().size() + " participants)");
        } catch (DomainException e) {
            System.out.println("Eror: " + e.getMessage());
        }

        List<String> techAdultEmails = EventService.techAdultEmails(events, CategoryOption.TECH);
        System.out.println("");
        System.out.println("Email addresses of people over 18 years old, ordered by name: ");
        techAdultEmails.forEach(System.out::println);

        System.out.println("");
        double avgAge = EventService.avgAge(events, CategoryOption.TECH);
        System.out.println("TECH event participants average age: " + avgAge);

        sc.close();
    }
}
