module org.example.mongodbdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;


    opens org.example.mongodbdemo to javafx.fxml;
    exports org.example.mongodbdemo;
}