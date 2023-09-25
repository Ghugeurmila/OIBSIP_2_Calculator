package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button one, two, three, four, five, six, seven, eight, nine, addition, multiply, divide, subtract, zero, allclear, dot, answer, closebrace, openbrace, cutone;
    TextView question, finalans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.question);
        finalans = findViewById(R.id.finalans);

        assignId(one, R.id.one);
        assignId(two, R.id.two);
        assignId(three, R.id.three);
        assignId(four, R.id.four);
        assignId(five, R.id.five);
        assignId(six, R.id.six);
        assignId(seven, R.id.seven);
        assignId(eight, R.id.eight);
        assignId(nine, R.id.nine);
        assignId(addition, R.id.addition);
        assignId(multiply, R.id.multiply);
        assignId(divide, R.id.divide);
        assignId(subtract, R.id.subtract);
        assignId(zero, R.id.zero);
        assignId(allclear, R.id.allclear);
        assignId(answer, R.id.answer);
        assignId(dot, R.id.dot);
        assignId(closebrace, R.id.closebrace);
        assignId(openbrace, R.id.openbrace);
        assignId(cutone, R.id.cutone);

    }

    void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button =(Button) v;
        String buttonText = button.getText().toString();
        //dataToCalculate=string for calculate the answer
        String dataToCalculate = question.getText().toString();
        if(buttonText.equals("AC")){
            question.setText("");
            finalans.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            question.setText(finalans.getText());
            finalans.setText("");
            return;
        }
        if(buttonText.equals("x")){
            if(dataToCalculate.length()==0||dataToCalculate.length()==1){
                question.setText("");
                finalans.setText("0");
                return;
            }else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        }
        else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        question.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            finalans.setText(finalResult);
        }


    }
    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}