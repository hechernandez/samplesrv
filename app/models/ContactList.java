package models;

import java.util.UUID;

import controllers.Application;
import controllers.ContactStore;


public class ContactList {

	private static ContactStore storedContacts = new ContactStore();
	private static SortedArrayList<Contact> contactList = new SortedArrayList<Contact>();
	private static int counter = 1;

	public ContactList() {
		contactList = storedContacts.readContacts();
		System.out.println(contactList.toString());
	}

	public Contact getContactById(long id){
		System.err.println("List len: " + contactList.size());
		Contact temp = new Contact();
		for (int i = 0; i < contactList.size(); i++) {
			temp = contactList.get(i);
			System.err.println("id: " + id +  " c.Id: " + temp.getId());
			if (temp.getId() == id){
				return temp;
			}
		}
		return null;
	}

	public Contact[] getAllContacts(){
		Contact result[] = new Contact[this.contactList.size()];
		for (int i=0; i < this.contactList.size(); ++i)
			result[i] = this.contactList.get(i);
		return result;
	}

	public boolean deleteContact(long id){
		int target = -1;

		for (int i=0; i < this.contactList.size(); ++i){
			if (this.contactList.get(i).getId() == id){
				target = i;
				break;
			}
		}
		if (target == -1){
			return false;
		}
		else {
			this.contactList.remove(target);
			return true;
		}
	}

	public Contact updateContact(Contact obj){
		int target = -1;

		for (int i=0; i < this.contactList.size(); i++){
			if (this.contactList.get(i).getId() == obj.getId()){
				target = i;
				break;
			}
		}
		if (target == -1){
			return null;
		}
		else {
			Contact C = this.contactList.get(target);
			C.setFirstName(obj.getFirstName());
			C.setLastName(obj.getLastName());
			C.setCellPhone(obj.getCellPhone());
			C.setWorkPhone(obj.getWorkPhone());
			C.setEmail(obj.getCellPhone());

			storedContacts.writeContacts(this.contactList);

			return C;
		}

	}
	public Contact addContact(Contact obj){

		SortedArrayList<Integer> idVals = new SortedArrayList<Integer>();
		for (int i = 0; i < contactList.size(); i++)
			idVals.add( (int) contactList.get(i).getId());

		for(int i=0; i < contactList.size(); i++){
			if(idVals.get(i) == counter)
				counter++;
		}

		long id = counter;

		obj.setId(id);
		System.out.println("ID " + id);

		contactList.add(obj);
		storedContacts.writeContacts(contactList);
		return obj;
	}

	private static ContactList singleton = new ContactList();

	public static ContactList getInstance(){
		return singleton;
	}

	public void obtainList(){
		contactList = storedContacts.readContacts();
	}


}