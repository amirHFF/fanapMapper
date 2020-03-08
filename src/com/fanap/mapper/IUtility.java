package com.fanap.mapper;

import java.lang.reflect.Field;

public interface IUtility {
    void setPrimitiveFields();
    void setEntityField();
    void setEnumerateField();
    void setCollectionField(Field side_A, Field side_B);

}
