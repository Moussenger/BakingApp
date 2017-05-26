package edu.udacity.mou.bakingapp.model;

import org.parceler.Parcel;

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
public class Ingredient {
    float quantity;
    String measure;
    String ingredient;
    Recipe recipe;

    public Ingredient() {

    }
}
