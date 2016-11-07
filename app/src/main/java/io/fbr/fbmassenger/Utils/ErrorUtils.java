package io.fbr.fbmassenger.Utils;

import java.io.IOException;
import java.lang.annotation.Annotation;

import io.fbr.fbmassenger.Model.ErrorModel;
import io.fbr.fbmassenger.Network.MassengerProvider;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by farhad on 11/1/16.
 */

public class ErrorUtils {
    public static ErrorModel parseError(Response<?> response){
        MassengerProvider provider=new MassengerProvider();

        Converter<ResponseBody,ErrorModel> converter=provider.getRetrofitClient().responseBodyConverter(ErrorModel.class,new Annotation[0]);

        ErrorModel errorModel;


        try {
            errorModel=converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorModel();
        }
        return errorModel;

    }
}
