import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    private String description;
    private String rawTime;
    private LocalDate date;
    private LocalDateTime dateTime;

    public Deadline(String rawString, String description, String rawTime) {
        super(rawString);
        this.description = description; // description
        this.rawTime = rawTime; // deadline date/dateTime/raw text

        try {
            this.dateTime = LocalDateTime.parse(rawTime);
            this.date = null;
        } catch (DateTimeParseException e) {
            // ignore
        }

        try {
            this.date = LocalDate.parse(rawTime);
            this.dateTime = null;
        } catch (DateTimeParseException e) {
            //ignore
        }
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        String time;

        if (this.dateTime != null) {
            time = convertDateTime(this.dateTime);
        } else if (this.date != null) {
            time = convertDate(this.date);
        } else {
            time = rawTime;
        }

        return time;
    }

    /**
     *  Convert dateTime YYYY-MM-DDThh:mm ( e.g. 2001-09-11T15:00 )
     *  to formatted dateTime  11 September 2001, 3:00 pm
    */
    public String convertDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
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
        return "D | " + done + " | " + this.getDescription() + " | " + this.getDeadline();
    }

    @Override
    public String toString() {
        String str = (this.taskStatus()) ? "X" : " ";
        String time = this.getDeadline();


        return "[D][" + str + "] " + description + " (by: " + time + ")";
    }



}
