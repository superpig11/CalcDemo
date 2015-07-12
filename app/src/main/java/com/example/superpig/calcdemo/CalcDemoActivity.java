package com.example.superpig.calcdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CalcDemoActivity extends Activity implements View.OnClickListener{
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_plus;
    Button btn_minus;
    Button btn_time;
    Button btn_divide;
    Button btn_equal;
    Button btn_dot;
    Button btn_del;
    Button btn_c;
    EditText et_input;
    boolean clear_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_demo);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_time = (Button) findViewById(R.id.btn_time);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_c = (Button) findViewById(R.id.btn_C);
        et_input = (EditText) findViewById(R.id.Calc_input);


        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_time.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        String str = et_input.getText().toString();
        switch (v.getId())
        {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dot:
                if (clear_flag){
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button) v).getText());
                break;
            case R.id.btn_plus:
            case R.id.btn_divide:
            case R.id.btn_time:
            case R.id.btn_minus:
                if (clear_flag){
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.btn_del:
                if (clear_flag){
                    clear_flag = false;
                    et_input.setText("");
                }else if (!str.equals("")) {
                    et_input.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.btn_C:
                clear_flag = true;
                et_input.setText("");
                break;
            case R.id.btn_equal:
                getResult();
            default:
                break;
        }
    }

    private void getResult(){
        String exp;
        exp = et_input.getText().toString();

        if (exp.equals("")){
            return;
        }else if (!exp.contains(" ")){
            return;
        }

        if (clear_flag){
            et_input.setText("");
        }

        clear_flag = true;

        String str1 = exp.substring(0, exp.indexOf(" "));
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
        String str2 = exp.substring(exp.indexOf(" ") + 3);
        double result = 0;

        if (!str1.equals("") && !str2.equals("")){
            double d1 = Double.parseDouble(str1);
            double d2 = Double.parseDouble(str2);

            if (op.equals("+")){
                result = d1 + d2;
            }else if (op.equals("-")){
                result = d1 - d2;
            }else if (op.equals("*")){
                result = d1 * d2;
            }else if (op.equals("/")){
                if (d2 == 0){
                    result = 0;
                }else{
                    result = d1 / d2;
                }
            }

            if (!str1.contains(".") && !str2.contains(".") && !op.equals("/")){
                int r = (int) result;
                et_input.setText(r + "");
            }else{
                et_input.setText(result + "");
            }
        }else if (!str1.equals("") && str2.equals("")){
            et_input.setText(exp);
        }else if (str1.equals("") && !str2.equals("")){
            double d2 = Double.parseDouble(str2);

            if (op.equals("+")){
                result = 0 + d2;
            }else if (op.equals("-")){
                result = 0 - d2;
            }else if (op.equals("*")){
                result = 0;
            }else if (op.equals("/")){
                result = 0;
            }

            if (!str2.contains(".")){
                int r = (int) result;
                et_input.setText(r + "");
            }else{
                et_input.setText(result + "");
            }
        }else if (str1.equals("") && !str2.equals("")){
            et_input.setText("");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
