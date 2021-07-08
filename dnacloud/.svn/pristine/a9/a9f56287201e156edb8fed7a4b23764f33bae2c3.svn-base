package com.bazl.dna.zuul.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
/**
 *
 * @author zhaoyong
 *
 */
public class HttpResponseUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpResponseUtil.class);

	private HttpResponseUtil() {

	}

	public static ClientHttpResponse response(final HttpStatus status, String json) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() {
				return status;
			}

			@Override
			public int getRawStatusCode() {
				return status.value();
			}

			@Override
			public String getStatusText() {
				return status.getReasonPhrase();
			}

			@Override
			public void close() {
				// close
			}

			@Override
			public InputStream getBody() {
				try {
					return new ByteArrayInputStream(new String(json.getBytes(), StandardCharsets.UTF_8.name()).getBytes());
				} catch (UnsupportedEncodingException e) {
					LOGGER.error("Error: ", e);
				}
				return null;
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}
		};
	}
}
