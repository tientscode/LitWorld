package com.tscode.LitWorld.Controller.Auth;

import com.tscode.LitWorld.Class.RdPassNewUser;
import com.tscode.LitWorld.Service.SessionService;
import com.tscode.LitWorld.Service.mail.MailerService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

@Controller
public class EmailController {

    @Autowired
    MailerService mailer;

    @Autowired
    SessionService sessionService;

    @RequestMapping("send/mail")
    public String home() {
        return "auth/Sendmail_Otp";
    }


    @PostMapping("/sendmail")
    public String demo(Model model, @RequestParam("email") String to) throws MessagingException {
        try {
            sessionService.set("email", to);
            Random random = new Random();
            int randomNumber = 1000 + random.nextInt(9000); // Generates a random number between 1000 and 9999
            String body = "Your verification code is: " + randomNumber;
            mailer.queue(to,"Quên Mật Khẩu", body);
            System.out.println("thành công");
            sessionService.set("otp", randomNumber);
        } catch (Exception e) {
            model.addAttribute("tb", "Email not find");
        }
        return "auth/Sendmail_Otp";
    }

    @PostMapping("/sendotp")
    public String handleOTP(@RequestParam("otp1") String otp1,
                            @RequestParam("otp2") String otp2,
                            @RequestParam("otp3") String otp3,
                            @RequestParam("otp4") String otp4,
                            RedirectAttributes redirectAttributes) {
        String otpString = otp1 + otp2 + otp3 + otp4;

        try {
            int otp = Integer.parseInt(otpString);
            int storedOtp = sessionService.get("otp");
            if (otp == storedOtp) {
                String newPassword = RdPassNewUser.generateRandomPassword(8);
                String email = sessionService.get("email"); // Assuming email is stored in session
                mailer.queue(email, "New Password", "Your new password is: " + newPassword);
                redirectAttributes.addFlashAttribute("message", "OTP is correct!");
                return "redirect:/login";
            } else {
                redirectAttributes.addFlashAttribute("message", "Invalid OTP!");
                return "redirect:/send/mail";
            }
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("message", "Invalid OTP format!");
            return "redirect:/send/mail";
        }
    }

}
