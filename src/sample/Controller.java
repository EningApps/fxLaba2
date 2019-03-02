package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.*;

public class Controller {

    @FXML
    private GridPane valuesContainer;
    @FXML
    private TextArea resultText;

    @FXML
    private Spinner<Integer> rSpinner1;

    @FXML
    private Spinner<Integer> rSpinner2;

    @FXML
    private Pane containerW;

    private static final int MATRIX_ROWS = 3;
    private static final int MATRIX_COLUMNS = 2;

    private static final int R1_DEFAULT = 7;
    private static final int R2_DEFAULT = 8;

    private int[][] valuesMatrix = new int[MATRIX_ROWS][MATRIX_ROWS];
    private int[] valuesC = new int[MATRIX_ROWS];
    private Float[] valuesW = new Float[MATRIX_ROWS];

    @FXML
    public void initialize() {
        initViews();
    }

    private void initViews() {
        rSpinner1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, R1_DEFAULT));
        rSpinner2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, R2_DEFAULT));
    }


    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void calculate() {
        clearValues();
        calculateW();
        setValuesW();
        setResult();
    }

    private void clearValues() {
        valuesW = new Float[MATRIX_ROWS];
    }

    private void calculateW() {
        int r1 = rSpinner1.getValue();
        int r2 = rSpinner2.getValue();
        float z1 = r1 * 1f / (r1 + r2);
        float z2 = r2 * 1f / (r1 + r2);
        for (int i = 0; i < MATRIX_ROWS; i++) {
            float value1 = Float.parseFloat(getTextAreaFromGrid(i, 0).getText());
            float value2 = Float.parseFloat(getTextAreaFromGrid(i, 1).getText());
            float result = value1 * z1 + value2 * z2;
            valuesW[i] = result;
        }
    }

    private void setValuesW() {
        int i = 0;
        for (Node wLabel : containerW.getChildren()) {
            ((Label) wLabel).setText(String.format("%.4f",valuesW[i++]));
        }
    }

    private void setResult() {
        List<Pair<Integer, Float>> valuesToIndex = new ArrayList();
        for (int i = 0; i < valuesW.length; i++) {
            valuesToIndex.add(new Pair(i + 1, valuesW[i]));
        }
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(valuesToIndex, new Comparator<Pair<Integer, Float>>() {
            @Override
            public int compare(Pair<Integer, Float> pair1, Pair<Integer, Float> pair2) {
                return Float.compare(pair2.getValue(), pair1.getValue());
            }
        });
        for (int i = 0; i < valuesToIndex.size(); i++) {
            stringBuilder.append(String.format("Z%d = %.4f ; ", valuesToIndex.get(i).getKey(), valuesToIndex.get(i).getValue()));
        }
        resultText.setText(stringBuilder.toString());
    }

    private TextArea getTextAreaFromGrid(int row, int col) {
        for (Node node : valuesContainer.getChildren()) {
            if (valuesContainer.getColumnIndex(node) == col && valuesContainer.getRowIndex(node) == row && node instanceof TextArea) {
                return (TextArea) node;
            }
        }
        return null;
    }

}



