package com.bachnh.dashboard_javafx.repository;

import com.bachnh.dashboard_javafx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
