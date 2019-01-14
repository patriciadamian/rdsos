package com.cs.ubbcluj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GUI extends Application {

    ExecutorService executorService = Executors.newFixedThreadPool(1);
    boolean resetTextArea = false;

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
        sniffingTool.setOnAction(event -> {
            resetTextArea = true;
            sniffingToolAction(textArea, resetTextArea);
        });

        VBox vBox = new VBox(wifiAnalyser, sniffingTool, textArea);
        Scene boxScene = new Scene(vBox, 500, 500);

        primaryStage.setScene(boxScene);
        primaryStage.setTitle("RDSOS tools");
        primaryStage.show();
    }

    private void wifiAnalyserAction(TextArea textArea) {
        executorService.shutdownNow();
        try {
            textArea.clear();
            textArea.appendText(WifiAnalyser.analyse());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sniffingToolAction(TextArea textArea, boolean resetTextArea) {
        if (resetTextArea) {
            textArea.clear();
        }
        try {
            SnifferTask task = new SnifferTask();
            task.setOnSucceeded((succeededEvent) -> {
                    textArea.appendText(task.getValue().toString());

                sniffingToolAction(textArea, false);
            });
            executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
            textArea.appendText("Result");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
