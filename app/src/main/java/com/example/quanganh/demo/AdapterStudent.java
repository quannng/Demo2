package com.example.quanganh.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanganh.demo.model.Student;

import java.util.ArrayList;

public class AdapterStudent extends BaseAdapter {
    Context context;
    ArrayList<Student> students;
    int layout;

    public AdapterStudent(Context context, ArrayList<Student> students, int layout) {
        this.context = context;
        this.students = students;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHodel{
        TextView tv_Name,tv_MSSV;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodel viewHodel;
        if(view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHodel= new ViewHodel();
            viewHodel.tv_MSSV = view.findViewById(R.id.tv_ItemMSSV);
            viewHodel.tv_Name= view.findViewById(R.id.tv_ItemName);
            view.setTag(viewHodel);
        }
        else {
            viewHodel= (ViewHodel) view.getTag();
        }
        Student student = students.get(i);
        viewHodel.tv_Name.setText(student.getName());
        viewHodel.tv_MSSV.setText(student.getMssv());

        return view;
    }
}
