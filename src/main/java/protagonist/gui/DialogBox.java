package protagonist.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label time;

    @FXML
    private GridPane bubble;

    private DialogBox(String text, String timeText, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        time.setText(timeText);

        displayPicture.setImage(img);
        displayPicture.setFitWidth(80);
        displayPicture.setFitHeight(80);
        displayPicture.setClip(new Circle(40, 40, 40));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);

        bubble.getStyleClass().add("reply-bubble");
    }

    public static DialogBox getUserDialog(String text, String timeText, Image img) {
        return new DialogBox(text, timeText, img);
    }

    public static DialogBox getDukeDialog(String text, String timeText, Image img) {
        var db = new DialogBox(text, timeText, img);
        db.flip();
        return db;
    }
}
