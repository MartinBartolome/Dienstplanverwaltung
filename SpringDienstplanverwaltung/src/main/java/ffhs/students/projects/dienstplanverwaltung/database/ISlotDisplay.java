package ffhs.students.projects.dienstplanverwaltung.database;

import java.util.List;
import java.util.stream.Collectors;

public interface ISlotDisplay extends ISlot{
    default String getTitle(){
        String applied = getAssigned().stream()
                .map(e -> e.getUser().getEmail())
                .reduce("", (acc, name) -> acc + ", " + name)
                .substring(2);
        return getDetailTitle() + ": " + applied;
    }
    default String getDetailTitle(){
        String title = getSlotType().getTitle();
        String numbers = "(" + getApplied().size() + "/" + getNumberOfEmployeesNeeded() + ")";
        return title + numbers;
    }
    default List<String> getAssignedStrings(){
        return getAssigned().stream()
                .map(iEmployee -> iEmployee.getUser().getEmail())
                .collect(Collectors.toList());
    }
    default List<String> getAppliedStrings(){
        return getApplied().stream()
                .map(iEmployee -> iEmployee.getUser().getEmail())
                .collect(Collectors.toList());
    }
}
