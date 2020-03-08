package com.fanap.model;


import com.fanap.mapper.Mapper;

import java.util.ArrayList;

public class PersonDTO {
    private Integer id;

    @Mapper(value = "name")
    private String name22;

    private String family;

    private String nationalCode;

    @Mapper(type = Contact.class)
    private ArrayList<ContactDto> contacts;

    @Mapper(type = Contact.class)
    private ContactDto contact;

    private String FatherName;
    private String phone;
    private String address;
    private GenderEnumDto gender;

    public GenderEnumDto getGender() {
        return gender;
    }

    public void setGender(GenderEnumDto gender) {
        this.gender = gender;
    }

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
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

    public ArrayList<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactDto> contacts) {
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName22() {
        return name22;
    }

    public void setName22(String name22) {
        this.name22 = name22;
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
        return "\tname22 : " + name22 + " family : " + family + " nationalCode: " + nationalCode + " }";
    }
}
