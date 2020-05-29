package com.AndroidProject.womenSafety;

//this is model class
public class User {
    //variables

    String name;
    String age;
    String phoneno;
    String nic;
    String address;
    String email;
    String username;
    String password;
    // Constructor with two parameters name and password
    public User( String username, String password)
    {

        this.username = username;
        this.password=password;
    }
    //Parameter constructor containing all  parameters
    public User(String name,String age, String phoneno,String nic, String address, String email, String username, String password)
    {

        this.name=name;
        this.age = age;
        this.phoneno = phoneno;
        this.nic = nic;
        this.address = address;
        this.email =email;
        this.username = username;
        this.password=password;

    }

    //getting name
    public String getName() {
        return name;
    }
    //setting name
    public void setName(String name) {
        this.name = name;
    }
    //getting age
    public String getAge() {
        return age;
    }
    //setting age
    public void setAge(String age) {
        this.age = age;
    }
    //getting Phone number
    public String getPhoneno() {
        return phoneno;
    }
    //setting Phone number
    public void setPhoneno(String Phoneno) {
        this.phoneno = Phoneno;
    }

    //getting nic
    public String getNic() {
        return nic;
    }
    //setting Phone number
    public void setNic(String nic) {
        this.nic = nic;
    }
    //getting address
    public String getAddress() {
        return address;
    }
    //setting Address
    public void setAddress(String address) {
        this.address = address;
    }

    //getting Username
    public String getEmail() {
        return email;
    }
    //setting Username
    public void setEmail(String email) {
        this.email = email;
    }
    //getting Username
    public String getUsername() {
        return username;
    }
    //setting Username
    public void setUsername(String username) {
        this.username = username;
    }
    //getting password
    public String getPassword() {
        return password;
    }
    //setting password
    public void setPassword(String password) {
        this.password = password;
    }
}