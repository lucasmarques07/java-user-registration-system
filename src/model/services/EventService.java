package model.services;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import model.entities.Event;
import model.entities.Participant;
import model.enums.CategoryOption;
import model.exceptions.DomainException;

public class EventService {

    public static Set<Participant> totalUniqueParticipants(List<Event> events) {

        Set<Participant> allParticipants = new HashSet<>();

        for (Event event : events) {
            allParticipants.addAll(event.getParticipants());
        }

        return allParticipants;
    }

    public static Map<CategoryOption, Integer> numberPerCategory(List<Event> events) {
        return events
                .stream()
                .collect(Collectors.groupingBy(Event::getCategory, Collectors.summingInt(e -> e.getParticipants().size())));
    }

    public static Event mostPopularEvent(List<Event> events) throws DomainException {
        if (events.isEmpty()) {
            throw new DomainException("There are no events registered");
        }
        return events.stream()
                .max(Comparator.comparingInt(e -> e.getParticipants().size()))
                .get();
    }

    public static List<String> techAdultEmails(List<Event> events, CategoryOption category) {
        return events.stream()
                .filter(e -> e.getCategory() == category)
                .flatMap(e -> e.getParticipants().stream())
                .filter(e -> e.getAge() >= 18)
                .distinct()
                .sorted((x, y) -> x.getName().compareToIgnoreCase(y.getName()))
                .map(Participant::getEmail)
                .toList();
    }

    public static double avgAge(List<Event> events, CategoryOption category) {
        return events.stream()
                .filter(e -> e.getCategory() == category)
                .flatMap(e -> e.getParticipants().stream())
                .distinct()
                .mapToInt(Participant::getAge)
                .average()
                .orElse(0.0);
    }
}
