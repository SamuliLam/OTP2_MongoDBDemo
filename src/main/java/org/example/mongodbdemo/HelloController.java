package org.example.mongodbdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.bson.Document;

public class HelloController {
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField cityField;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button readButton;

    @FXML
    public void initialize() {
        addButton.setOnAction(event -> addDocument());
        updateButton.setOnAction(event -> updateDocument());
        deleteButton.setOnAction(event -> deleteDocument());
        readButton.setOnAction(event -> readDocument());
    }

    private void addDocument() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String city = cityField.getText();
        QuickStart.addDocument(name, age, city);
        showAlert("Document added successfully", null);
    }

    private void updateDocument() {
        String id = idField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String city = cityField.getText();
        QuickStart.updateDocument(id, name, age, city);
        showAlert("Document updated successfully", null);
    }

    private void deleteDocument() {
        String id = idField.getText();
        QuickStart.deleteDocument(id);
        showAlert("Document deleted successfully", null);
    }

    private void readDocument() {
        String id = idField.getText();
        Document doc = QuickStart.readDocumentById(id);
        if (doc != null) {
            nameField.setText(doc.getString("name"));
            ageField.setText(String.valueOf(doc.getInteger("age")));
            cityField.setText(doc.getString("city"));
            showAlert("Document read successfully", doc.toJson());
        } else {
            showAlert("No matching documents found", null);
        }
    }

    private void showAlert(String message, String details) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message + (details != null ? "\n\nDetails:\n" + details : ""));
        alert.showAndWait();
    }
}