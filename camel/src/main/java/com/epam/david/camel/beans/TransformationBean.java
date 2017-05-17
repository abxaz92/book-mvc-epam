package com.epam.david.camel.beans;

/**
 * Created by David_Chaava on 5/17/2017.
 */
public class TransformationBean {
    public String makeUpperCase(String inputString) {
        if (inputString == null)
            return null;
        return inputString.toUpperCase();
    }
}
