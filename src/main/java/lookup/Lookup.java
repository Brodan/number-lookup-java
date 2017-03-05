package hello;

import java.util.Optional;

import com.twilio.Twilio;
import com.twilio.rest.lookups.v1.PhoneNumber;

public class Lookup {
    public static final String ACCOUNT_SID = Optional.ofNullable(
        System.getenv("TWILIO_ACCOUNT_SID")).orElseThrow(
        () -> new IllegalArgumentException("TWILIO_ACCOUNT_SID is not set in the environment"));
    public static final String AUTH_TOKEN = Optional.ofNullable(
        System.getenv("TWILIO_AUTH_TOKEN")).orElseThrow(
        () -> new IllegalArgumentException("TWILIO_AUTH_TOKEN is not set in the environment"));

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        PhoneNumber number = PhoneNumber
            .fetcher(new com.twilio.type.PhoneNumber("")) // TODO: Gather command line arg.
            .setType("carrier")
            .fetch();

        System.out.println(number.getCarrier().get("name"));
        System.out.println(number.getCarrier().get("type"));
    }
}