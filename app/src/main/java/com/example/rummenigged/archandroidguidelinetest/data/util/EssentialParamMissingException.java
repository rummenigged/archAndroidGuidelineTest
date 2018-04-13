package com.example.rummenigged.archandroidguidelinetest.data.util;

import android.support.annotation.NonNull;

/**
 * Created by rummenigged on 26/03/18.
 */

public class EssentialParamMissingException extends RuntimeException {
    EssentialParamMissingException(@NonNull final String missingParam, @NonNull final Object rawObject){
        super("The params: " + missingParam + " are missing in received object: " + rawObject);
    }

}
