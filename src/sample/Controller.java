package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.text.ParseException;
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

    private static final int MATRIX_SIZE = 5;

    private int[][] valuesMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];
    private int[] valuesC = new int[MATRIX_SIZE];
    private int[] valuesW = new int[MATRIX_SIZE];

    private List<ChoiceBox> choiceBoxes = new ArrayList<>();


    @FXML
    public void initialize() {
        initViews();
    }

    private void initViews() {
        for (Node child : rootContainer.getChildren()) {
            if (child instanceof ChoiceBox) {
                choiceBoxes.add((ChoiceBox) child);
                ((ChoiceBox) child).getSelectionModel().selectFirst();
            }
        }
    }

    public void fillRandomValues(ActionEvent actionEvent) {
        clearValues();
        setRandomValues();
        refreshValues();
        calculateC();
        calculateW();
    }

    private void clearValues() {
        valuesC = new int[MATRIX_SIZE];
        valuesW = new int[MATRIX_SIZE];
    }

    public void refreshValues() {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (i == j) {
                    continue;
                } else {
                    try {
                        valuesMatrix[i][j] = Integer.parseInt(choiceBoxes.get(i * MATRIX_SIZE + j).getValue().toString());
                    } catch (Exception e) {
                        int x;
                    }
                }
            }
        }
    }

    private void setRandomValues() {
        Random random = new Random();
        for (ChoiceBox choiceBox: choiceBoxes) {
            int value = (random.nextInt(11) + 1) / 10;
            choiceBox.setValue(value);
        }
    }

    private void calculateC() {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                valuesC[i] += valuesMatrix[i][j];
            }
        }
        int i = 0;
        for (Node children : containerC.getChildren()) {
            ((Label) children).setText(String.valueOf(valuesC[i++]));
        }
    }

    private void calculateW() {

    }


    public void calculate(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }
}



