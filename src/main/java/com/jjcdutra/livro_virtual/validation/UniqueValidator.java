package com.jjcdutra.livro_virtual.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.entityClass = constraintAnnotation.entity();
        this.fieldName = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // Pode considerar valores nulos como válidos ou ajustar conforme necessidade
//        if (value == null) {
//            return true;
//        }

        String queryString = "select 1 from " + entityClass.getName() + " where " + fieldName + "=:value";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1,
                "Foi encontrado mais de um " + entityClass + " com o atributo " + fieldName + " = " + value);

        return list.isEmpty();
    }
}
