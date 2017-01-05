package Entities;

import java.io.Serializable;

public class UserET implements Serializable {

	private String userName;
	private String passWord;
	private int permission;
	private int flag;
	private String firstName;
	private String lastName;
	private String email;
	private int id;
	
	public UserET(String userName,String passWord){
		
		this.userName=userName;
		this.passWord=passWord;
		
	}
public void SetFromSQL(int id,int permission,String firstName,String lastName,String email){
		this.id=id;
		this.permission=permission;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		
	}
	public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
