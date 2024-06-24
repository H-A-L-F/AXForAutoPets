package constants;

import java.util.ArrayList;
import java.util.Arrays;

public enum DevelopmentState {
    INSERT_MATCH,
    GET_ENM_ROUND,
    INSERT_ROUND;

    public static final ArrayList<DevelopmentState> state = new ArrayList<>(Arrays.asList(
            INSERT_MATCH,
//            GET_ENM_ROUND,
            INSERT_ROUND
    ));
}
