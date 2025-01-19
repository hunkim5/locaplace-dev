package com.locaplace.api.common.util;

import java.lang.reflect.Field;


public class EntityMapper {
    public static <T, U> U toEntity(T vo, Class<U> entityClass) {
        try {
            U entity = entityClass.getDeclaredConstructor().newInstance();
            copyFields(vo, entity);
            return entity;
        } catch (Exception e) {
            //TODO
            throw new RuntimeException("Entity 변환 중 오류 발생", e);
        }
    }

    private static <T, U> void copyFields(T source, U target) {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        for (Field sourceField : sourceFields) {
            copyField(source, target, sourceField);
        }
    }

    private static <T, U> void copyField(T source, U target, Field sourceField) {
        try {
            sourceField.setAccessible(true);
            String fieldName = sourceField.getName();
            Object value = sourceField.get(source);

            Field targetField = target.getClass().getDeclaredField(fieldName);
            targetField.setAccessible(true);
            targetField.set(target, value);
        } catch (NoSuchFieldException ignored) {
            // 대상 엔티티에 해당 필드가 없으면 무시
        } catch (IllegalAccessException e) {
            //TODO
            throw new RuntimeException("필드 복사 중 오류 발생", e);
        }
    }
}