package com.daneTech.percentage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1,editText2;
    Button button1,button2,button3,button4;
    TextView textView1;
    Boolean isMode1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.setBackgroundColor(Color.GREEN);
                button2.setBackgroundColor(Color.parseColor("#808080"));
                editText1.setHint("Enter a value eg: 6");
                editText2.setHint("Enter the total value eg: 10");
                isMode1 = true;
                reset();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //percentage of value
                button2.setBackgroundColor(Color.GREEN);
                button1.setBackgroundColor(Color.parseColor("#808080"));
                editText1.setHint("Enter percent eg: 60");
                editText2.setHint("Enter a value eg: 10");
                isMode1 = false;
                reset();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formula1 = "inputPercentageOfTotal =  (input1*100)/input2;";
                String formula2 = "if(input1>9)\n" +
                                  "     input1Decimal =  input1/100;\n" +
                                  "else\n" +
                                  "     input1Decimal =  input1/10;\n"+
                                  "result = input2*input1Decimal;";
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.AlertDialogCustom));
                builder.setCancelable(true);
                builder.setTitle("Formulas:");
                builder.setMessage("Formula1: \n"+formula1+"\n\nFormula2: \n"+formula2);
                builder.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1String = editText1.getText().toString();
                String input2String =editText2.getText().toString();
                if (input1String.equals("") || input2String.equals("") || isMode1 == null) {
                    Toast.makeText(getApplicationContext(), "Please complete details", Toast.LENGTH_SHORT).show();
                    return;
                }
                Double input1 = Double.parseDouble(input1String);
                Double input2 = Double.parseDouble(input2String);

                //mode 1
                if(isMode1){
                    double inputPercentageOfTotal = ((double) input1*100)/input2;
                    textView1.setText("Output: \n"+input1String+" is "+(inputPercentageOfTotal%1 == 0 ? String.valueOf((int) Math.round(inputPercentageOfTotal)):inputPercentageOfTotal)+"% of "+(input2%1==0? String.valueOf((int) Math.round(input2)):input2));
                }
                //mode 2
                else{
                    double input1Decimal,result;
                    if(input1>9)
                        input1Decimal = (double) input1/100;
                    else
                        input1Decimal = (double) input1/10;
                    result = input2*input1Decimal;
                    textView1.setText("Output: \n"+input1String+"% of "+input2String+" is "+String.valueOf(result%1==0 ? String.valueOf((int) Math.round(result)): result));
                }
                

            }
        });
    }

    public void reset(){
        editText1.setText("");
        editText2.setText("");
        textView1.setText("Output: ");
    }

    public void init(){
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        textView1 = findViewById(R.id.textView1);
        //set color
        button1.setBackgroundColor(Color.parseColor("#808080"));
        button2.setBackgroundColor(Color.parseColor("#808080"));
        button3.setBackgroundColor(Color.parseColor("#808080"));
        button4.setBackgroundColor(Color.parseColor("#808080"));
    }
}