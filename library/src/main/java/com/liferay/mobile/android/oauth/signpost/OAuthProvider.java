/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.android.oauth.signpost;

import com.liferay.mobile.android.http.Method;
import com.liferay.mobile.android.http.Request;
import com.liferay.mobile.android.http.client.HttpClient;
import com.liferay.mobile.android.http.client.OkHttpClientImpl;

import java.util.HashMap;

import java.util.Map;
import oauth.signpost.AbstractOAuthProvider;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.http.HttpResponse;

/**
 * @author Javier Gamarra
 */
public class OAuthProvider extends AbstractOAuthProvider {

	public OAuthProvider(
		String requestURL, String accessURL, String authorizeURL) {

		super(requestURL, accessURL, authorizeURL);
	}

	@Override
	protected HttpRequest createRequest(String endpointURL) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("Accept-Encoding","*");

		Request request = new Request(
			Method.GET, map, endpointURL, null, 15000);

		return new RequestWrapper(request);
	}

	@Override
	protected HttpResponse sendRequest(HttpRequest request) throws Exception {
		return new ResponseWrapper(client.send((Request)request.unwrap()));
	}

	protected transient HttpClient client = new OkHttpClientImpl();

}