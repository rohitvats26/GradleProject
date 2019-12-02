package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<String> getToken() {
		System.out.println("rohit");
		String id = "53d7e14aee681a0034030003";
		String key = "pXeTVcmdbU9XxH6fPcPlq8Y9D9G3Cdo5Eh2nMSgKj/DWqeSFFXDdmpz5Trv+L2hQNM+nGa704Rf8Z22W9O1jdQ==";
		/*
		 * String expiry = DateTime.UtcNow.AddDays(10); using (String encoder = new
		 * HMACSHA512(Encoding.UTF8.GetBytes(key))) { String dataToSign = id + "\n" +
		 * expiry.ToString("O", CultureInfo.InvariantCulture); String hash =
		 * encoder.ComputeHash(Encoding.UTF8.GetBytes(dataToSign)); String signature =
		 * Base64.ToBase64String(hash); String encodedToken =
		 * String.format("SharedAccessSignature uid={0}&ex={1:o}&sn={2}", id, expiry,
		 * signature); Console.WriteLine(encodedToken); }
		 */
		return new ResponseEntity<String>("rohit", HttpStatus.OK);
	}

	public static void testGit() {

		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		Repository repository = null;
		try {
			repository = builder.setGitDir(new File("/ds/ds")).readEnvironment().findGitDir().setMustExist(true)
					.build();
			Git git = new Git(repository);
			AddCommand cmd = git.add();
			cmd.addFilepattern("someDirectory").call();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoFilepatternException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
