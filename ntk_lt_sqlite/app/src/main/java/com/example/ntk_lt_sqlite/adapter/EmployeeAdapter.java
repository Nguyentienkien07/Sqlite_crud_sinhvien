package com.example.ntk_lt_sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ntk_lt_sqlite.R;
import com.example.ntk_lt_sqlite.model.Employee;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {
    private Context context;
    private List<Employee> list;

    public EmployeeAdapter(Context context, List<Employee> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int iPosition) {
        return list.get(iPosition);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.layout_employee_item, null);
        }

        TextView tvName=view.findViewById(R.id.tvName);
        TextView tvClasss=view.findViewById(R.id.tvClass);

        Employee emp =list.get(i);
        tvName.setText(emp.getName());
        tvClasss.setText("" + emp.getClasss());
        return view;
    }
}
