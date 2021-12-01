package com.example.lotto;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.*;

public class HelloController {
    @FXML
    private Label numList;
    @FXML
    private Label numGen;
    @FXML
    private Button btnSorsol;
    private Timer timer;
    private List<Integer> numbers = new ArrayList<>();
    private int cycle = 0;
    private boolean sortMode = false;


    @FXML
    public void sorsolBtnClick() {
        if (!sortMode) {
            numList.setText("");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> { sorsol(); });
                }
            }, 0, 150);
        } else {
            Collections.sort(numbers);
            numList.setText("");
            for (int num : numbers) {
                addToNumList(num);
            }
            reset();
        }
    }

    private void sorsol() {
        if (cycle < 50) {
            int num = (int) (Math.random() * 90 + 1);
            numGen.setText(num + "");
            cycle++;
            if (cycle % 10 == 0) {
                while (numbers.contains(num)) {
                    num = (int) (Math.random() * 90 + 1);
                    numGen.setText(num + "");
                }
                numbers.add(num);
                addToNumList(num);
            }
        } else {
            timer.cancel();
            btnSorsol.setText("Rendez");
            sortMode = true;
        }
    }

    private void reset() {
        sortMode = false;
        btnSorsol.setText("Sorsol");
        cycle = 0;
        numbers.clear();
    }

    private void addToNumList(int num) {
        String text = numList.getText();
        if (text.length() == 0) text += num + "";
        else text += ", " + num;
        numList.setText(text);
    }
}