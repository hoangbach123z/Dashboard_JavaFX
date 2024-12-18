package com.bachnh.dashboard_javafx.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDT0 {
    private String ID;

    private String employeecode;

    private String fullname;

    private String gender;

    private String birthday;

    private String cardId;

    private String mobile;

    private String email;

    private String address;

//    private String cProfileImage;
//
//    private String cQrCode;

    private String departmentName;

    private String roleName;

    private String status;

    private String createDate;

    private String updateDate;
}
