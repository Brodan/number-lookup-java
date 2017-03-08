package lookup;

import java.util.Optional;

import com.twilio.Twilio;
import com.twilio.rest.lookups.v1.PhoneNumber;

public class Lookup {
    
    public static final String ACCOUNT_SID = Optional.ofNullable(
        System.getenv("TWILIO_ACCOUNT_SID")).orElseThrow(
        () -> new IllegalArgumentException("TWILIO_ACCOUNT_SID is not set in the environment."));
    
    public static final String AUTH_TOKEN = Optional.ofNullable(
        System.getenv("TWILIO_AUTH_TOKEN")).orElseThrow(
        () -> new IllegalArgumentException("TWILIO_AUTH_TOKEN is not set in the environment."));

    public static void main(String[] args) {
        if(args.length == 0) {
            throw new IllegalArgumentException("Missing command line argument.");
        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String lookup_number = args[0];

        PhoneNumber number = PhoneNumber
            .fetcher(new com.twilio.type.PhoneNumber(lookup_number))
            .setType("carrier")
            .fetch();

        System.out.println("Carrier name: " + number.getCarrier().get("name"));
        System.out.println("Number type: " + number.getCarrier().get("type"));
    }
}