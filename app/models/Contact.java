package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Contact implements Serializable, Comparable<Contact> {

	private long Id;
	private String firstName;
	private String lastName;
	private String cellPhone;
	private String workPhone;
	private String email;
	private ArrayList<ContactAddress> addresses = new ArrayList<ContactAddress>();

    public Contact(){
    }

    public Contact(long i, String fN, String lN, String cP, String wP, String e, ArrayList<ContactAddress> a){
    	this.Id = i;
        this.firstName = fN;
        this.lastName = lN;
        this.cellPhone = cP;
        this.workPhone = wP;
        this.email = e;
        this.addresses = a;
    }
    
    public void setId(long i){
    	this.Id = i;
    }

    public void setFirstName(String fN){
        this.firstName = fN;
    }

    public void setLastName(String lN){
        this.lastName = lN;
    }

    public void setCellPhone(String cP){
        this.cellPhone = cP;
    }

    public void setWorkPhone(String wP){
        this.workPhone = wP;
    }

    public void setEmail(String e){
        this.email = e;
    }

//    public void setAddress(ContactAddress a){
//        if(this.addresses.get(0) == null){
//        	this.addresses.add(a);
//        }
//        else {
//        	this.addresses.set(0, a);
//        }
//    }
    
    public long getId(){
    	return this.Id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getCellPhone(){
        return this.cellPhone;
    }

    public String getWorkPhone(){
        return this.workPhone;
    }

    public String getEmail(){
        return this.email;
    }
    
//    public ContactAddress getAddress(){
//    	if(this.addresses.get(0) == null) return null;
//    	else{
//    		return this.addresses.get(0);
//    	}
//    }

    @Override
    public String toString(){
        return this.getFirstName() + " " + this.getLastName();
    }

	@Override
	public int compareTo(Contact arg0) {

		String contactAFirstLetter = String.valueOf(this.getFirstName().charAt(0));
		String contactBFirstLetter = String.valueOf(arg0.getFirstName().charAt(0));
		
		if(contactAFirstLetter.compareTo(contactBFirstLetter) > 0){
			return 1;
		}
		
		if(contactAFirstLetter.compareTo(contactBFirstLetter) < 0){
			return -1;
		}
		return 0;
		
	}
}