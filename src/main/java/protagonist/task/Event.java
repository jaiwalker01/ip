package protagonist.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task with a start and end time
 */
public class Event extends Task {

    private final String description;

    private final String rawStartTime;
    private LocalDate startDate;
    private LocalDateTime startDateTime;

    private final String rawEndTime;
    private LocalDate endDate;
    private LocalDateTime endDateTime;

    /**
     * Constructs a {@code Event}
     * @param rawString literal String input from user
     * @param description name from input
     * @param rawStartTime start time from input
     * @param rawEndTime end time from input
     */
    public Event(String rawString, String description, String rawStartTime, String rawEndTime) {
        super(rawString);
        this.description = description;
        this.rawStartTime = rawStartTime;
        this.rawEndTime = rawEndTime;

        try {
            this.startDateTime = LocalDateTime.parse(rawStartTime);
            this.startDate = null;
        } catch (DateTimeParseException e) {
            // ignore
        }

        try {
            this.startDate = LocalDate.parse(rawStartTime);
            this.startDateTime = null;
        } catch (DateTimeParseException e) {
            //ignore
        }

        try {
            this.endDateTime = LocalDateTime.parse(rawEndTime);
            this.endDate = null;
        } catch (DateTimeParseException e) {
            // ignore
        }

        try {
            this.endDate = LocalDate.parse(rawEndTime);
            this.endDateTime = null;
        } catch (DateTimeParseException e) {
            //ignore
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getEventStart() {
        String time;

        if (this.startDateTime != null) {
            time = convertDateTime(this.startDateTime);
        } else if (this.startDate != null) {
            time = convertDate(this.startDate);
        } else {
            time = rawStartTime;
        }

        return time;
    }

    public String getEventEnd() {
        String time;

        if (this.endDateTime != null) {
            time = convertDateTime(this.endDateTime);
        } else if (this.endDate != null) {
            time = convertDate(this.endDate);
        } else {
            time = rawEndTime;
        }

        return time;
    }

    public String getRawStartTime() {
        return rawStartTime;
    }

    public String getRawEndTime() {
        return rawEndTime;
    }

    /**
     *  Convert dateTime YYYY-MM-DDThh:mm ( e.g. 2001-09-11T15:00 )
     *  to formatted dateTime  11 September 2001, 3:00 pm
     */
    public String convertDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mm a"));
    }

    /**
     *  Convert date YYYY-MM-DD ( e.g. 2001-09-11 )
     *  to formatted date  11 September 2001
     */
    public String convertDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    @Override
    public String toFileFormat() {
        // T | <0/1> | <name>
        String done = this.taskStatus() ? "1" : "0";
        return "E | " + done + " | " + this.getDescription() + " | " + this.getRawStartTime()
                + " | " + this.getRawEndTime();
    }

    @Override
    public String toString() {
        String str = (this.taskStatus()) ? "X" : " ";

        return "[E][" + str + "] " + description + " (from: " + this.getEventStart()
                + " to: " + this.getEventEnd() + " )";
    }
}
