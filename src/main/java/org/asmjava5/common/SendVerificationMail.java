package org.asmjava5.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendVerificationMail {
    private final SendMail mail;
    public void sendVerificationEmail(String to, String token) {
        String verifyLink = "http://localhost:5173/verify-email?activationToken=" + token;
        String subject = "Xác minh tài khoản của bạn";
        String content = String.format("""
        <p>Xin chào,</p>
        <p>Bạn đã đăng ký tài khoản thành công. Vui lòng xác minh tài khoản bằng cách nhấn vào liên kết dưới đây:</p>
        <p><a href="%s">Xác minh tài khoản</a></p>
        <p>Liên kết này sẽ hết hạn sau 10 phút.</p>
    """, verifyLink);

        mail.sendHtmlMail(to, subject, content);
    }
}
