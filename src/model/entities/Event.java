package model.entities;

import java.util.HashSet;
import java.util.Set;
import model.enums.CategoryOption;

public class Event {

    private String name;
    private CategoryOption category;
    private Set<Participant> participants = new HashSet<>();

    public Event(String name, CategoryOption categoryOptions) {
        this.name = name;
        this.category = categoryOptions;
    }

    public String getName() {
        return name;
    }

    public CategoryOption getCategory() {
        return category;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }
}
