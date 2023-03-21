package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button zeroButton;
    private Button sumButton;
    private Button subtractButton;
    private Button multiplyButton;
    private Button divideButton;
    private Button sqrtButton;
    private Button minusDegreeButton;
    private Button invertButton;
    private Button commaButton;
    private Button clearButton;
    private Button equalsButton;

    private TextView textRes;
    private TextView textRes2;

    private double value1 = Double.NaN;
    private double value2;

    private Operation currentOperation;

    private boolean newNumber = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setupView();

        oneButton.setOnClickListener(v -> {
            inputDigit("1");
        });

        twoButton.setOnClickListener(v -> {
            inputDigit("2");
        });

        threeButton.setOnClickListener(v -> {
            inputDigit("3");
        });

        fourButton.setOnClickListener(v -> {
            inputDigit("4");
        });

        fiveButton.setOnClickListener(v -> {
            inputDigit("5");
        });

        sixButton.setOnClickListener(v -> {
            inputDigit("6");
        });

        sevenButton.setOnClickListener(v -> {
            inputDigit("7");
        });

        eightButton.setOnClickListener(v -> {
            inputDigit("8");
        });

        nineButton.setOnClickListener(v -> {
            inputDigit("9");
        });

        zeroButton.setOnClickListener(v -> {
            inputDigit("0");
        });

        sumButton.setOnClickListener(v -> {
            if (textRes.getText().length() > 0) {
                operation();
                currentOperation = Operation.SUM;
                textRes2.setText(toIntIfPossible(value1) + " " + currentOperation.sign + " ");
//                textRes.setText("");
            }
        });

        subtractButton.setOnClickListener(v -> {
            if (textRes.getText().length() > 0) {
                operation();
                currentOperation = Operation.SUB;
                textRes2.setText(toIntIfPossible(value1) + " " + currentOperation.sign + " ");
//                textRes.setText("");
            }
        });

        divideButton.setOnClickListener(v -> {
            if (textRes.getText().length() > 0) {
                operation();
                currentOperation = Operation.DIV;
                textRes2.setText(toIntIfPossible(value1) + " " + currentOperation.sign + " ");
//                textRes.setText("");
            }
        });

        multiplyButton.setOnClickListener(v -> {
            if (textRes.getText().length() > 0) {
                operation();
                currentOperation = Operation.MULT;
                textRes2.setText(toIntIfPossible(value1) + " " + currentOperation.sign + " ");
//                textRes.setText("");
            }
        });

        equalsButton.setOnClickListener(v -> {
            if (currentOperation == Operation.EQU) return;
            if (textRes.getText().length() > 0) {
                textRes2.append(textRes.getText() + " =");
                operation();
                currentOperation = Operation.EQU;
                textRes.setText(String.valueOf(toIntIfPossible(value1)));
                value1 = Double.NaN;
            }
        });

        sqrtButton.setOnClickListener(v -> {
            if (textRes.getText().length() > 0) {
                textRes.setText(String.valueOf(toIntIfPossible(Math.sqrt(Double.parseDouble(textRes.getText().toString())))));
            }
        });

        minusDegreeButton.setOnClickListener(v -> {
            if (textRes.getText().length() > 0) {
                textRes.setText(String.valueOf(toIntIfPossible(1.0 / Double.parseDouble(textRes.getText().toString()))));
            }
        });

        invertButton.setOnClickListener(v -> {
            if (textRes.getText().length() > 0) {
                textRes.setText(String.valueOf(toIntIfPossible(Double.parseDouble(textRes.getText().toString()) * -1.0)));
            }
        });

        commaButton.setOnClickListener(v -> {
            if (textRes.getText().toString().indexOf('.') > 0) return;
            if (textRes.getText().length() > 0) {
                textRes.append(".");
            } else {
                textRes.append("0.");
                newNumber = false;
            }
        });

        clearButton.setOnClickListener(v -> {
            value1 = Double.NaN;
            value2 = Double.NaN;
            textRes.setText("");
            textRes2.setText("");
            newNumber = true;
        });
    }

    private Number toIntIfPossible(double number) {
        if (isInt(number)) {
            return (int) number;
        } else {
            return number;
        }
    }

    private boolean isInt(double number) {
        return number % 1 == 0;
    }

    private void inputDigit(String digit) {
        if (newNumber || textRes.getText().toString().equals("0")) {
            textRes.setText(digit);
            newNumber = false;
        } else {
            textRes.append(digit);
        }
    }

    private void operation() {
        if (!Double.isNaN(value1)) {
//            if (textRes2.getText().toString().charAt(0) == '-') {
//                value1 = (-1) * value1;
//            }
            value2 = Double.parseDouble(textRes.getText().toString());

            switch (currentOperation) {
                case SUM:
                    value1 = value1 + value2;
                    break;
                case SUB:
                    value1 = value1 - value2;
                    break;
                case MULT:
                    value1 = value1 * value2;
                    break;
                case DIV:
                    value1 = value1 / value2;
                    break;
                case EQU:
                    break;
            }
        } else {
            value1 = Double.parseDouble(textRes.getText().toString());
        }

        newNumber = true;
    }

    private void setupView() {
        this.textRes = findViewById(R.id.result_textview);
        this.textRes2 = findViewById(R.id.result_textview2);
        this.oneButton = findViewById(R.id.one_button);
        this.twoButton = findViewById(R.id.two_button);
        this.threeButton = findViewById(R.id.three_button);
        this.fourButton = findViewById(R.id.four_button);
        this.fiveButton = findViewById(R.id.five_button);
        this.sixButton = findViewById(R.id.six_button);
        this.sevenButton = findViewById(R.id.seven_button);
        this.eightButton = findViewById(R.id.eight_button);
        this.nineButton = findViewById(R.id.nine_button);
        this.zeroButton = findViewById(R.id.zero_button);
        this.sumButton = findViewById(R.id.sum_button);
        this.subtractButton = findViewById(R.id.minus_button);
        this.multiplyButton = findViewById(R.id.multiply_button);
        this.divideButton = findViewById(R.id.divide_button);
        this.sqrtButton = findViewById(R.id.sqrt_button);
        this.minusDegreeButton = findViewById(R.id.minus_degree_button);
        this.invertButton = findViewById(R.id.invert_button);
        this.commaButton = findViewById(R.id.comma_button);
        this.clearButton = findViewById(R.id.clear_button);
        this.equalsButton = findViewById(R.id.equal_button);
    }

    private enum Operation {
        SUM("+"),
        SUB("-"),
        MULT("*"),
        DIV("/"),
        EQU("=");

        private final String sign;

        Operation(String sign) {
            this.sign = sign;
        }
    }
}