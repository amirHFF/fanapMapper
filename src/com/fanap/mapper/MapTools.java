package com.fanap.mapper;

import com.fanap.model.Contact;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public abstract class MapTools {

    protected Field side_A;
    protected Field side_B;
    protected Object obj_A;
    protected Object obj_B;

    public MapTools() {
    }

    public MapTools(Field side_A, Field side_B, Object obj_A, Object obj_B) {

        this.side_A = side_A;
        this.side_B = side_B;
        this.obj_A = obj_A;
        this.obj_B = obj_B;
    }

    public void setPrimitiveFields() {
        try {
            side_B.set(obj_B, side_A.get(obj_A));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setEntityField() {

        MapperImp mapper=new MapperImp(new Utility());
        try {
            System.out.println(side_A.get(obj_A));
            side_B.set(obj_B, mapper.classicMapper(side_A.get(obj_A), Contact.class));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setEnumerateField() {
        try {
            side_B.set(obj_B, Enum.valueOf((Class<Enum>) side_B.getType(), side_A.get(obj_A).toString()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setCollectionField(Field side_A, Field side_B ){
        MapperImp mapper=new MapperImp(new Utility());
        try {
            Object ListB = side_A.getType().newInstance();
            Method add = side_A.getType().getDeclaredMethod("add", Object.class);

            ArrayList<Object> ListA = new ArrayList<>((Collection<?>) side_A.get(obj_A));
//
            for (Object objA : ListA) {
                add.invoke(ListB, mapper.classicMapper(objA, side_A.getAnnotation(Mapper.class).type()));
            }
            side_B.set(obj_B, ListB);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public Field getSide_A() {
        return side_A;
    }

    public void setSide_A(Field side_A) {
        this.side_A = side_A;
    }

    public Field getSide_B() {
        return side_B;
    }

    public void setSide_B(Field side_B) {
        this.side_B = side_B;
    }

    public Object getObj_A() {
        return obj_A;
    }

    public void setObj_A(Object obj_A) {
        this.obj_A = obj_A;
    }

    public Object getObj_B() {
        return obj_B;
    }

    public void setObj_B(Object obj_B) {
        this.obj_B = obj_B;
    }
}
