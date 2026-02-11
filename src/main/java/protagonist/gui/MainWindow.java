package protagonist.gui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/messi.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/protagonist.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // IMPORTANT: make dialog container match scrollpane width so bubbles don't become tall/skinny
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));
    }

    /** Injects the Duke instance */
    public void setDuke(Duke d) {
        duke = d;

        String now = LocalTime.now().format(TIME_FMT);

        // greeting bubble
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getWelcomeResponse(), now, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String response = duke.getResponse(input);
        String now = LocalTime.now().format(TIME_FMT);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, now, userImage),
                DialogBox.getDukeDialog(response, now, dukeImage)
        );
        userInput.clear();

        if (duke.shouldExit()) {
            sendButton.setDisable(true);
            userInput.setDisable(true);

            PauseTransition delay = new PauseTransition(Duration.seconds(10));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}
