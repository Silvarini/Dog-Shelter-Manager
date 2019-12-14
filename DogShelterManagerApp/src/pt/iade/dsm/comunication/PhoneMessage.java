package pt.iade.dsm.comunication;

import java.io.IOException;


import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/*
 * 
 * This class will send SMS to the guests about their adoption requests.
 * This is still in development.
 * 
 * */

public class PhoneMessage {
	
	  // Find your Account Sid and Token at twilio.com/user/account
	  public static final String ACCOUNT_SID = "AC75c630888bf429454f670697283a3124";
	  public static final String AUTH_TOKEN = "aa75b4bfb7efc86d6f59bcd13f3bbc4c";

	  private static String messageText=null;
	  
	  
	  public static void sendMsg(String phone) {
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber(phone),
	    								  new PhoneNumber("+18316619150"),
	    								  "Funcionou").create();

	    System.out.println(message.getSid());
	  }
	  
	  
	  public static void sendMesg(String phone, String state) throws IOException, NexmoClientException {
		  NexmoClient client = new NexmoClient.Builder()
				  .apiKey("564acfcc")
				  .apiSecret("OB2aIYyuwIg2TUyX")
				  .build();
		  		
		  if(state.equals("accepted"))
				messageText = "Accepted";
		  if(state.equals("accepted"))
			  	messageText = "Rejected";
			  	
				
				TextMessage message = new TextMessage("Nexmo", phone, messageText);
				SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

				for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
				    System.out.println(responseMessage);
				}
	  }
	}

