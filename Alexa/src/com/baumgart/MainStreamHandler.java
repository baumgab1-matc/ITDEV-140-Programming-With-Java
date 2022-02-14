/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.baumgart;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import com.baumgart.handlers.*;
import com.baumgart.handlers.myhandlers.*;

import java.util.ArrayList;
import java.util.List;

public class MainStreamHandler extends SkillStreamHandler {

    public static int remainingCalories = 0;

    //todo in the future make this a database
    public static List<FoodItem> meals = new ArrayList<>();

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new AddNameIntentHandler(),
                        new ForgetNameIntentHandler(),
                        new SetCalorieIntakeIntentHandler(),
                        new SayCalorieIntakeIntentHandler(),
                        new SayRemainingCaloriesIntentHandler(),
                        new AddFoodIntentHandler(),
                        new ForgetFoodIntentHandler(),
                        new SayMealsIntentHandler(),
                        new FallbackIntentHandler())
                .withSkillId("amzn1.ask.skill.3e062f2c-81b5-4b6d-bb1e-0db15f2f96ac")
                .withTableName("user_name")
                .withAutoCreateTable(true)
                .build();
    }

    public MainStreamHandler() {
        super(getSkill());
    }

}
