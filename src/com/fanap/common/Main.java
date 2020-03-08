package com.fanap.common;

import com.fanap.mapper.Mapper;
import com.fanap.mapper.MapperImp;
import com.fanap.mapper.Utility;
import com.fanap.model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[]  args){
        PersonDTO dto=new PersonDTO();
        dto.setName22("amir");
        dto.setFamily("foladi");
        dto.setFatherName("ali asghar");
        dto.setId(21);

        //----Contact
        ContactDto contactDto=new ContactDto();
        contactDto.setName("ahmad");
        contactDto.setId(1);

        dto.setContact(contactDto);
        dto.setContacts(new ArrayList<>(Arrays.asList(new ContactDto("alireza"),new ContactDto("gholam"))));

        //enum
        dto.setGender(GenderEnumDto.MALE);

        MapperImp mapper=new MapperImp(new Utility());

        Person person= (Person) mapper.classicMapper(dto, Person.class);
        System.out.println(person);
    }
}
