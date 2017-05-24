package edu.udacity.mou.bakingapp.bus.events;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by mou on 23/05/17.
 */

@Builder
@Data
public class RecipesGotEvent {
    private boolean success;
}
