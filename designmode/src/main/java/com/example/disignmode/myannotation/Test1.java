package com.example.disignmode.myannotation;

import java.lang.reflect.Field;

/**
 * created by linghaoDo on 2020-03-19
 * description:
 * <p>
 * version:
 */
public class Test1 {
    public static void main(String[] args) {
        Class<Person> clazz = Person.class;
        Field fields[] = clazz.getDeclaredFields();
        for (Field field1 : fields) {
            field1.setAccessible(true);
            System.out.println("field1.getType()-->" + field1.getType().getName());
            Required required = field1.getAnnotation(Required.class);
            if (required != null) {
                System.out.println(field1.getName() + "-->" + "name-->" + required.name() + "value-->" + required.value());
            }
        }
    }
}
