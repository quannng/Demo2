package com.example.quanganh.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanganh.demo.model.Student;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper{

    public Database(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Student(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, mssv TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addStudent(ContentValues values){
        try {
            SQLiteDatabase db= this.getWritableDatabase();
            db.insert("Student",null,values);
            db.close();
        }
        catch (Exception e){

        }
    }
    public ArrayList<Student> getStudent(){
        ArrayList<Student> students=new ArrayList<>();
            SQLiteDatabase db=this.getReadableDatabase();
            String select = "select * from Student";

            try(Cursor cursor= db.rawQuery(select,null)){
                if(cursor.moveToFirst()){
                    do {
                        Student student = new Student();
                        student.setId(cursor.getInt(0));
                        student.setName(cursor.getString(1));
                        student.setMssv(cursor.getString(2));
                        students.add(student);
                    }
                    while (cursor.moveToNext());
                }
            }
            catch (Exception e){

            }
            db.close();
        return students;

    }
    public void editStudent(ContentValues values,int id){
        SQLiteDatabase db= this.getWritableDatabase();
        try{
            db.update("Student",values,"_id=?",new String[]{String.valueOf(id)});
            db.close();
        }
        catch (Exception e){

        }
    }
    public void removeStudent(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        try{
            db.delete("Student","_id=?",new String[]{String.valueOf(id)});
            db.close();
        }catch (Exception e){

        }
    }
    public ArrayList<Student> searchStudennt(int value){
        ArrayList<Student> students=new ArrayList<>();
            SQLiteDatabase db=this.getReadableDatabase();
            String sql="select * from student where name like '%"+value+"%'";
            try(Cursor cursor = db.rawQuery(sql,null)){
                if(cursor.moveToFirst()){
                    do{
                        Student student=new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                        students.add(student);
                    }while (cursor.moveToNext());
                }
            }
            catch (Exception e){

            }
            db.close();
        return students;
    }
}
