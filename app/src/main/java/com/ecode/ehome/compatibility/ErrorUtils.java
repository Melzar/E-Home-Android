package com.ecode.ehome.compatibility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.models.errors.ErrorResponse;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Utility class providing methods needed for parsing JSON API Spec errors.
 *
 * @author jbegic
 */
public class ErrorUtils {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	private ErrorUtils() {
		// Private constructor
	}

	/**
	 * Parses provided ResponseBody and returns it as ErrorResponse.
	 * @param errorResponse error response body
	 * @return ErrorResponse collection
	 * @throws IOException
	 */
	public static ErrorResponse parseErrorResponse(ResponseBody errorResponse) throws IOException {
		return MAPPER.readValue(errorResponse.bytes(), ErrorResponse.class);
	}

	/**
	 * Parses provided JsonNode and returns it as ErrorResponse.
	 * @param errorResponse error response body
	 * @return ErrorResponse collection
	 * @throws JsonProcessingException thrown in case JsonNode is not parseable
	 */
	public static ErrorResponse parseError(JsonNode errorResponse) throws JsonProcessingException {
		return MAPPER.treeToValue(errorResponse, ErrorResponse.class);
	}

}
