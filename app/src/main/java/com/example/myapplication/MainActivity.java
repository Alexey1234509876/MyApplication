package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editTextTextPersonName2, editTextTextPersonName3;
    private Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //присваиваем переменным реальный текстовый объект
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        add_button = findViewById(R.id.add_button);
        textView = findViewById(R.id.textView);

        //этот метод позволяет создать событие которое
        //будет срабатывать при нажатии на кнопку
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        //запуск питона
        Python py = Python.getInstance();
        //создаем бъект питон
        PyObject prob = py.getModule("myscript");

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //все что внутри этого метода будет срабатывать при
                //нажатии на кнопку
                //считываем при нажатии на кнопку

                //считываем вес
                float num1 = Float.parseFloat(editTextTextPersonName2.getText().toString());
                //считываем вес
                float num2 = Float.parseFloat(editTextTextPersonName3.getText().toString());

                //вызываем функцию и передаем в нее рост и вес
                PyObject obj = prob.callAttr("main", num1, num2);

                //установим возвращаемое значение
                textView.setText(obj.toString());
            }
        });
    }
}
