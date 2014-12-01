package controllers;

import models.Contact;
import models.ContactList;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ContactController extends Controller {

	/**
	 * Obtains the contacts with specified id.
	 * @param id the contact's id
	 * @return 404 if contact not found, 200 if found
	 */
	public static Result getContact(Long id){
		System.out.println("Get id: "+ id);
		ObjectNode result = Json.newObject();
		ContactList theList = ContactList.getInstance();
		Contact C = theList.getContactById(id);
		if (C == null){
			System.out.println("Not Found");
			return notFound(); // 404
		}
		else {
			System.out.println("Get: " + C);
			result.put("Contact", Json.toJson(C));
			return ok(result);
		}
	}
	
	/**
	 * Obtains all contacts.
	 * @return all of the contacts stored in text file
	 */
	public static Result getAllContacts(){
		ObjectNode result = Json.newObject();
		ContactList theList = ContactList.getInstance();
		Contact[] C = theList.getAllContacts();
		if (C == null){
			System.out.println("Not Found");
			return notFound(); // 404
		}
		else {
			System.out.println("Got all contacts.");
			result.put("Contacts", Json.toJson(C));
			return ok(result);
		}
	}

	/**
	 * Stores new contact.
	 * @return a copy of the contact created
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public static Result storeContact(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("Read Data");
			JsonNode json = request().body().asJson();
			System.out.println("Json Success: " + json);
			System.err.println("json: " + json);
			
			Contact newContact = mapper.readValue(json.toString(), Contact.class);
			ContactList theList = ContactList.getInstance();
			newContact = theList.addContact(newContact);
			System.out.println(newContact.toString());
			
			ObjectNode result = Json.newObject();
			result.put("Contact", Json.toJson(newContact));
			return created(result);
		}
		catch(Exception e){
			e.printStackTrace();
			return badRequest("Missing information");
		}

	}

	/**
	 * Updates the contact with the specified id.
	 * @param id the contact's id
	 * @return 404 if contact not found, 200 if found
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public static Result updateContact(Long id){
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("Reading Data from contact with id " + id + ".");
			JsonNode json = request().body().asJson();
			System.out.println("Json Success: " + json);
			System.err.println("json: " + json);
			
			Contact updContact = mapper.readValue(json.toString(), Contact.class);
			System.out.println(updContact.toString());
			
			ContactList theList = ContactList.getInstance();
			updContact = theList.updateContact(updContact);

			System.err.println("Updating: " + updContact);
			
			ObjectNode result = Json.newObject();
			result.put("Contact", Json.toJson(updContact));
			return ok(result);		
		}
		catch(Exception e){
			e.printStackTrace();
			return badRequest("Missing information");			
		}
	}

	/**
	 * Deletes contact with the specified id.
	 * @param id the id of the contact to be deleted
	 * @return 204 if OK with no content to return, 404 otherwise
	 */
	public static Result deleteContact(Long id){
		System.out.println("Get id: "+ id);
		ContactList theList = ContactList.getInstance();
		boolean erased = theList.deleteContact(id);
		if (erased){
			System.out.println("Erased");
			// This is code 204 - OK with no content to return
			return noContent();
		}
		else {
			System.out.println("Not Erased");
			return notFound("Contact not found");
		}

	}

}
