package com.ecode.ehome.compatibility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.retrofit.RetrofitType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * JSON API request/response converter factory.
 *
 * @author jbegic
 */
public class JSONAPIConverterFactory extends Converter.Factory {
	private ResourceConverter parser;
	private Converter.Factory alternativeFactory;

	public JSONAPIConverterFactory(ResourceConverter parser) {
		this.parser = parser;
	}

	public JSONAPIConverterFactory(ObjectMapper mapper, Class<?>... classes) {
		this.parser = new ResourceConverter(mapper, classes);
	}

	/**
	 * Sets alternative converter factory to use in case type is cannot be handled by this factory. <br />
	 *
	 * This method is useful in cases where you want to use same retrofit instance to consume primary JSON API spec
	 * APIs and some other APIs that are not JSON API spec compliant, eg. JSON.
	 * @param alternativeFactory factory implementation
	 */
	public void setAlternativeFactory(Converter.Factory alternativeFactory) {
		this.alternativeFactory = alternativeFactory;
	}

	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		RetrofitType retrofitType = new RetrofitType(type);

		if (retrofitType.isValid() && parser.isRegisteredType(retrofitType.getType())) {
			if (retrofitType.isCollection()) {
				return new JSONAPIResponseBodyConverter<>(parser, retrofitType.getType(), true);
			} else {
				return new JSONAPIResponseBodyConverter<>(parser, retrofitType.getType(), false);
			}
		} else if (alternativeFactory != null) {
			return alternativeFactory.responseBodyConverter(type, annotations, retrofit);
		} else {
			return null;
		}
	}

	@Override
	public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
		RetrofitType retrofitType = new RetrofitType(type);

		if (retrofitType.isValid() && parser.isRegisteredType(retrofitType.getType())) {
			return new JSONAPIRequestBodyConverter<>(parser);
		} else if (alternativeFactory != null) {
			return alternativeFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
		} else {
			return null;
		}
	}
}
