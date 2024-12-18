package com.bachnh.dashboard_javafx.service.impl;

import com.bachnh.dashboard_javafx.entity.Userlogin;
import com.bachnh.dashboard_javafx.repository.UserLoginRepository;
import com.bachnh.dashboard_javafx.service.IUserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements IUserLoginService {
    private final UserLoginRepository userLoginRepository;
    @Override
    public String login(String username, String password) {
        Userlogin userLogin = userLoginRepository.findByUsername(username);
        if (userLogin != null && userLogin.getPassword().equals(password)) {
            return "Valid";
        }
        return "Invalid";
    }
}
