package com.example.rummenigged.archandroidguidelinetest.data.util;

/**
 * Created by rummenigged on 26/03/18.
 */

public interface DataMapper<DataType> {

    void assertEssentialParams(DataType data) throws EssentialParamMissingException;
}
