package com.example.quanganh.demo.model;

public class Student {
    private int id;
    private String name,mssv;

    public Student(int id, String name, String mssv) {
        this.id = id;
        this.name = name;
        this.mssv = mssv;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
}
