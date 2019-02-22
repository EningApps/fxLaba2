package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Controller {

    @FXML
    private AnchorPane rootContainer;
    @FXML
    private Button randomButton;
    @FXML
    private Button calculateButton;
    @FXML
    private Button exitButton;

    @FXML
    private Pane containerC;

    @FXML
    private Pane containerW;

    public void fillRandomValues(ActionEvent actionEvent) {
        List<ChoiceBox> choiceBoxes = new ArrayList<>();
        for (Node child : rootContainer.getChildren()) {
            if (child instanceof ChoiceBox) {
                choiceBoxes.add((ChoiceBox) child);
            }
        }
        Random random = new Random();
        for (ChoiceBox choiceBox : choiceBoxes) {
            int value = (random.nextInt(11)+1)/10;
            choiceBox.setValue(value);
        }
    }

    public void calculate(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }
}



