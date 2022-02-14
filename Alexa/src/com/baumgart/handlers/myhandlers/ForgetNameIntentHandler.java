package com.baumgart.handlers.myhandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class ForgetNameIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ForgetNameIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;

        //check if name is in db
        if (input.getAttributesManager().getPersistentAttributes().containsKey("name")) {
            input.getAttributesManager().getPersistentAttributes().clear();
            input.getAttributesManager().savePersistentAttributes();
            speechText = "Your name has been forgotten.";
        } else {
            speechText = "Looks like theres no name for me to forget.";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .build();
    }
}
