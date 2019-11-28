package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class HelloController {

	@GetMapping("/")
	@ApiOperation(value = "View Home Input", response = String.class)
	public ResponseEntity<String> getWelcomePage() {

		return new ResponseEntity<String>("Welcome", HttpStatus.OK);
	}

	@GetMapping("/getUser/{input}")
	@ApiOperation(value = "View The Input", response = String.class)
	public ResponseEntity<String> getUser(@PathVariable String input) {

		return new ResponseEntity<String>(input, HttpStatus.OK);
	}

	@GetMapping("hmac")
	public ResponseEntity<String> getEnycription(@RequestParam String key) {
		StringBuilder sb = new StringBuilder();
		sb.append("PUT\n"); // method
		sb.append('\n'); // content encoding
		sb.append('\n'); // content language
		sb.append("0" + '\n'); // content length
		sb.append('\n'); // md5 (optional)
		sb.append('\n'); // content type
		sb.append('\n'); // legacy date
		sb.append('\n'); // if-modified-since
		sb.append('\n'); // if-match
		sb.append('\n'); // if-none-match
		sb.append('\n'); // if-unmodified-since
		sb.append('\n'); // range
		sb.append("x-ms-date:" + "Wed, 27 Nov 2019 14:06:03 GMT" + '\n'); // headers
		sb.append("x-ms-version:2014-02-14\n");
		sb.append("/" + "scodastorage" + "/mycontainer1" + "\nrestype:container");
		String message = sb.toString();
		try {
			String secret = "TeX3YIaHk6YG3sYVrxKWShMLq2Nslxnqi1c1k8nNAGyi9ds59mZuoo4GoyHdPLr2DO0+HwUyeYMUDOue35up5A==";
			byte[] decodedBytes = Base64.getDecoder().decode(secret);
			System.out.println(decodedBytes.toString());
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(decodedBytes, "HmacSHA256");
			sha256_HMAC.init(secret_key);
			String hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes()));
			System.out.println(hash);
			return new ResponseEntity<String>("welcome user " + hash, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error");
		}
		return new ResponseEntity<String>("welcome user", HttpStatus.OK);
	}

	@GetMapping("/getToken")
	@ApiOperation(value = "get The Access Token", response = String.class)
	public ResponseEntity<String> getToken(@RequestHeader String resourceUri, @RequestHeader String keyName,
			@RequestHeader String key) {

		/*
		 * String expiry = DateTime.UtcNow.AddDays(10); using (String encoder = new
		 * HMACSHA512(Encoding.UTF8.GetBytes(key))) { var dataToSign = id + "\n" +
		 * expiry.ToString("O", CultureInfo.InvariantCulture); var hash =
		 * encoder.ComputeHash(Encoding.UTF8.GetBytes(dataToSign)); var signature =
		 * Convert.ToBase64String(hash); var encodedToken =
		 * string.Format("SharedAccessSignature uid={0}&ex={1:o}&sn={2}", id, expiry,
		 * signature); Console.WriteLine(encodedToken); }
		 */
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	public static String getHMAC256(String key, String input) {
		Mac sha256_HMAC = null;
		String hash = null;
		try {
			sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			Encoder encoder = Base64.getEncoder();

			hash = new String(encoder.encode(sha256_HMAC.doFinal(input.getBytes("UTF-8"))));

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return hash;
	}

}
