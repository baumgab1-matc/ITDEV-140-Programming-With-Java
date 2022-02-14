package com.baumgart.handlers.myhandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import com.baumgart.FoodItem;

import java.util.Map;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;
import static com.baumgart.MainStreamHandler.meals;
import static com.baumgart.MainStreamHandler.remainingCalories;

public class AddFoodIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddFoodIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        RequestHelper requestHelper = RequestHelper.forHandlerInput(input);

        Map<String, Object> persistentAttributes= input.getAttributesManager().getPersistentAttributes();

        //get name and calories from slot
        Optional<String> foodName = requestHelper.getSlotValue("food_item_name");
        Optional<String> foodCaloriesStr = requestHelper.getSlotValue("food_item_calories");
        int foodCalories = Integer.parseInt(foodCaloriesStr.get());

        //get calories that user set
        String calorieIntake = (String) input.getAttributesManager().getSessionAttributes().get("daily_calories");

        String speechText;

        //check if calories have been set
        if (calorieIntake != null && !calorieIntake.isEmpty()) {
            //has been set, need to update the calorie intake

            //current calorie standings
            remainingCalories -= foodCalories;

            if (remainingCalories >= 0) {

                if (persistentAttributes.containsKey("name")) {
                    String name = persistentAttributes.get("name").toString();
                    speechText = "Okay " + name + ", I will make note of that.  You now have " + remainingCalories + " calories left.";
                } else {
                    speechText = "Food noted. Calories left for the day " + remainingCalories;
                }

            } else {
                speechText = "Food noted. You are now over daily limit by " + Math.abs(remainingCalories) + " calories.";
            }


        } else {
            speechText = "Your daily calorie limit has not been set, say 'set calories' to set limit so food can be logged.";
        }


        //add food and calories to list
        meals.add(new FoodItem(foodName.get(), foodCalories));

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .build();
    }
}
