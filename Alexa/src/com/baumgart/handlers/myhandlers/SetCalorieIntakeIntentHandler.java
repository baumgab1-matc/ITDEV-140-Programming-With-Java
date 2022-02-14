package com.baumgart.handlers.myhandlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import java.util.Map;
import java.util.Optional;

import static com.baumgart.MainStreamHandler.remainingCalories;
import static com.amazon.ask.request.Predicates.intentName;

public class SetCalorieIntakeIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SetCalorieIntakeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        RequestHelper requestHelper = RequestHelper.forHandlerInput(input);
        String speechText = "";

        //get name from slot
        Optional<String> dailyCalories = requestHelper.getSlotValue("daily_calories");

        if (dailyCalories.isPresent()) {
            try {
                int calories = Integer.parseInt(dailyCalories.get());

                if (calories >= 0) {
                    //set session attributes
                    AttributesManager attributesManager = input.getAttributesManager();
                    Map<String,Object> attributes = attributesManager.getSessionAttributes();

                    //add to session
                    attributes.put("daily_calories", dailyCalories.get());

                    //save to session
                    attributesManager.setSessionAttributes(attributes);
                    remainingCalories = Integer.parseInt(dailyCalories.get());

                    speechText = "Your daily calorie goal has been set. Say 'add food' to add food.";
                } else {
                    speechText = "Sorry I cannot set negative numbers to be your daily calorie limit.";
                }


            } catch (NumberFormatException e) {
                speechText = "Sorry there was a problem setting your daily calories. Make sure you give me a number.";
            }
        }


        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .build();
    }

}
