package com.example.mycalc;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Boolean isFirstNull = true;
    Boolean isRes = false;
    Boolean isDot = false;
    Boolean isReverseNumb = false;
    Boolean isFieldZero = true;
    Boolean isDevideZero = false;

    String newNumb;
    String oldNumb;
    String lastNumb;
    String operator = "";
    String numb;

    Double result = 0.0;

    EditText field;

    MediaPlayer buttSound;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field = findViewById(R.id.Field);
        buttSound = MediaPlayer.create(this, R.raw.buttsound);
    }

    @SuppressLint("SetTextI18n")

    public void clckNumb(View view) {
        buttSound.start();
        if (isRes){
            field.setText("0");
            operator = "";
            oldNumb = "";
            newNumb = "";
            isFirstNull = true;
            isDot = false;
            isRes = false;
            isFieldZero = true;
        }
        if (isFirstNull & view.getId() != R.id.buttReverseSign){
            field.setText("");
            isFirstNull = false;
        }
        if (isFieldZero & view.getId() != R.id.butt0){
            isFieldZero = false;
        }
        numb = field.getText().toString();
        if (view.getId() == R.id.butt0){
            if (isFieldZero) {field.setText("0"); isFirstNull = true;} else {field.setText(numb + "0");}
        } else if (view.getId() == R.id.butt1) {
            field.setText(numb + "1");
        } else if (view.getId() == R.id.butt2) {
            field.setText(numb + "2");
        } else if (view.getId() == R.id.butt3) {
            field.setText(numb + "3");
        } else if (view.getId() == R.id.butt4) {
            field.setText(numb + "4");
        } else if (view.getId() == R.id.butt5) {
            field.setText(numb + "5");
        } else if (view.getId() == R.id.butt6) {
            field.setText(numb + "6");
        } else if (view.getId() == R.id.butt7) {
            field.setText(numb + "7");
        } else if (view.getId() == R.id.butt8) {
            field.setText(numb + "8");
        } else if (view.getId() == R.id.butt9) {
            field.setText(numb + "9");
        } else if (view.getId() == R.id.buttDot & !isDot){
            field.setText(numb + ".");
            if (field.getText().toString().equals(".")){
                field.setText("0.");
            }
            isDot = true;
        } else if (view.getId() == R.id.buttReverseSign & isReverseNumb & !field.getText().toString().equals("0") & !field.getText().toString().equals("0.")){
            field.setText(numb.substring(1));
            isReverseNumb = false;
        } else if (view.getId() == R.id.buttReverseSign & !field.getText().toString().equals("0") & !field.getText().toString().equals("0.")){
            field.setText("-" + numb);
            isReverseNumb = true;
        }

    }

    public void mathAction(View view) {
        buttSound.start();
        isFirstNull = true;
        isRes = false;
        isDot = false;
        oldNumb = field.getText().toString();
        if (view.getId() == R.id.buttPlus) {
            operator = "+";
        } else if (view.getId() == R.id.buttMinus) {
            operator = "-";
        } else if (view.getId() == R.id.buttDevide) {
            operator = "/";
        } else if (view.getId() == R.id.butt1DevideX) {
            operator = "1/x";
        } else if (view.getId() == R.id.buttMultiply) {
            operator = "*";
        } else if (view.getId() == R.id.buttSqrtX) {
            operator = "√";
        } else if (view.getId() == R.id.buttXsquared2) {
            operator = "**2";
        } else if (view.getId() == R.id.buttXsquaredK) {
        operator = "**k";
        }

    }

    @SuppressLint("SetTextI18n")
    public void clckRes(View view) {
        buttSound.start();
        newNumb = field.getText().toString();
        switch (operator) {
            case "+":
                result = Double.parseDouble(oldNumb) + Double.parseDouble(newNumb);
                break;
            case "-":
                result = Double.parseDouble(oldNumb) - Double.parseDouble(newNumb);
                break;
            case "/":
                if (Double.parseDouble(newNumb) == 0){
                    isDevideZero = true;
                    result = Double.parseDouble(oldNumb) / Double.parseDouble(newNumb);
                } else {
                    result = Double.parseDouble(oldNumb) / Double.parseDouble(newNumb);
                }
                break;
            case "1/x":
                result = 1 / Double.parseDouble(newNumb);
                break;
            case "*":
                result = Double.parseDouble(oldNumb) * Double.parseDouble(newNumb);
                break;
            case "√":
                result = Math.sqrt(Double.parseDouble(oldNumb));
                break;
            case "**2":
                result = Math.pow(Double.parseDouble(oldNumb), 2);
                break;
            case "**k":
                result = Math.pow(Double.parseDouble(oldNumb), Double.parseDouble(newNumb));
                break;
        }
        if (isDevideZero){
            field.setText(result.toString());
            Toast.makeText(MainActivity.this, "На ноль делить нельзя", Toast.LENGTH_SHORT).show();
            isDevideZero = false;
        } else {
            field.setText(result.toString());
        }
        isRes = true;

    }

    public void clckClear(View view) {
        buttSound.start();
        if (view.getId() == R.id.buttCE){
            lastNumb = field.getText().toString();
            field.setText("0");
            isFirstNull = true;
            isDot = false;
            isFieldZero = true;
            if (lastNumb.equals(oldNumb)){
                oldNumb = "";
            } else if (lastNumb.equals(newNumb)){
                newNumb = "";
            }
        } else if (view.getId() == R.id.buttC){
            field.setText("0");
            operator = "";
            oldNumb = "";
            newNumb = "";
            isFirstNull = true;
            isDot = false;
            isFieldZero = true;
        }

    }

    @SuppressLint("SetTextI18n")
    public void clckPrecent(View view) {
        buttSound.start();
        if (operator.isEmpty()){
            String numb = field.getText().toString();
            double temp = Double.parseDouble(numb) / 100;
            field.setText(temp + "");
            isRes = true;
        } else {
            newNumb = field.getText().toString();
            switch (operator) {
                case "+":
                    result = Double.parseDouble(oldNumb) + (Double.parseDouble(oldNumb) / 100 * Double.parseDouble(newNumb));
                    break;
                case "-":
                    result = Double.parseDouble(oldNumb) - (Double.parseDouble(oldNumb) / 100 * Double.parseDouble(newNumb));
                    break;
                case "/":
                    result = Double.parseDouble(oldNumb) / (Double.parseDouble(oldNumb) / 100 * Double.parseDouble(newNumb));
                    break;
                case "*":
                    result = Double.parseDouble(oldNumb) * (Double.parseDouble(oldNumb) / 100 * Double.parseDouble(newNumb));
                    break;
                case "**k":
                    result = Math.pow(Double.parseDouble(oldNumb), Double.parseDouble(oldNumb) / 100 * Double.parseDouble(newNumb));
                    break;
            }
            field.setText(result.toString());
            isRes = true;
        }

    }
}