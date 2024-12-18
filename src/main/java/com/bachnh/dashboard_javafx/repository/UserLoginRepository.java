package com.bachnh.dashboard_javafx.repository;

import com.bachnh.dashboard_javafx.entity.Userlogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<Userlogin,String> {
    Userlogin findByUsername(String username);
}
