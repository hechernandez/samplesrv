package controllers;

import models.Contact;
import models.ContactAddress;
import models.SortedArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactStore {

	private File file;
	private FileInputStream input;
	private BufferedReader br;
	private InputStreamReader inputStream;

	private PrintWriter writer;

	private final int CONTACT_FIELDS = 6;
	private int ADDRESS_FIELDS = 0;

	public ContactStore() {
	}

	/**
	 * Obtains contacts in file.
	 */
	public SortedArrayList<Contact> readContacts() {

		SortedArrayList<Contact> tempList = new SortedArrayList<Contact>();

		try {

			this.file = Application.getFile(); 
			this.input = new FileInputStream(this.file); //initialize fileinputstream
			inputStream = new InputStreamReader(this.input); //initialize inputstreamreader
			br = new BufferedReader(inputStream); //initialize our reader

		} catch(Exception e) {
			e.getStackTrace();
		}

		try{

			String line = br.readLine();

			while (line != null) {
				Contact temp = new Contact();
				for(int i=0; i < CONTACT_FIELDS; i++){ //from first name to addressfields
					if(i == 0){
						temp.setId((long)Long.valueOf(line.substring(2))); // extract id number from format id#
					}
					if(i == 1){
						temp.setFirstName(line);
					}
					if(i == 2){
						temp.setLastName(line);
					}
					if(i == 3){
						temp.setCellPhone(line);
					}
					if(i == 4){
						temp.setWorkPhone(line);
					}
					if(i == 5){
						temp.setEmail(line);
					}
//					if(i == 6){
//						this.ADDRESS_FIELDS = Integer.valueOf((line.substring(13))); // extract address fields from format addressfields#
//					}
					line = br.readLine();
				}

//				for(int i=0; i < ADDRESS_FIELDS; i++){ //from address name to zip code
//					ContactAddress contactAddress = new ContactAddress();
//					for(int j=0; j < 6; j++){
//						if(j == 0){
//							System.out.println(line);
//							contactAddress.setAddressName(line);
//						}
//						if(j == 1){
//							System.out.println(line);
//							contactAddress.setStreet(line);
//						}
//						if(j == 2){
//							System.out.println(line);
//							contactAddress.setStreetNumber(line);
//						}
//						if(j == 3){
//							System.out.println(line);
//							contactAddress.setCity(line);
//						}
//						if(j == 4){
//							System.out.println(line);
//							contactAddress.setState(line);
//						}
//						if(j == 5){
//							System.out.println(line);
//							contactAddress.setZipCode(line);
//						}
//
//						line = br.readLine();
//					}
//					temp.setAddress(contactAddress);
//				}				
				tempList.add(temp);
			}
			inputStream.close();
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tempList;
	}

	/**
	 * Writes contacts to file.
	 * @param contacts the contacts that will be written in the file
	 */
	public void writeContacts(SortedArrayList<Contact> contacts){

		try{

			writer = new PrintWriter(Application.getFile());

			for(int i=0; i < contacts.size(); i++){
				Contact temp = contacts.get(i);

				writer.write("id" + temp.getId() + "\n");
				writer.write(temp.getFirstName() + "\n");
				writer.write(temp.getLastName() + "\n");
				writer.write(temp.getCellPhone() + "\n");
				writer.write(temp.getWorkPhone() + "\n");
				writer.write(temp.getEmail() + "\n");

//				if(temp.getAddress() != null) {
//					writer.write("addressfields1");
//					writer.write(temp.getAddress().getAddressName() + "\n");
//					writer.write(temp.getAddress().getStreet() + "\n");
//					writer.write(temp.getAddress().getStreetNumber() + "\n");
//					writer.write(temp.getAddress().getCity() + "\n");
//					writer.write(temp.getAddress().getState() + "\n");
//					writer.write(temp.getAddress().getZipCode() + "\n");
//				}
//				else {
//					writer.write("addressfields0");
//						              writer.write("N/A" + "\n");
//						              writer.write("N/A" + "\n");
//						              writer.write("N/A" + "\n");
//						              writer.write("N/A" + "\n");
//						              writer.write("N/A" + "\n");
//						              writer.write("N/A" + "\n");
//				}				
			}

			writer.close();

		} catch(IOException e){
			e.printStackTrace();
		}
	}
}

