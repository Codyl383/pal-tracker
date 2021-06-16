package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public ResponseEntity<TimeEntry> create(TimeEntry timeEntryToCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntryRepository.create(timeEntryToCreate));
    }

    public ResponseEntity<TimeEntry> read(long timeEntryId) {
        TimeEntry t = timeEntryRepository.find(timeEntryId);
        return t != null ? ResponseEntity.status(HttpStatus.OK).body(t) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<TimeEntry> update(long timeEntryId, TimeEntry timeEntryToUpdate) {
        TimeEntry t = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        return t != null ? ResponseEntity.status(HttpStatus.OK).body(t) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    public ResponseEntity<Void> delete(long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(timeEntryRepository.list());
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> createTimeEntry(@RequestBody TimeEntry timeEntry) {
        return create(timeEntry);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> getListOfEntries() {
        return list();
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> getTimeEntry(@PathVariable Long id) {
        return read(id);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> updateTimeEntry(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        return update(id, timeEntry);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void> deleteTimeEntry(@PathVariable Long id) {
        return delete(id);
    }

}
