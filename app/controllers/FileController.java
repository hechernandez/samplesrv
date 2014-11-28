//package controllers;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//
//import models.Contact;
//import models.ContactList;
//import play.api.Play;
//import play.libs.Json;
//import play.mvc.BodyParser;
//import play.mvc.Controller;
//import play.mvc.Result;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
//public class FileController extends Controller {
//	
//	public static Result getText(){
//		File realFile = Application.getFile();
//		System.out.println("File: " + realFile.toString());
//		FileReader file;
//		try {
//			file = new FileReader(realFile);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("File not Found");
//			return notFound(); // 404
//		}
//		
//		String temp = null;
//		
//		BufferedReader br = new BufferedReader(file);
//		try {
//			String line = br.readLine();
//
//			while (line != null) {
//				temp += temp + line;
//		        line = br.readLine();
//		    }
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				br.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return ok(temp);
//	}
//}
