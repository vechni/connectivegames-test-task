package com.semyonov.module.input;

public class Validation
{
    private final int MIN_VALUE_OF_PARAM = 1;
    private final String MESSAGE_PARAM_IS_NEGATIVE = "Error! The parameter must be greater than zero.";

    public boolean isNotCorrectParam( final int param ){
        if( param < MIN_VALUE_OF_PARAM ){
            System.out.println(MESSAGE_PARAM_IS_NEGATIVE);
            return true;
        }

        return false;
    }
}
