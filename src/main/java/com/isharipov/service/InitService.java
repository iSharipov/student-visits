package com.isharipov.service;

import com.isharipov.annotations.Table;
import com.isharipov.exceptions.NoTableFoundException;
import com.isharipov.model.TableInfo;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Илья on 25.10.2016.
 */
public class InitService {
    private final JdbcTemplate jdbcTemplate;

    public InitService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(Table.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("com.isharipov.model")) {
            Class clazz = null;
            try {
                clazz = Class.forName(bd.getBeanClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String tableName;
            String columnName;
            if (clazz.isAnnotationPresent(Table.class)) {
                Table table = (Table) clazz.getAnnotation(Table.class);
                tableName = table.name().toUpperCase();
            } else {
                tableName = clazz.getName().toUpperCase();
            }
            List<TableInfo> query = jdbcTemplate.query("SELECT COLUMN_NAME, TYPE_NAME" +
                    " FROM INFORMATION_SCHEMA.COLUMNS" +
                    " WHERE TABLE_NAME = " + "'" + tableName + "'", (rs, i) -> new TableInfo(rs.getString(1), rs.getString(2)));
            if (query.size() == 0) {
                throw new NoTableFoundException("table: " + tableName + " not found");
            }
            query.stream().forEach(i -> System.out.println(i.getColumnName() + " " + i.getTypeName()));
        }
    }
}
