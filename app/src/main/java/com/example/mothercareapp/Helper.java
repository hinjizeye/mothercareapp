package com.example.mothercareapp;

public class Helper {
   String name, age,  numOfChildren, location, email, password;

  public Helper() {
  }
  public Helper(String name, String age, String numOfChildren, String location, String email, String password) {
    this.name = name;
    this.age = age;
    this.numOfChildren = numOfChildren;
    this.location = location;
    this.email = email;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getNumOfChildren() {
    return numOfChildren;
  }
  public void setNumOfChildren(String numOfChildren) {
    this.numOfChildren = numOfChildren;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

}
