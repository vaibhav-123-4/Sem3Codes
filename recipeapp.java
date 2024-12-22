//Create a recipe app that allows user to save their recipes. To prevent accidental modifications to original recipes you need to implement a deep copy mechanism

class instructions {
    String step;

    instructions(String step) {
        this.step = step;
    }

    
    instructions(instructions original) {
        this.step = original.step;
    }

    public String toString() {
        return step;
    }
}

class ingredients {
    String ingredient;

    ingredients(String ingredient) {
        this.ingredient = ingredient;
    }

    
    ingredients(ingredients original) {
        this.ingredient = original.ingredient;
    }

    public String toString() {
        return ingredient;
    }
}

class recipe {
    String name;
    instructions[] steps;
    ingredients[] ingredients;

    // Original constructor
    recipe(String name, instructions[] steps, ingredients[] ingredients) {
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
    }

    
    recipe(recipe original) {
        this.name = original.name;

        // Deep copying the steps array
        this.steps = new instructions[original.steps.length];
        for (int i = 0; i < original.steps.length; i++) {
            this.steps[i] = new instructions(original.steps[i]);  // Call deep copy constructor for each instruction
        }

        // Deep copying the ingredients array
        this.ingredients = new ingredients[original.ingredients.length];
        for (int i = 0; i < original.ingredients.length; i++) {
            this.ingredients[i] = new ingredients(original.ingredients[i]);  // Call deep copy constructor for each ingredient
        }
    }

    public String toString() {
        String result = "Recipe for : " + name + "\n";
        result += "Ingredients: \n";
        for (int i = 0; i < ingredients.length; i++) {
            result += ingredients[i] + "\n";
        }
        result += "Instructions: \n";
        for (int i = 0; i < steps.length; i++) {
            result += steps[i] + "\n";
        }
        return result;
    }
}

public class recipeapp {
    public static void main(String[] args) {
        instructions[] steps = new instructions[3];
        steps[0] = new instructions("Step 1: Boil water");
        steps[1] = new instructions("Step 2: Add pasta");
        steps[2] = new instructions("Step 3: Cook for 10 minutes");

        ingredients[] ingredients = new ingredients[2];
        ingredients[0] = new ingredients("Pasta");
        ingredients[1] = new ingredients("Water");

        // Creating the original recipe
        recipe recipe1 = new recipe("Pasta", steps, ingredients);
        System.out.println("Original Recipe:");
        System.out.println(recipe1);

        // Creating a deep copy of recipe1
        recipe recipeCopy = new recipe(recipe1);
        System.out.println("Copied Recipe:");
        System.out.println(recipeCopy);

    }
}
