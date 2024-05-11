package ru.litavrina.ToDoListV2.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
public class TrackUserActionAspect {
    @AfterReturning(value = "@annotation(TrackUserAction)", returning = "returnedValue")
    public void log(Object returnedValue) {
        System.out.println("Метод вернул значение " + returnedValue);
    }
}
