package com.casumo.hometest.videorentalstore.films.rest.mapper;

import com.casumo.hometest.videorentalstore.films.bizz.InsertFilmParameter;
import com.casumo.hometest.videorentalstore.films.rest.InsertFilmRequestBody;


/**
 * InsertFilmParameterMapper class.
 */
public class InsertFilmParameterMapper
{
    public static InsertFilmParameter map(final InsertFilmRequestBody requestBody)
    {
        if (requestBody != null)
        {
            final InsertFilmParameter parameter = new InsertFilmParameter();
            parameter.setName(requestBody.getName());
            parameter.setFilmTypeId(requestBody.getFilmTypeId());
            return parameter;
        }
        return null;
    }
}
