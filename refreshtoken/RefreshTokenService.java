package com.ths.mims.refreshtoken;

import com.ths.mims.mstinvsupplier.MstInvSupplier;
import com.ths.mims.trnstdstudent.TrnStdStudent;
import com.ths.mims.user.User;
import com.ths.mims.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
//  @Value("${bezkoder.app.jwtRefreshExpirationMs}")
//  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private UserRepository userRepository;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(User user) {
    RefreshToken refreshToken = new RefreshToken();

//    refreshTokenRepository.deleteByRtStaffIdStaffIdEquals(mstStaff.getStaffId());

    Long refreshTokenDurationMs = 86400000L;

    refreshToken.setRtUserId(user);
    refreshToken.setRtExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshToken createRefreshToken(MstInvSupplier mstInvSupplier) {
    RefreshToken refreshToken = new RefreshToken();

    Long refreshTokenDurationMs = 86400000L;

    refreshToken.setRtSupplierId(mstInvSupplier);
    refreshToken.setRtExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshToken createRefreshToken(TrnStdStudent trnStdStudent) {
    RefreshToken refreshToken = new RefreshToken();

    Long refreshTokenDurationMs = 86400000L;

    refreshToken.setRtStudentId(trnStdStudent);
    refreshToken.setRtExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getRtExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByRtUserIdUserIdEquals(userId);
  }
}
