package com.baumgart.handlers.myhandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import com.baumgart.FoodItem;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.baumgart.MainStreamHandler.meals;
import static com.baumgart.MainStreamHandler.remainingCalories;

public class ForgetFoodIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ForgetFoodIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        RequestHelper requestHelper = RequestHelper.forHandlerInput(input);
        String speechText = "";

        //get name from slot
        String mealToForget = requestHelper.getSlotValue("meal_to_forget").get();

        Optional<FoodItem> toForget = meals.stream().filter(meal -> meal.getName().equalsIgnoreCase(mealToForget)).findFirst();

        if (toForget.isPresent()) {
            speechText = "Meal has been dropped";
            remainingCalories += toForget.get().getCalories();
            meals.remove(toForget.get());
        } else {
            speechText = "Looks like that meal hasn't been added.";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .build();
    }
}
