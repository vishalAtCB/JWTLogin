package com.ths.mims.refreshtoken;

import com.ths.mims.mstinvsupplier.MstInvSupplier;
import com.ths.mims.trnstdstudent.TrnStdStudent;
import com.ths.mims.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity(name = "mst_refresh_token")
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long rtId;

  @OneToOne
  @JoinColumn(name = "rtUserId", referencedColumnName = "userId")
  private User rtUserId;

  @OneToOne
  @JoinColumn(name = "rtSupplierId", referencedColumnName = "supplierId")
  private MstInvSupplier rtSupplierId;

  @OneToOne
  @JoinColumn(name = "rtStudentId", referencedColumnName = "studentId")
  private TrnStdStudent rtStudentId;

  @Column(nullable = false, unique = true)
  private String token;

  @Column(nullable = false)
  private Instant rtExpiryDate;

  public RefreshToken() {
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
