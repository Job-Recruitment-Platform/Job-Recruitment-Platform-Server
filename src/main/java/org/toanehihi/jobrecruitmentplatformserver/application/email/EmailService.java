package org.toanehihi.jobrecruitmentplatformserver.application.email;

public interface EmailService {
    void sendPasswordResetEmail(String recieveEmail, String token);
    void sendVerificationEmail(String receiveEmail, String token);
}
