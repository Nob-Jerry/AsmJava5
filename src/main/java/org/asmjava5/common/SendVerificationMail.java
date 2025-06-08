package org.asmjava5.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendVerificationMail {
    private final SendMail mail;

    public void sendVerificationEmail(String to, String token) {
        String verifyLink = "http://localhost:5173/verify-email?activationToken=" + token;
        String subject = "Xác minh tài khoản QuLyn";

        String content = String.format("""
        <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 24px; background-color: #f4f8fb; border-radius: 10px; border: 1px solid #e0e0e0;">
            <div style="text-align: center;">
                <h2 style="color: #2563eb;">Xác minh tài khoản của bạn</h2>
                <p style="color: #333; font-size: 15px;">
                    Cảm ơn bạn đã đăng ký tài khoản tại <strong>QuLyn</strong> 🎉<br />
                    Vui lòng xác minh email của bạn để hoàn tất quá trình đăng ký.
                </p>

                <div style="margin: 30px 0;">
                    <a href="%s" style="background-color: #2563eb; color: white; padding: 12px 24px; text-decoration: none; border-radius: 6px; font-weight: bold; display: inline-block;">
                        ✅ Xác minh tài khoản
                    </a>
                </div>

                <p style="color: #555; font-size: 13px;">
                    Liên kết này sẽ hết hạn sau <strong>10 phút</strong>. Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.
                </p>

                <hr style="margin-top: 32px; border: none; border-top: 1px solid #ddd;" />
                <p style="font-size: 12px; color: #999; margin-top: 20px;">
                    © 2025 QuLyn. Mọi quyền được bảo lưu.
                </p>
            </div>
        </div>
    """, verifyLink);

        mail.sendHtmlMail(to, subject, content);
    }

    public void sendEmailResetPassword(String to, String token) {
        String resetLink = "http://localhost:5173/reset-password?token=" + token;
        String subject = "Yêu cầu đặt lại mật khẩu tài khoản QuLyn";

        String content = String.format("""
        <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #eee; border-radius: 8px; background-color: #f9f9f9;">
            <h2 style="color: #2c3e50;">Xin chào,</h2>
            <p style="font-size: 15px; color: #333;">
                Bạn nhận được email này vì đã yêu cầu đặt lại mật khẩu cho tài khoản <strong>QuLyn</strong>.
            </p>
            <p style="font-size: 15px; color: #333;">
                Vui lòng nhấn vào nút bên dưới để thay đổi mật khẩu:
            </p>
            <div style="text-align: center; margin: 20px 0;">
                <a href="%s" style="background-color: #007bff; color: white; padding: 12px 24px; text-decoration: none; border-radius: 6px; display: inline-block;">
                    Đặt lại mật khẩu
                </a>
            </div>
            <p style="font-size: 14px; color: #666;">
                Nếu bạn không yêu cầu hành động này, vui lòng bỏ qua email này.
            </p>
            <p style="font-size: 13px; color: #999;">
                Liên kết có hiệu lực trong vòng 10 phút.
            </p>
            <hr style="margin-top: 30px;">
            <p style="font-size: 12px; color: #bbb; text-align: center;">
                QuLyn &copy; 2025
            </p>
        </div>
    """, resetLink);

        mail.sendHtmlMail(to, subject, content);
    }

}
