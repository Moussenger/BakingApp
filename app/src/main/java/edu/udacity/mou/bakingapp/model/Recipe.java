package edu.udacity.mou.bakingapp.model;

import org.parceler.Parcel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by mou on 22/05/17.
 */

@Data
@Builder
@AllArgsConstructor(suppressConstructorProperties=true)
@Parcel
public class Recipe {
    int id;
    int servings;
    String name;
    String image;
    List<Ingredient> ingredients;
    List<Step> steps;

    public Recipe() {

    }
}
