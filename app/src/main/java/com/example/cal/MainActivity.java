package com.example.cal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView solutiointv,resulttv;
    MaterialButton buttonC,buttonopenbracket,buttonclosebracket;
    MaterialButton buttondivide,buttonmultiple,buttonminus,buttonadd;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonac,buttondot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.black));

        resulttv = findViewById(R.id.result_tv);
        solutiointv = findViewById(R.id.solutioin_tv);

        assignID(buttonC,R.id.button_C);
        assignID(buttonopenbracket,R.id.button_open_bracket);
        assignID(buttonclosebracket,R.id.button_close_bracket);
        assignID(buttondivide,R.id.button_divide);
        assignID(buttonmultiple,R.id.button_multiple);
        assignID(buttonminus,R.id.button_minus);
        assignID(buttonadd,R.id.button_add);
        assignID(button0,R.id.button_0);
        assignID(button1,R.id.button_1);
        assignID(button2,R.id.button_2);
        assignID(button3,R.id.button_3);
        assignID(button4,R.id.button_4);
        assignID(button5,R.id.button_5);
        assignID(button6,R.id.button_6);
        assignID(button7,R.id.button_7);
        assignID(button8,R.id.button_8);
        assignID(button9,R.id.button_9);
        assignID(button0,R.id.button_0);
        assignID(buttonac,R.id.button_ac);
        assignID(buttondot,R.id.button_dot);







    }

    void assignID(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutiointv.getText().toString();


        if(buttonText.equals("AC")){
            solutiointv.setText("");
            resulttv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutiointv.setText(resulttv.getText());
            return;
        }
        if(buttonText.equals("c")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutiointv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            resulttv.setText(finalResult);
        }

    }

    String getResult(String data){
     try {
         Context context = Context.enter();
         context.setOptimizationLevel(-1);
         Scriptable scriptable =  context.initStandardObjects();
         String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
       if (finalResult.endsWith(".0")){
           finalResult = finalResult.replace(".0","");
       }
         return finalResult;
     }catch (Exception e){
         return "Err";

     }

    }
}