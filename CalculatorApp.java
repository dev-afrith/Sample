import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private TextField display;
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    @Override
    public void start(Stage primaryStage) {
        display = new TextField();
        display.setEditable(false);
        display.setMinSize(200, 50);
        display.setStyle("-fx-font-size: 20px;");

        GridPane grid = new GridPane();
        grid.add(display, 0, 0, 4, 1);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        int row = 1;
        int col = 0;

        for (String label : buttons) {
            Button button = new Button(label);
            button.setMinSize(50, 50);
            button.setStyle("-fx-font-size: 18px;");
            button.setOnAction(e -> handleButton(label));
            grid.add(button, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        Scene scene = new Scene(grid, 220, 270);
        primaryStage.setTitle("JavaFX Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButton(String label) {
        if (label.matches("[0-9]")) {
            if (startNewNumber) {
                display.setText(label);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + label);
            }
        } else if (label.matches("[+\\-*/]")) {
            firstOperand = Double.parseDouble(display.getText());
            operator = label;
            startNewNumber = true;
        } else if (label.equals("=")) {
            double secondOperand = Double.parseDouble(display.getText());
            double result = calculate(firstOperand, secondOperand, operator);
            display.setText(String.valueOf(result));
            startNewNumber = true;
        } else if (label.equals("C")) {
            display.clear();
            firstOperand = 0;
            operator = "";
            startNewNumber = true;
        }
    }

    private double calculate(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> (b == 0) ? 0 : a / b;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
