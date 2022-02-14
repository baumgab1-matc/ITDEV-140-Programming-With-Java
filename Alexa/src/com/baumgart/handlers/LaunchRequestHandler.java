/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.baumgart.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import java.util.Map;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.requestType;
import static com.baumgart.MainStreamHandler.meals;
import static com.baumgart.MainStreamHandler.remainingCalories;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;

        //reset past variables
        meals.clear();
        remainingCalories = 0;

        //check if name is in db
        Map<String, Object> persistentAttributes= input.getAttributesManager().getPersistentAttributes();

        if (persistentAttributes.containsKey("name")) {
            String name = persistentAttributes.get("name").toString();
            speechText = "Welcome back " + name + ". Don't forget to set your daily calorie goal by saying 'set calories'.";
        } else {
            speechText = "Welcome to the Alexa Skills Calorie Counter. It looks like you're new. " +
                    "You can add your name by saying 'add name'. Or you can get started by saying 'set calories'.";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withReprompt(speechText)
                .build();
    }

}
