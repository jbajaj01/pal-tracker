package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;
    private final DistributionSummary timeEntrySummary;

    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry)
    { this.timeEntryRepository = timeEntryRepository;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity(createdTimeEntry, HttpStatus.CREATED);
    }

     @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {

        TimeEntry readTimeEntry = timeEntryRepository.find(id);

        if (readTimeEntry == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(readTimeEntry);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List timeEntries = timeEntryRepository.list();
        return new ResponseEntity(timeEntries, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id,
                                 @RequestBody TimeEntry expected) {
        TimeEntry updateTimeEntry = timeEntryRepository.update(id,expected);

        if (updateTimeEntry == null) {
            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.ok(updateTimeEntry);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
