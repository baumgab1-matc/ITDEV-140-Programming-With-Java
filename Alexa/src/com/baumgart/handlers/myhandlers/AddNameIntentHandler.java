package com.baumgart.handlers.myhandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;

import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

public class AddNameIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddNameIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        RequestHelper requestHelper = RequestHelper.forHandlerInput(input);

        //get name from slot
        Optional<String> name = requestHelper.getSlotValue("name");

        //save name to database
        input.getAttributesManager().getPersistentAttributes().put("name", name.get());
        input.getAttributesManager().savePersistentAttributes();

        String speechText = "Great, your name has been added.";

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .build();
    }

}
