package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView textHitung;
    TextView textHasil;

    String hitung = "";
    String formula = "";
    String tempFormula = "";

    boolean kurungBuka = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews() {
        textHasil = (TextView) findViewById(R.id.textHasil);
        textHitung = (TextView) findViewById(R.id.textHitung);
    }

    private void setHitung(String givenValue) {
        hitung = hitung + givenValue;
        textHitung.setText(hitung);
    }

    public void kurungOnClick(View view) {
        if(kurungBuka)
        {
            setHitung("(");
            kurungBuka = false;
        }else
        {
            setHitung(")");
            kurungBuka = true;
        }
    }

    public void deleteOnClick(View view) {
    }

    public void equalOnClick(View view) {
        Double hasil = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        cekPangkat();

        try {
            hasil = (double)engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText( this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(hasil != null)
            textHasil.setText(String.valueOf(hasil.doubleValue()));
    }

    private void cekPangkat() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < hitung.length(); i++)
        {
            if (hitung.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = hitung;
        tempFormula = hitung;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;

    }

    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";

        for (int i = index + 1; i < hitung.length(); i++)
        {
            if(isNumeric(hitung.charAt(i)))
                numberRight = numberRight + hitung.charAt(i);
            else
                break;
        }

        for (int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(hitung.charAt(i)))
                numberLeft = numberLeft + hitung.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original, changed);

    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }

    public  void clearOnClick(View view) {
        textHitung.setText("");
        hitung = "";
        textHasil.setText("");
        kurungBuka = true;
    }

    public void eightOnClick(View view) {
        setHitung("8");
    }

    public void sevenOnClick(View view) {
        setHitung("7");
    }

    public void pangkatOnClick(View view) {
        setHitung("^");
    }

    public void divideOnClick(View view) {
        setHitung("/");
    }

    public void fourOnClick(View view) {
        setHitung("4");
    }

    public void oneOnClick(View view) {
        setHitung("1");
    }

    public void nineOnClick(View view) {
        setHitung("9");
    }

    public void timesOnClick(View view) {
        setHitung("*");
    }

    public void fiveOnClick(View view) {
        setHitung("5");
    }

    public void sixOnClick(View view) {
        setHitung("6");
    }

    public void minusOnClick(View view) {
        setHitung("-");
    }

    public void twoOnClick(View view) {
        setHitung("2");
    }

    public void threeOnClick(View view) {
        setHitung("3");
    }

    public void plusOnClick(View view) {
        setHitung("+");
    }

    public void zeroOnClick(View view) {
        setHitung("0");
    }

    public void dotOnClick(View view) {
        setHitung(".");
    }

}