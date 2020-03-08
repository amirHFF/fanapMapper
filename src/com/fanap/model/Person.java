package com.fanap.model;

import com.fanap.model.Contact;
import com.fanap.model.Gender;

import java.util.List;

public class Person {

    private Integer id;
    private String name;
    private String family;
    private String nationalCode;
    private List<Contact> contacts;
    private Contact contact;
    private String FatherName;
    private String phone;
    private String address;
    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        System.out.println("person { ");
        if (contacts != null)
            contacts.forEach(System.out::println);
        return "\tname22 :" + name + " family :" + family +" gender :"+gender
                + " nationalCode :" + nationalCode+" contact :" +contact+" }";
    }

}
