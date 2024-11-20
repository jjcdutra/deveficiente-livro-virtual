package com.jjcdutra.livro_virtual.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        this.entityClass = constraintAnnotation.domainClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // Pode considerar valores nulos como válidos ou ajustar conforme necessidade
        if (value == null) {
            return true;
        }

        String queryString = "select 1 from " + entityClass.getName() + " where " + fieldName + "=:value";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("value", value);

        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <= 1, "Id " + value + " não está cadastrado na base de dados.");

        return !list.isEmpty();
    }
}
