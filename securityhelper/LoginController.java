package com.ths.mims.securityhelper;

import com.ths.mims.mstinvsupplier.MstInvSupplier;
import com.ths.mims.mstinvsupplier.MstInvSupplierRepository;
import com.ths.mims.refreshtoken.*;
import com.ths.mims.securityconfig.JwtUtil;
import com.ths.mims.trnstdstudent.TrnStdStudent;
import com.ths.mims.trnstdstudent.TrnStdStudentRepository;
import com.ths.mims.user.User;
import com.ths.mims.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin()
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MstInvSupplierRepository mstInvSupplierRepository;

    @Autowired
    private TrnStdStudentRepository trnStdStudentRepository;

    @Autowired
    RefreshTokenService refreshTokenService;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        JwtResponse resp = new JwtResponse();
        try {
            System.out.println("Inside try LoginDto controller");
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            System.out.println("Inside catch LoginDto controller");
            e.printStackTrace();
            resp.setStatus(false);
            return ResponseEntity.ok(resp);
//            throw new Exception("Bad Credentials");
        } catch (BadCredentialsException e) {
            System.out.println("Inside catch 2 LoginDto controller");
            e.printStackTrace();
            resp.setStatus(false);
            return ResponseEntity.ok(resp);
//            throw new Exception("Bad Credentials");
        }
        //fine area..
        UserDetails userDetails = this.userLoginService.loadUserByUsername(jwtRequest.getUsername());

//        String token = this.jwtUtil.generateToken(userDetails);
        String token = this.jwtUtil.generateTokenFromUsername(userDetails.getUsername());
        final Optional<User> user = this.userRepository.findByUserUsernameEqualsAndUserIsActiveTrue(jwtRequest.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.get());
        boolean status = true;
        resp.setUserId(user.get().getUserId());
        resp.setEmployeeId(user.get().getUserEmployeeId().getEmployeeId());
        resp.setFullName(user.get().getUserFullname());
        resp.setRoleId(user.get().getUserRoleId().getRoleId());
        resp.setRoleName(user.get().getUserRoleId().getRoleName());
        resp.setToken(token);
        resp.setInstituteId(user.get().getUserEmployeeId().getEmployeeInstituteId().getInstituteId());
        resp.setInstituteName(user.get().getUserEmployeeId().getEmployeeInstituteId().getInstituteName());
        resp.setToken(token);
        resp.setRefreshToken(refreshToken.getToken());
        resp.setStatus(true);
        return ResponseEntity.ok(resp);
    }

    @RequestMapping(value = "/supplier_token", method = RequestMethod.POST)
    public ResponseEntity<?> generateSupplierToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        JwtResponse resp = new JwtResponse();
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            resp.setStatus(false);
            return ResponseEntity.ok(resp);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            resp.setStatus(false);
            return ResponseEntity.ok(resp);
        }
//        fine area..
        UserDetails userDetails = this.userLoginService.loadUserByUsername(jwtRequest.getUsername());

        String token = this.jwtUtil.generateTokenFromUsername(userDetails.getUsername());
        final Optional<MstInvSupplier> mstInvSupplier = this.mstInvSupplierRepository.findBySupplierUsernameEqualsAndSupplierIsActiveTrue(jwtRequest.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(mstInvSupplier.get());
        boolean status = true;
        resp.setSupplierId(mstInvSupplier.get().getSupplierId());
        resp.setFullName(mstInvSupplier.get().getSupplierName());
//        resp.setRoleId('Supplier');
        resp.setRoleName("Supplier");
        resp.setToken(token);
//        resp.setInstituteId(user.get().getUserEmployeeId().getEmployeeInstituteId().getInstituteId());
//        resp.setInstituteName(user.get().getUserEmployeeId().getEmployeeInstituteId().getInstituteName());
        resp.setToken(token);
        resp.setRefreshToken(refreshToken.getToken());
        resp.setStatus(true);
        resp.setSupplierIsApprove(mstInvSupplier.get().getSupplierIsApprove());
        return ResponseEntity.ok(resp);
    }

    @RequestMapping(value = "/student_token", method = RequestMethod.POST)
    public ResponseEntity<?> generateStudentToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        HashMap map = new HashMap();
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            map.put("status", false);
            return ResponseEntity.ok(map);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            map.put("status", false);
            return ResponseEntity.ok(map);
        }
//        fine area..

        UserDetails userDetails = this.userLoginService.loadUserByUsername(jwtRequest.getUsername());

        String token = this.jwtUtil.generateTokenFromUsername(userDetails.getUsername());
        RefreshToken refreshToken = null;
        try{
            if(jwtRequest.getLoginAs().equals("User")){
                final Optional<User> user = this.userRepository.findByUserUsernameEqualsAndUserIsActiveTrue(jwtRequest.getUsername());
                refreshToken = refreshTokenService.createRefreshToken(user.get());

                map.put("userId", user.get().getUserId());
                map.put("employeeId", user.get().getUserEmployeeId().getEmployeeId());
                map.put("fullName", user.get().getUserFullname());
                map.put("roleId", user.get().getUserRoleId().getRoleId());
                map.put("roleName", user.get().getUserRoleId().getRoleName());
                map.put("instituteId", user.get().getUserEmployeeId().getEmployeeInstituteId().getInstituteId());
                map.put("instituteName", user.get().getUserEmployeeId().getEmployeeInstituteId().getInstituteName());

            }else if(jwtRequest.getLoginAs().equals("Supplier")){
                final Optional<MstInvSupplier> mstInvSupplier = this.mstInvSupplierRepository.findBySupplierUsernameEqualsAndSupplierIsActiveTrue(jwtRequest.getUsername());
                refreshToken = refreshTokenService.createRefreshToken(mstInvSupplier.get());

                map.put("supplierId", mstInvSupplier.get().getSupplierId());
                map.put("fullName", mstInvSupplier.get().getSupplierName());
                map.put("roleName", "Supplier");
                map.put("supplierIsApprove", mstInvSupplier.get().getSupplierIsApprove());

            }else if(jwtRequest.getLoginAs().equals("Student")){
                final Optional<TrnStdStudent> trnStdStudent = this.trnStdStudentRepository.findByStudentUsernameEqualsAndStudentIsActiveTrueAndStudentIsDeletedFalse(jwtRequest.getUsername());
                refreshToken = refreshTokenService.createRefreshToken(trnStdStudent.get());

                map.put("studentId", trnStdStudent.get().getStudentId());
                map.put("fullName", trnStdStudent.get().getStudentFullName());
                map.put("roleName", "Student");

            }else {
                throw new Exception("Please Specify the Parameter");
            }

            map.put("token", token);
            map.put("refreshToken", refreshToken.getToken());
            map.put("status", true);

        }catch (NoSuchElementException e){
            throw new UsernameNotFoundException("Username not found!!!");
        }
        catch (Exception e){

        }
        return ResponseEntity.ok(map);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getRtUserId)
                .map(user -> {
                    String token = jwtUtil.generateTokenFromUsername(user.getUserUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
