package com.example.demo.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class Main {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://api-dev.reddio.com/v1/mints");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");

		httpConn.setRequestProperty("content-type", "application/json");
		httpConn.setRequestProperty("X-API-Key", "rk-f14ae3cb-4279-482c-badc-3bc97fe6ef91");

		httpConn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write("{ \"contract_address\":\"0x21562034154c968879e1b5190384024b6dc681cd\", \"stark_key\":\"0x5eb68e02aa951a8b7590e15b7b14a1d3b5314f874ee78287a68632a3390756e\", \"amount\":\"10\"}");
		writer.flush();
		writer.close();
		httpConn.getOutputStream().close();

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
	}
}
