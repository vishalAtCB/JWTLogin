package com.ths.mims.securityhelper;

//import com.ths.mims.mstzoho.MstZoho;
//import com.ths.mims.relstaffmoduleaction.RelStaffModuleActionDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JwtResponse {
    Long userId;
    Long employeeId;
    Long supplierId;
    Long studentId;
    String fullName;
    Long instituteId;
    String instituteName;
    Long roleId;
    String roleName;
    String token;
    Boolean status;
    String refreshToken;
    Boolean supplierIsApprove;

    public JwtResponse() {
    }

    public JwtResponse(String token, Long userId, Long employeeId, Long supplierId, Long studentId, Long instituteId, String fullName, Boolean status, String refreshToken, Boolean supplierIsApprove) {
        this.token = token;
        this.status = status;
        this.userId = userId;
        this.employeeId = employeeId;
        this.supplierId = supplierId;
        this.studentId = studentId;
        this.fullName = fullName;
        this.instituteId = instituteId;
        this.refreshToken = refreshToken;
        this.supplierIsApprove = supplierIsApprove;
    }
}
