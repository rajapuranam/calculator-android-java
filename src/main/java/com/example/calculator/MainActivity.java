package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button one, two, three, four, five, six, seven, eight, nine, zero, doubleZero, dot, clear, plusminus, remainder, addition, substraction, multiplication, division, equal;
    private String input;
    private TextView result1, result2;
    private char[] stringToCharArray;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.back_gradient));

        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);

        one = findViewById(R.id.one); one.setOnClickListener(this);
        two = findViewById(R.id.two); two.setOnClickListener(this);
        three = findViewById(R.id.three); three.setOnClickListener(this);
        four = findViewById(R.id.four); four.setOnClickListener(this);
        five = findViewById(R.id.five); five.setOnClickListener(this);
        six = findViewById(R.id.six); six.setOnClickListener(this);
        seven = findViewById(R.id.seven); seven.setOnClickListener(this);
        eight = findViewById(R.id.eight); eight.setOnClickListener(this);
        nine = findViewById(R.id.nine); nine.setOnClickListener(this);
        zero = findViewById(R.id.zero); zero.setOnClickListener(this);
        doubleZero = findViewById(R.id.doublezero); doubleZero.setOnClickListener(this);
        dot = findViewById(R.id.dot); dot.setOnClickListener(this);

        clear = findViewById(R.id.clear); clear.setOnClickListener(this);
        plusminus = findViewById(R.id.plusminus); plusminus.setOnClickListener(this);

        addition = findViewById(R.id.buttonadd); addition.setOnClickListener(this);
        substraction = findViewById(R.id.buttonsub); substraction.setOnClickListener(this);
        multiplication = findViewById(R.id.buttonmul); multiplication.setOnClickListener(this);
        division = findViewById(R.id.buttondiv); division.setOnClickListener(this);
        remainder = findViewById(R.id.buttonrem); remainder.setOnClickListener(this);

        equal = findViewById(R.id.buttoneql); equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String temp;
        if(view.getId() == R.id.clear){
            try {
                StringBuffer sb= new StringBuffer(result2.getText());
                sb.deleteCharAt(sb.length()-1);
                result1.setText("0");
                result2.setText(sb.toString());
            } catch(Exception err){}
        } else if(view.getId() == R.id.plusminus){
            try {
                temp = result2.getText().toString();
                if(temp != null && temp.length() != 0){
                    if(temp.charAt(0) != '-')
                        result2.setText("-"+temp);
                    else
                        result2.setText(temp.substring(1, temp.length()));
                }
            } catch(Exception err){}
        } else if(view.getId() == R.id.buttoneql){
            try {
                input = result2.getText().toString();
                if (input.length() != 0) {
                    stringToCharArray = input.toCharArray();
                    input = "";
                    for (char oper : stringToCharArray)
                        if (!Character.isDigit(oper) && oper != '.')
                            input += " " + oper + " ";
                        else
                            input += oper;
                    input.trim();
                    double d = ExpressionEvaluator.eval(input);
                    int length_double_d = String.valueOf(d).length();
                    int length_int_d = String.valueOf((int)Math.round(d)).length();
                    d = new BigDecimal(d).setScale(9, RoundingMode.HALF_EVEN).doubleValue();
                    if(d == (long) d)
                        result1.setText(String.format("%d",(long)d));
                    else
                        result1.setText(String.format("%s",d));
                } else
                    Toast.makeText(this, "Enter values..", Toast.LENGTH_SHORT).show();
            } catch(UnsupportedOperationException err) {
                Toast.makeText(this, "Cannot Divide by Zero!!", Toast.LENGTH_SHORT).show();
            } catch(Exception err){
                System.out.println("Error: " + err.getMessage());
                System.out.println("Error: " + err.getClass());
            }
        } else {
            switch (view.getId()){
                case R.id.one:
                    result2.setText(result2.getText() + "1");
                    break;
                case R.id.two:
                    result2.setText(result2.getText() + "2");
                    break;
                case R.id.three:
                    result2.setText(result2.getText() + "3");
                    break;
                case R.id.four:
                    result2.setText(result2.getText() + "4");
                    break;
                case R.id.five:
                    result2.setText(result2.getText() + "5");
                    break;
                case R.id.six:
                    result2.setText(result2.getText() + "6");
                    break;
                case R.id.seven:
                    result2.setText(result2.getText() + "7");
                    break;
                case R.id.eight:
                    result2.setText(result2.getText() + "8");
                    break;
                case R.id.nine:
                    result2.setText(result2.getText() + "9");
                    break;
                case R.id.zero:
                    result2.setText(result2.getText() + "0");
                    break;
                case R.id.doublezero:
                    result2.setText(result2.getText() + "00");
                    break;
                case R.id.dot:
                    temp = result2.getText().toString();
                    if(temp.charAt(temp.length() - 1) != '.')
                        result2.setText(result2.getText() + ".");
                    break;
                case R.id.buttonadd:
                    temp = result2.getText().toString();
                    if(temp != null && temp.length() != 0)
                        if (Character.isDigit(temp.charAt(temp.length()-1)))
                            result2.setText(result2.getText() + "+");
                    break;
                case R.id.buttonsub:
                    temp = result2.getText().toString();
                    if(temp != null && temp.length() != 0)
                        if (Character.isDigit(temp.charAt(temp.length()-1)))
                            result2.setText(result2.getText() + "-");
                    break;
                case R.id.buttonmul:
                    temp = result2.getText().toString();
                    if(temp != null && temp.length() != 0)
                        if (Character.isDigit(temp.charAt(temp.length()-1)))
                            result2.setText(result2.getText() + "*");
                    break;
                case R.id.buttondiv:
                    temp = result2.getText().toString();
                    if(temp != null && temp.length() != 0)
                        if (Character.isDigit(temp.charAt(temp.length()-1)))
                            result2.setText(result2.getText() + "/");
                    break;
                case R.id.buttonrem:
                    temp = result2.getText().toString();
                    if(temp != null && temp.length() != 0)
                        if (Character.isDigit(temp.charAt(temp.length()-1)))
                            result2.setText(result2.getText() + "%");
                    break;
                default:
                    Toast.makeText(this, "Default block in switch case!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}