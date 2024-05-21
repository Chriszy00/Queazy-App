package com.quiz.quizapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.quiz.quizapp.model.Question;

public class QuizController {

    @FXML
    public Label question;

    @FXML
    public Button opt1, opt2, opt3, opt4;

    static int counter = 0;
    static int correct = 0;
    static int wrong = 0;

    private List<Question> questions;
    private String category;

    public void setCategory(String category) {
        this.category = category;
        loadQuestionsFromFile();
        loadQuestions();
    }

    private void loadQuestionsFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String fileName = "src/main/resources/json/" + category + ".json";
            questions = objectMapper.readValue(new File(fileName), new TypeReference<List<Question>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQuestions() {
        if (counter < questions.size()) {
            Question currentQuestion = questions.get(counter);
            question.setText((counter + 1) + ". " + currentQuestion.getQuestion());
            opt1.setText(currentQuestion.getOptions().get(0));
            opt2.setText(currentQuestion.getOptions().get(1));
            opt3.setText(currentQuestion.getOptions().get(2));
            opt4.setText(currentQuestion.getOptions().get(3));
        }
    }

    private boolean checkAnswer(String answer) {
        return questions.get(counter).getAnswer().equals(answer);
    }

    private void handleOptionClick(ActionEvent event, Button button) {
        if (checkAnswer(button.getText())) {
            correct++;
        } else {
            wrong++;
        }
        if (counter == questions.size() - 1) {
            showResult(event);
        } else {
            counter++;
            loadQuestions();
        }
    }

    private void showResult(ActionEvent event) {
        try {
            System.out.println(correct);
            Stage thisStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            thisStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quiz/quizapp/result.fxml"));
            Scene scene = new Scene(loader.load());
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void opt1clicked(ActionEvent event) {
        handleOptionClick(event, opt1);
    }

    @FXML
    public void opt2clicked(ActionEvent event) {
        handleOptionClick(event, opt2);
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        handleOptionClick(event, opt3);
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        handleOptionClick(event, opt4);
    }
}
