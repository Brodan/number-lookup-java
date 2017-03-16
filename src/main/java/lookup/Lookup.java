package lookup;

import com.twilio.Twilio;
import com.twilio.rest.lookups.v1.PhoneNumber;

public class Lookup {
    
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static void main(String[] args) {
        if(args.length == 0) {
            throw new IllegalArgumentException("Missing command line argument.");
        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String lookup_number = args[0];

        try {
            PhoneNumber number = PhoneNumber
                .fetcher(new com.twilio.type.PhoneNumber(lookup_number))
                .setType("carrier")
                .fetch();

            System.out.println("Carrier name: " + number.getCarrier().get("name"));
            System.out.println("Number type: " + number.getCarrier().get("type"));

        } catch(com.twilio.exception.ApiException e) {
            if(e.getStatusCode() == 404) {
                System.out.println("Phone number not found.");
            } else {
                throw e;
            }
        }
    }
}
