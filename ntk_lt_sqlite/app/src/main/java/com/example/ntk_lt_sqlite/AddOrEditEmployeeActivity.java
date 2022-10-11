package com.example.ntk_lt_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ntk_lt_sqlite.model.Employee;
import com.example.ntk_lt_sqlite.sqlite.EmployeeDao;

public class AddOrEditEmployeeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtId,edtName,edtClasss;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_employee);

        edtId=findViewById(R.id.edittextId);
        edtName=findViewById(R.id.edittextName);
        edtClasss=findViewById(R.id.edittextClass);

        btnSave=findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(this);
        findViewById(R.id.buttonList).setOnClickListener(this);

        readEmployee();
    }
    private void readEmployee(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle ==null){
            return;
        }
        String id = bundle.getString("id");

        EmployeeDao dao = new EmployeeDao(this);
        Employee emp =dao.getById(id);

        edtId.setText(emp.getId());
        edtName.setText(emp.getName());
        edtClasss.setText(""+ emp.getClasss());

        btnSave.setText("Update");


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSave:
                EmployeeDao dao = new EmployeeDao(this);
                Employee emp = new Employee();
                emp.setId(edtId.getText().toString());
                emp.setName(edtName.getText().toString());
                emp.setClasss(Float.parseFloat(edtClasss.getText().toString()));


                if (btnSave.getText().equals("Save")){
                    dao.insert(emp);
                }else{
                    dao.update(emp);
                }
                Toast.makeText(this, "New Game has been saved", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}