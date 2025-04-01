package com.example.hwlesson4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        result = findViewById(R.id.result);
        result.setText("0");

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn_point
        };

        View.OnClickListener listener = view -> {
            Button button = (Button) view;
            String buttonText = button.getText().toString();

            if (isNewInput) {
                currentInput = "";
                isNewInput = false;
            }

            if (buttonText.equals(".")) {
                if (currentInput.isEmpty() || currentInput.contains(".")) {
                    return; // Предотвращаем множественные точки
                }
            }

            currentInput += buttonText;
            result.setText(currentInput);
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtonIds = {R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply, R.id.btn_divide};

        View.OnClickListener listener = view -> {
            if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                Button button = (Button) view;
                operator = button.getText().toString();
                isNewInput = true;
            }
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.btn_equal).setOnClickListener(view -> {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double secondNumber = Double.parseDouble(currentInput);
                double resultValue = 0;

                switch (operator) {
                    case "+": resultValue = firstNumber + secondNumber; break;
                    case "-": resultValue = firstNumber - secondNumber; break;
                    case "*": resultValue = firstNumber * secondNumber; break;
                    case "/":
                        if (secondNumber != 0) {
                            resultValue = firstNumber / secondNumber;
                        } else {
                            result.setText("Error");
                            return;
                        }
                        break;
                }

                result.setText(formatResult(resultValue));
                currentInput = String.valueOf(resultValue);
                operator = "";
                isNewInput = true;
            }
        });

        findViewById(R.id.btn_clear).setOnClickListener(view -> {
            currentInput = "";
            operator = "";
            firstNumber = 0;
            isNewInput = true;
            result.setText("0");
        });

        findViewById(R.id.btn_backspace).setOnClickListener(view -> {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                result.setText(currentInput.isEmpty() ? "0" : currentInput);
            }
        });
    }

    private String formatResult(double value) {
        if (value == (int) value) {
            return String.valueOf((int) value);
        } else {
            return String.valueOf(value);
        }
    }
}
