package edu.udacity.mou.bakingapp.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by mou on 22/05/17.
 */

@Data
@Builder
public class Recipe {
    private int id;
    private int servings;
    private String name;
    private String image;
    private List<Ingredient> ingredients;
    private List<Step> steps;
}
