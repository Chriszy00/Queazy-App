package com.quiz.quizapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CategoriesController {

    @FXML
    private Button mathBtn;

    @FXML
    private Button sciBtn;

    @FXML
    private Button geoBtn;

    @FXML
    private void initialize() {

        mathBtn.setOnAction(event -> loadQuiz(event, "math"));
        sciBtn.setOnAction(event -> loadQuiz(event, "science"));
        geoBtn.setOnAction(event -> loadQuiz(event, "geography"));
    }

    private void loadQuiz(ActionEvent event, String category) {
        try {
            Stage thisStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            thisStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quiz/quizapp/quiz.fxml"));
            Scene scene = new Scene(loader.load());

            QuizController controller = loader.getController();
            controller.setCategory(category);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
