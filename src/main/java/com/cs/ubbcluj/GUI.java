package com.cs.ubbcluj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefWidth(500);
        textArea.setPrefHeight(400);

        Button wifiAnalyser = new Button("Wifi Analyser");
        Button sniffingTool = new Button("Sniffing tool");

        wifiAnalyser.setOnAction(event -> wifiAnalyserAction(textArea));
        sniffingTool.setOnAction(event -> sniffingToolAction(textArea));

        VBox vBox = new VBox(wifiAnalyser, sniffingTool, textArea);
        Scene boxScene = new Scene(vBox, 500, 500);

        primaryStage.setScene(boxScene);
        primaryStage.setTitle("RDSOS tools");
        primaryStage.show();
    }

    private void wifiAnalyserAction(TextArea textArea) {
        try {
            textArea.clear();
            textArea.appendText(WifiAnalyser.analyse());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sniffingToolAction(TextArea textArea) {
            textArea.clear();
//            textArea.appendText(SniffingPackets.sniff(args));
    }
}
