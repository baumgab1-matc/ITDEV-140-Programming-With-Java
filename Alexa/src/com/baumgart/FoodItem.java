package com.baumgart;

public class FoodItem {

    private final String name;
    private final int calories;

    public FoodItem(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodItem foodItem = (FoodItem) o;

        if (calories != foodItem.calories) return false;
        return name != null ? name.equals(foodItem.name) : foodItem.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + calories;
        return result;
    }
}
