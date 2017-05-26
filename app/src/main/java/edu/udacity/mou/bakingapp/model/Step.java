package edu.udacity.mou.bakingapp.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Builder;

/**
 * Created by mou on 22/05/17.
 */

@Data
@Builder
@Parcel
@AllArgsConstructor(suppressConstructorProperties=true)
public class Step {
    @SerializedName("id")
    int position;
    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;
    Recipe recipe;

    public Step() {

    }
}
