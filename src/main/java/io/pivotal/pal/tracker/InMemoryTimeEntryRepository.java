package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private final Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    private long counter=1;

    @Override
    public TimeEntry create(TimeEntry timeEntry ){
        TimeEntry createdTimeEntry = new TimeEntry(counter,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());
        timeEntryMap.put(createdTimeEntry.getId(), createdTimeEntry);
        counter++;
        return createdTimeEntry;
    }

    @Override
    public TimeEntry find(long id) {

        return timeEntryMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {

        return new ArrayList<TimeEntry>(timeEntryMap.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (timeEntryMap.containsKey(id) ){
            TimeEntry updatedTimeEntry = new TimeEntry(id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(),
                    timeEntry.getHours());
            timeEntryMap.put(id, updatedTimeEntry);
            return updatedTimeEntry;
        }else{
            return null;
        }

    }

    @Override
    public void delete(long timeEntryId) {

            timeEntryMap.remove(timeEntryId);



    }


}
