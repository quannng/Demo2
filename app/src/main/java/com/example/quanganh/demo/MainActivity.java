package com.example.quanganh.demo;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanganh.demo.model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText ed_Name,ed_MSSV,ed_Search;
    private ListView lv_List;
    private Button bt_Add,bt_Remove,bt_Edit;
    private Database db;
    private AdapterStudent adapter;
    private ArrayList<Student> students;
    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        bt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed_Name.getText().toString();
                String mssv = ed_MSSV.getText().toString();
                if(name.equals("")&& mssv.equals("")){
                    Toast.makeText(MainActivity.this, "Chua nhap du", Toast.LENGTH_SHORT).show();
                }
                else {
                    ContentValues values = new ContentValues();
                    values.put("name",name);
                    values.put("mssv",mssv);
                    db.addStudent(values);
                    updateLV();
                }
            }
        });
        lv_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               student= students.get(i);
                ed_MSSV.setText(student.getMssv());
                Toast.makeText(MainActivity.this, ""+student.getMssv(), Toast.LENGTH_SHORT).show();
                ed_Name.setText(student.getName());
            }
        });
        bt_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=student.getId();
                db.removeStudent(id);
                updateLV();
            }
        });


    }

    private void updateLV() {
        students.clear();
        students.addAll(db.getStudent());
        adapter.notifyDataSetChanged();
    }

    private void init() {
        ed_MSSV = findViewById(R.id.edt_MSSV);
        ed_Name=findViewById(R.id.edt_Name);
        lv_List=findViewById(R.id.lv_List);
        bt_Add=findViewById(R.id.bt_Add);
        bt_Remove=findViewById(R.id.bt_Remove);
        bt_Edit=findViewById(R.id.bt_Edit);
        ed_Search=findViewById(R.id.edt_Search);
        db= new Database(this);
        students= db.getStudent();
        adapter = new AdapterStudent(this,students,R.layout.item_list);
        lv_List.setAdapter(adapter);


    }
}
