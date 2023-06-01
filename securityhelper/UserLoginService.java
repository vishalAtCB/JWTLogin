package com.ths.mims.securityhelper;

import com.ths.mims.mstinvsupplier.MstInvSupplier;
import com.ths.mims.mstinvsupplier.MstInvSupplierRepository;
import com.ths.mims.trnstdstudent.TrnStdStudent;
import com.ths.mims.trnstdstudent.TrnStdStudentRepository;
import com.ths.mims.user.User;
import com.ths.mims.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MstInvSupplierRepository mstInvSupplierRepository;
    @Autowired
    private TrnStdStudentRepository trnStdStudentRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final Optional<User> user = this.userRepository.findByUserUsernameEqualsAndUserIsActiveTrue(userName);
        System.out.print("Hey " + user.toString());
        if (!user.isPresent()) {
            final Optional<MstInvSupplier> mstInvSupplier = this.mstInvSupplierRepository.findBySupplierUsernameEqualsAndSupplierIsActiveTrue(userName);
            if (!mstInvSupplier.isPresent()) {
                final Optional<TrnStdStudent> trnStdStudent = this.trnStdStudentRepository.findByStudentUsernameEqualsAndStudentIsActiveTrueAndStudentIsDeletedFalse(userName);
                if(!trnStdStudent.isPresent()){
                    throw new UsernameNotFoundException("User is not approved !!");
                }else {
                    return new UserLoginDetails(trnStdStudent.get());
                }
            } else {
                return new UserLoginDetails(mstInvSupplier.get());
            }
        } else {
            return new UserLoginDetails(user.get());
        }
    }
}
