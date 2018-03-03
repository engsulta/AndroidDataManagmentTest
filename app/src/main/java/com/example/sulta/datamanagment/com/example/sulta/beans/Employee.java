package com.example.sulta.datamanagment.com.example.sulta.beans;

public class Employee
{
    private int id,salary;
    private String name,phone;

    public Employee(int id, String name, String phone, int salary) {
        this.id = id;
        this.salary = salary;
        this.name = name;
        this.phone = phone;
    }
    public Employee(String name, String phone, int salary) {
        this.salary = salary;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name;
    }
}
