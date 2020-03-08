package com.fanap.mapper;

import com.fanap.mapper.MapperImp;
import com.fanap.model.Contact;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Utility extends MapTools{


    public Utility(){}

    public Utility(Field side_A, Field side_B, Object obj_A, Object obj_B){
        super(side_A,side_B,obj_A,obj_B);

    }

    @Override
    public void setPrimitiveFields() {
        try {
            side_B.set(obj_B, side_A.get(obj_A));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEntityField() {
        MapperImp mapper=new MapperImp(new Utility());
        try {
            System.out.println(side_A.get(obj_A));
            side_B.set(obj_B, mapper.classicMapper(side_A.get(obj_A), Contact.class));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEnumerateField() {
        try {
            side_B.set(obj_B, Enum.valueOf((Class<Enum>) side_B.getType(), side_A.get(obj_A).toString()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCollectionField(Field side_A, Field side_B) {
        MapperImp mapper=new MapperImp(new Utility());
        try {
            Object ListB = side_A.getType().newInstance();
            Method add = side_A.getType().getDeclaredMethod("add", Object.class);

            ArrayList<Object> ListA = new ArrayList<>((Collection<?>) side_A.get(obj_A));

            for (Object objA : ListA) {
                add.invoke(ListB, mapper.classicMapper(objA, side_A.getAnnotation(Mapper.class).type()));
            }
            side_B.set(obj_B, ListB);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }



}
