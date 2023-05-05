package com.pasofe.deadmanwarning;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pasofe.deadmanwarning.databinding.ActivityWarningBinding;

public class WarningActivity extends AppCompatActivity {

    private ActivityWarningBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWarningBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        VibrationEffect vibrationEffect = VibrationEffect.createOneShot(10000, VibrationEffect.DEFAULT_AMPLITUDE);

        vb.vibrate(vibrationEffect);
    }
    /*
    SmsManager smsManager = SmsManager.getDefault();
    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    */
    /*
    Properties props = new Properties();
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.socketFactory.port", "465");
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.port", "465");

Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("user@gmail.com", "password");
    }
});

try {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress("user@gmail.com"));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("recipient@gmail.com"));
    message.setSubject("Testing Subject");
    message.setText("Dear Mail Crawler,"
        + "\n\n No spam to my email, please!");

    Transport.send(message);

    System.out.println("Done");

} catch (MessagingException e) {
    throw new RuntimeException(e);
}
     */
}