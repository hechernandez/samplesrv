package models;

import controllers.ContactStore;

public class ContactList {
	
	private static ContactStore storedContacts = new ContactStore();
	private static SortedArrayList<Contact> contactList = new SortedArrayList<Contact>();
	private static int counter = 1;
	
	public ContactList() {
		contactList = storedContacts.readContacts();
		System.out.println(contactList.toString());
	}
	
	/**
	 * Obtains all contacts in the file.
	 */
	public void obtainList(){
		contactList = storedContacts.readContacts();
	}
	
	/**
	 * Returns the contact in the contact list with the specified id.
	 * @param id the id that will be looked for
	 * @return
	 */
	public Contact getContactById(long id){
		System.err.println("List len: " + contactList.size());
		Contact temp = new Contact();
		for (int i = 0; i < contactList.size(); i++) {
			temp = contactList.get(i);
			System.err.println("id: " + id +  "c.Id: " + temp.getId());
			if (temp.getId() == id){
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Returns all of the contacts stored in the server side
	 * @return an array with all contacts in the server
	 */
	public Contact[] getAllContacts(){
		Contact result[] = new Contact[contactList.size()];
		for (int i=0; i < contactList.size(); ++i)
			result[i] = contactList.get(i);
		return result;
	}
	
	/**
	 * Adds a new contact to the server's list
	 * @param obj the contact to be added
	 * @return the contact that was added successfully
	 */
	public Contact addContact(Contact obj){
		
		SortedArrayList<Integer> idVals = new SortedArrayList<Integer>();
		for (int i = 0; i < contactList.size(); i++)
			idVals.add( (int) contactList.get(i).getId()); // add all ids in order

		for(int i=0; i < contactList.size(); i++){
			if(idVals.get(i) == counter)
				counter++; // if there is already a contact with counter as id, continue searching for a new one
		}
		
		long id = counter;
		
		obj.setId(id);
		System.out.println("ID " + id);
		
		contactList.add(obj);
		storedContacts.writeContacts(contactList);
		return obj;
	}
	
	/**
	 * Erases the contact with the specified Id in the server.
	 * @param id the id that will be looked for to delete that contact
	 * @return true if delete was successful, false otherwise
	 */
	public boolean deleteContact(long id){
		int target = -1;
		
		for (int i=0; i < contactList.size(); ++i){
			if (contactList.get(i).getId() == id){
				target = i;
				break;
			}
		}
		if (target == -1){
			return false;
		}
		else {
			contactList.remove(target);
			storedContacts.writeContacts(contactList);
			return true;
		}
	}
	
	/**
	 * Updates the information of a specific contact
	 * @param obj the contact to be added
	 * @return the contact that was added if successful
	 */
	public Contact updateContact(Contact obj){
		int target = -1;
		
		for (int i=0; i < contactList.size(); i++){
			if (contactList.get(i).getId() == obj.getId()){
				target = i;
				break;
			}
		}
		if (target == -1){
			return null;
		}
		else {
			Contact C = contactList.get(target);
			C.setFirstName(obj.getFirstName());
			C.setLastName(obj.getLastName());
			C.setCellPhone(obj.getCellPhone());
			C.setWorkPhone(obj.getWorkPhone());
			C.setEmail(obj.getCellPhone());
			
			storedContacts.writeContacts(contactList);
			
			return C;
		}
		
	}
	
	private static ContactList singleton = new ContactList();
		
	public static ContactList getInstance(){
		return singleton;
	}	
	
}
