/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SmsSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACe83f1455a37c59cff4853461ac443c27";
    public static final String AUTH_TOKEN = "142d3904a8df4468ff05bb9630612d15";

    public static void SendSMS(String to, String from, String body){
        
        Message message = Message
                .creator(new PhoneNumber(to),  // to
                         new PhoneNumber(from),  // from
                         body)
                .create();
    }
}