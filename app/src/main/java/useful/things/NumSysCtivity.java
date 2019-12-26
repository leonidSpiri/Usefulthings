package useful.things;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;

public class NumSysCtivity extends AppCompatActivity {
    EditText vvodtxt;
    TextView txt0;
    TextView txt1;
    TextView txt2;
    TextView txtotvet;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numsys);
        vvodtxt=(EditText)findViewById(R.id.vvodtxt);
        txt0=(TextView)findViewById(R.id.txt0);
        txt1=(TextView)findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        txtotvet=(TextView)findViewById(R.id.txtotvet);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        vvodtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vvodtxt.setText("");
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (vvodtxt.getText().toString().equals("") || vvodtxt.getText().toString().equals(getResources().getString(R.string.VvodNextNumSys))){
                    Toast.makeText(NumSysCtivity.this, "Вы ничего не ввели", Toast.LENGTH_SHORT).show();

                }else {
                    BigInteger dec;
                    switch (checkedId) {
                        case R.id.rbbinary:
                            try {
                                dec =new BigInteger(new BigInteger(String.valueOf(Integer.parseInt(vvodtxt.getText().toString()))).toString(10),2);
                                txtotvet.setText(getResources().getString(R.string.in_the_binary_system)+" "+getResources().getString(R.string.Number)+" " + vvodtxt.getText().toString()+" "+getResources().getString(R.string.Equal) +":");
                                txt0.setText(Integer.toOctalString(Integer.parseInt(dec.toString()))+ " "+getResources().getString(R.string.in_the_octal_system));
                                txt1.setText(Integer.toHexString(Integer.parseInt(dec.toString())).toUpperCase()+ " "+getResources().getString(R.string.in_the_hexadecimal_system));
                                txt2.setText(String.valueOf(dec)+ " "+getResources().getString(R.string.in_the_decimal_system));
                            }
                            catch (Exception e) {
                                Toast.makeText(NumSysCtivity.this, getResources().getText(R.string.errnumsys), Toast.LENGTH_SHORT).show();
                            }

                            break;
                        case R.id.rbdecimal:
                            try {
                            txtotvet.setText(getResources().getString(R.string.in_the_decimal_system)+" "+getResources().getString(R.string.Number)+" " + vvodtxt.getText().toString()+" "+getResources().getString(R.string.Equal) +":");
                            txt0.setText(Integer.toBinaryString(Integer.parseInt(vvodtxt.getText().toString())) + " "+getResources().getString(R.string.in_the_binary_system));
                            txt1.setText(Integer.toOctalString(Integer.parseInt(vvodtxt.getText().toString()))+ " "+getResources().getString(R.string.in_the_octal_system));
                            txt2.setText(Integer.toHexString(Integer.parseInt(vvodtxt.getText().toString())).toUpperCase()+ " "+getResources().getString(R.string.in_the_hexadecimal_system));
                            }
                            catch (Exception e) {
                                Toast.makeText(NumSysCtivity.this, getResources().getText(R.string.errnumsys), Toast.LENGTH_SHORT).show();
                            }

                            break;
                        case R.id.rbhex:
                            try {
                            dec = BigInteger.valueOf(hextodec(vvodtxt.getText().toString().toUpperCase()));
                            txtotvet.setText(getResources().getString(R.string.Hexadecimal)+" "+getResources().getString(R.string.Number)+" " + vvodtxt.getText().toString().toUpperCase()+" "+getResources().getString(R.string.Equal) +":");
                            txt0.setText(Integer.toBinaryString(Integer.parseInt(dec.toString()))+ " "+getResources().getString(R.string.in_the_binary_system));
                            txt1.setText(Integer.toOctalString(Integer.parseInt(dec.toString()))+ " "+getResources().getString(R.string.in_the_octal_system));
                            txt2.setText(String.valueOf(dec)+ " "+getResources().getString(R.string.in_the_decimal_system));
                            }
                            catch (Exception e) {
                                Toast.makeText(NumSysCtivity.this, getResources().getText(R.string.errnumsys), Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.rboctal:
                            try {
                            dec =new BigInteger(new BigInteger(String.valueOf(Integer.parseInt(vvodtxt.getText().toString()))).toString(10),8);
                            txtotvet.setText(getResources().getString(R.string.in_the_octal_system)+" "+getResources().getString(R.string.Number)+" " + vvodtxt.getText().toString()+" "+getResources().getString(R.string.Equal) +":");
                            txt0.setText(Integer.toBinaryString(Integer.parseInt(dec.toString()))+ " "+getResources().getString(R.string.in_the_binary_system));
                            txt1.setText(Integer.toHexString(Integer.parseInt(vvodtxt.getText().toString())).toUpperCase()+ " "+getResources().getString(R.string.in_the_hexadecimal_system));
                            txt2.setText(String.valueOf(dec)+ " "+getResources().getString(R.string.in_the_decimal_system));
                            break;
                            }
                            catch (Exception e) {
                                Toast.makeText(NumSysCtivity.this, getResources().getText(R.string.errnumsys), Toast.LENGTH_SHORT).show();
                            }
                            default:
                            break;
                    }
                }
            }
        });
    }
    public static int hextodec(String hex) {
        String digits = "0123456789ABCDEF";
        int val = 0;
        for (int i = 0; i < hex.length(); i++) {
            char j = hex.charAt(i);
            int k = digits.indexOf(j);
            val = 16*val + k;
        }
        return val;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("txtotvet", txtotvet.getText().toString());
        outState.putString("txt0", txt0.getText().toString());
        outState.putString("txt1", txt1.getText().toString());
        outState.putString("txt2", txt2.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtotvet.setText(savedInstanceState.getString("txtotvet"));
        txt0.setText(savedInstanceState.getString("txt0"));
        txt1.setText(savedInstanceState.getString("txt1"));
        txt2.setText(savedInstanceState.getString("txt2"));
    }
}
