package com.example.rummenigged.archandroidguidelinetest.data.util;

import com.example.rummenigged.archandroidguidelinetest.data.model.CryptocurrencyRaw;

/**
 * Created by rummenigged on 26/03/18.
 */

public class CryptocurrencyMapper implements DataMapper<CryptocurrencyRaw>{

    @Override
    public void assertEssentialParams(CryptocurrencyRaw data) throws EssentialParamMissingException {
        String missingParams = "";

        if (data.getId() == null){
            missingParams += " Id";
        }

        if (data.getName() == null){
            missingParams += " Name";
        }

        if (data.getRank() == 0){
            missingParams += " Rank";
        }

        if (data.getPriceUsd() == 0){
            missingParams += " PriceUsd";
        }

//        if (data.getMaxSupply() == 0){
//            missingParams += " MaxSupply";
//        }

        if (!missingParams.isEmpty()){
            throw new EssentialParamMissingException(missingParams, data);
        }
    }
}
