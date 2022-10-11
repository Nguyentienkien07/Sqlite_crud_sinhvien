package com.example.ntk_lt_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ntk_lt_sqlite.adapter.EmployeeAdapter;
import com.example.ntk_lt_sqlite.model.Employee;
import com.example.ntk_lt_sqlite.sqlite.DBHelper;
import com.example.ntk_lt_sqlite.sqlite.EmployeeDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EmployeeAdapter employeeAdapter;
    private ListView lvEmployees;
    private String employeeId;
    private List<Employee> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 //       DBHelper dbHelper = new DBHelper(this);
  //      SQLiteDatabase database = dbHelper.getReadableDatabase();
  //      database.close();

        findViewById(R.id.buttonEdit).setOnClickListener(this);
        findViewById(R.id.buttonInsert).setOnClickListener(this);
        findViewById(R.id.buttonDelete).setOnClickListener(this);

        lvEmployees = findViewById(R.id.lvEmployees);
        EmployeeDao dao = new EmployeeDao(this);
        list = dao.getAll();
        employeeAdapter = new EmployeeAdapter(this, list);
        lvEmployees.setAdapter(employeeAdapter);
        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee emp = list.get(i);
                employeeId = emp.getId();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        EmployeeDao dao= new EmployeeDao(this);
        List<Employee> updatedList = dao.getAll();

        list.clear();
        updatedList.forEach(item->list.add(item));
        employeeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddOrEditEmployeeActivity.class);
        switch (view.getId()) {
            case R.id.buttonInsert:
                startActivity(intent);
                break;
            case R.id.buttonEdit:
                if(employeeId==null){
                    Toast.makeText(this, "Id must be selected", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bundle bundle=new Bundle();
                bundle.putString("id",employeeId);
                intent.putExtra("data",bundle);

                startActivity(intent);
                break;
            case R.id.buttonDelete:
                if(employeeId==null){
                    Toast.makeText(this, "Employee ID must be selected", Toast.LENGTH_SHORT).show();
                    return;
                }

                EmployeeDao dao= new EmployeeDao(this);
                dao.delete(employeeId);
                employeeId=null;

                onResume();

                Toast.makeText(this, "Employee was deleted", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}