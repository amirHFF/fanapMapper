package com.fanap.mapper;

import java.lang.reflect.Field;
import java.util.*;

public class MapperImp {

    private MapTools util;

    public MapperImp(MapTools util) {
        this.util = util;
    }

    public MapperImp() {
    }

    public static Object Mapper(Object obj_A, Class C) {
        Object destination = null;

        try {
            destination = C.newInstance();
            Object obj_B = destination;
            Arrays.stream(C.getDeclaredFields())
                    .forEach(side_B -> {
                        side_B.setAccessible(true);
                        Arrays.stream(obj_A.getClass().getDeclaredFields())
                                .forEach(Side_A -> {
                                    Side_A.setAccessible(true);
                                    if (Side_A.getName().equals(side_B.getName()) && Side_A.getAnnotation(Mapper.class) == null) {
                                        try {
                                            side_B.set(obj_B, Side_A.get(obj_A));
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (Side_A.isAnnotationPresent(Mapper.class)
                                            &&
                                            Side_A.getAnnotation(Mapper.class).value().equals(side_B.getName())) {
                                        System.out.println(Side_A.getAnnotation(Mapper.class).type().toString());
                                        System.out.println(Side_A.getName());

                                        try {
                                            side_B.set(obj_B, Side_A.get(obj_A));
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    if (Side_A.isAnnotationPresent(Mapper.class) && Side_A.getAnnotation(Mapper.class).type() != String.class
                                            &&
                                            side_B.getName().equals(Side_A.getName())
                                    ) {
                                        System.out.println(Side_A.getName());
                                        try {
                                            List<Object> op2 = new ArrayList<>();
                                            List<Object> op1 = new ArrayList<>((Collection<?>) Side_A.get(obj_A));
                                            op1.forEach(obj -> {
                                                op2.add(Mapper(obj, Side_A.getAnnotation(Mapper.class).type()));
                                            });
                                            side_B.set(obj_B, op2);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    });


        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("dest : " + destination.toString());
//        System.out.println("source :" + source.toString());
        return destination;
    }


    public  Object classicMapper(Object obj_A, Class C) {
        Object obj_B = null;
        try {
            obj_B = C.newInstance();
            Field[] sides_A = obj_A.getClass().getDeclaredFields();
            Field[] sides_B = C.getDeclaredFields();
            for (Field side_A : sides_A) {
                side_A.setAccessible(true);

                for (Field side_B : sides_B) {
                    side_B.setAccessible(true);

                    util.setSide_A(side_A);
                    util.setSide_B(side_B);
                    util.setObj_A(obj_A);
                    util.setObj_B(obj_B);

                    if (side_A.getName().equals(side_B.getName())) {
                        if (side_A.isAnnotationPresent(Mapper.class)) {
                            //if side a has a nested entity
                            if (side_A.getAnnotation(Mapper.class).type() == side_B.getType()) {
                                util.setEntityField();
                            }
                        }
                        //for regular mapping
                        //if side A has a Collection field
                        if (side_A.isAnnotationPresent(Mapper.class) && side_B.getName().equals(side_A.getName()) && isCollection(side_A)) {

                            util.setCollectionField(side_A, side_B);

                        }
                        if (side_A.getType().isEnum())
                            util.setEnumerateField();

                        if (side_A.getType()==side_B.getType())
                            util.setPrimitiveFields();
                    }
                    //if side A has a Mappable field with different name( if name in both side is different )
                    else if (side_A.isAnnotationPresent(Mapper.class) && side_A.getAnnotation(Mapper.class).value().equals(side_B.getName())) {
                        System.out.println(side_A.getName());

                        util.setPrimitiveFields();

                    }

                }
            }
//            System.out.println("dest = " + obj_B.toString());
//            System.out.println("source =" + obj_A.toString());

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj_B;
    }

    private List<Object> listMapper(List<Object> first) {
        return null;
    }

    private static boolean isCollection(Field field) {
        boolean isCollection = false;
        for (Class C : field.getType().getInterfaces()) {
            System.out.println();
            if (C == List.class) {
                isCollection = true;
                break;
            }
        }
        System.out.println(isCollection);
        return isCollection;
    }
}
//                        try {
//
//                            side_B[j].set(obj_B, side_A[i].get(obj_A));
//
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }


//                        try {
//                            Object ListB = side_A[i].getType().newInstance();
//                            Method add = side_A[i].getType().getDeclaredMethod("add", Object.class);
//
//                            ArrayList<Object> ListA = new ArrayList<>((Collection<?>) side_A[i].get(obj_A));
////
//                            for (Object objA : ListA) {
//                                add.invoke(ListB, classicMapper(objA, side_A[i].getAnnotation(Mapper.class).type()));
//                            }
//                            side_B[j].set(obj_B, ListB);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (NoSuchMethodException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        }