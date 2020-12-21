package ffhs.students.projects.dienstplanverwaltung.shiftplan;

import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import java.util.List;
import java.util.stream.Collectors;

public interface ISlotDisplay extends ISlot {
    default String getTitle(){
        String applied = "";
        if (getAssigned().size() > 0)
            applied = getAssigned().stream()
                .map(e -> e.getUser().getNickname())
                .reduce("", (acc, name) -> acc + ", " + name)
                .substring(2);
        return getDetailTitle() + ": " + applied;
    }
    default String getDetailTitle(){
        String title = getTitle();
        String numbers = "(" + getAssigned().size() + "/" + getNumberOfEmployeesNeeded() + ")";
        return title + numbers;
    }
    default List<String> getAssignedStrings(){
        return getAssigned().stream()
                .map(iEmployee -> iEmployee.getUser().getNickname())
                .collect(Collectors.toList());
    }
    default List<String> getAppliedStrings(){
        return getApplied().stream()
                .map(iEmployee -> iEmployee.getUser().getNickname())
                .collect(Collectors.toList());
    }
}
