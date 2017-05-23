package edu.udacity.mou.bakingapp.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by mou on 22/05/17.
 */

@Data
@Builder
public class Step {
    @SerializedName("id")
    private int position;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;
    private Recipe recipe;

}
