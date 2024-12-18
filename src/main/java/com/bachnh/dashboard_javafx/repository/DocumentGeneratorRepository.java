package com.bachnh.dashboard_javafx.repository;

import com.bachnh.dashboard_javafx.entity.DocumentGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentGeneratorRepository extends JpaRepository<DocumentGenerator, String> {
    DocumentGenerator findByEmployeeCodeAndTemplate(String employeeCode, String template);
}
