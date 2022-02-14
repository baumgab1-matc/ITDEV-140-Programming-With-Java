package com.baumgart.handlers.myhandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;
import static com.baumgart.MainStreamHandler.meals;

public class SayMealsIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SayMealsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        StringBuilder holder = new StringBuilder();
        int calorieCount = 0;

        //check if any food has been added
        if (meals.isEmpty()) {
            holder.append("Looks like no food has been added yet. You can add food by saying 'add food'.");
        } else {

            holder.append("Today you have had ");
            //an egg and an apple

            if (meals.size() == 1) {
                String meal = grammarHelper(meals.get(0).getName());
                holder.append(meal).append(".");
                calorieCount += meals.get(0).getCalories();
            } else {
                for (int i = 0; i < meals.size(); i++) {

                    String mealName = meals.get(i).getName();

                    //check fo last item
                    if (i == meals.size() - 1) {
                        holder.append("and ");
                        holder.append(grammarHelper(mealName));
                        holder.append(". ");
                    } else {
                        holder.append(grammarHelper(mealName)).append(", ");
                    }

                    calorieCount += meals.get(i).getCalories();
                }
            }

            holder.append(" Total calories so far is ").append(calorieCount);
        }

        String speechText = holder.toString();

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withReprompt(speechText)
                .build();
    }


    private String grammarHelper(String str) {
        String vowels = "aeiouAEIOU";
        String result;

        if (vowels.contains("" + str.charAt(0))) {
            result = "an " + str;
        } else {
            result = "a " + str;
        }

        return result;
    }

}
