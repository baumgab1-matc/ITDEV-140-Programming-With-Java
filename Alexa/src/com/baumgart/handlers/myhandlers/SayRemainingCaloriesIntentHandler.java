package com.baumgart.handlers.myhandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.baumgart.MainStreamHandler.remainingCalories;

public class SayRemainingCaloriesIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SayRemainingCaloriesIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String calorieIntake = (String) input.getAttributesManager().getSessionAttributes().get("daily_calories");
        String speechText;

        if (calorieIntake != null && !calorieIntake.isEmpty()) {
            speechText = String.format("You have %d calories left", remainingCalories);
        } else {
            speechText = "Your daily limit has not been set, say 'set calories' to set limit.";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .build();
    }
}
