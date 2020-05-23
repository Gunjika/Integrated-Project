package com.capgemini.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="User6")
@DynamicUpdate(true)
@DynamicInsert(true)
public class User {

@Id
@Column(name="userId")
@NotNull(message="must not be null")
@Positive
private Long userId;

@Column(name="userName")
@NotEmpty(message="Username not be Empty")
@Pattern(regexp="([A-Za-z]+)|([A-Za-z]+[A-Za-z]+)", message="allow only alphabets" )
private String userName;

@Column(name="password")
@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,14}$")
private String password;

/**@OneToOne
@JoinColumn(name="userTest")
private Test1 userTest;*/

@Column(name="isAdmin")
private Boolean isAdmin;

public User() {
	super();
	// TODO Auto-generated constructor stub
}

public User(@NotNull(message = "must not be null") @Positive Long userId,
		@NotEmpty(message = "Username not be Empty") @Pattern(regexp = "([A-Za-z]+)|([A-Za-z]+[A-Za-z]+)", message = "allow only alphabets") String userName,
		@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,14}$") String password,
		Boolean isAdmin) {
	super();
	this.userId = userId;
	this.userName = userName;
	this.password = password;
	this.isAdmin = isAdmin;
}

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Boolean getIsAdmin() {
	return isAdmin;
}

public void setIsAdmin(Boolean isAdmin) {
	this.isAdmin = isAdmin;
}




}