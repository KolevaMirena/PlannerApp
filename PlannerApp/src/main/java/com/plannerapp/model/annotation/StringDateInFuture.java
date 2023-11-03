package com.plannerapp.model.annotation;


import com.plannerapp.vallidation.StringDateInFutureValidator;

@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@javax.validation.Constraint(validatedBy = {StringDateInFutureValidator.class})
public @interface StringDateInFuture {


    java.lang.String message() default "Date is not in future!";

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends javax.validation.Payload>[] payload() default {};


}
