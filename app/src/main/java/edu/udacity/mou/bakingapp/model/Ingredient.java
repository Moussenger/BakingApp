package edu.udacity.mou.bakingapp.model;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by mou on 22/05/17.
 */

@Data
@Builder
public class Ingredient {
    private float quantity;
    private String measure;
    private String ingredient;
    private Recipe recipe;
}
