package com.capgemini.hms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hms.entity.User;
import com.capgemini.hms.exception.UserException;
import com.capgemini.hms.service.UserServiceInterface;

/***************************************************************************************************************************
 *  @author          DEEPAK
 *  Description      It is a Controller class that provides the Control for Registering a new user,viewing all the users,
                     login and deleting a user. 
 *  Version          1.0
 *  Created Date     
 **************************************************************************************************************************/


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {
	
	
	@Autowired
	private UserServiceInterface  userService;
	

/***************************************************************************************************************************
*  Method       :getallUser
*  Description  :To view all users and their details present in the database
*  @returns List:List of Users and their details
*  Created By   :@author DEEPAK
*  Created Date :   
***************************************************************************************************************************/

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/viewalluser")
	public ResponseEntity<List<User>> getallUser() {
		List<User> userlist = userService.showAllUsers();
		return new ResponseEntity<List<User>>(userlist , HttpStatus.OK);
		
	}
	
/***************************************************************************************************************************
*  Method       :addUser
*  Description  :To Register a new user
*  @returns String:It will return Done if user has been registered
*  @throws UserException :It is raised due to invalid userid,invalid userame ,password pattern mismatched and if checkbox
*                         left empty   
*  Created By   :@author DEEPAK
*  Created Date :   
***************************************************************************************************************************/

	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/Register")
	public ResponseEntity<String> addUser( @RequestBody User user, BindingResult br) throws UserException
	{
		String err="";
		if(br.hasErrors()) {
			List<FieldError> errors= br.getFieldErrors();
			for(FieldError error:errors)
				err +=error.getDefaultMessage() +"<br/>";
			throw new UserException(err);
		}
		try {
			
			
			userService.addUser(user);
			return new ResponseEntity<String>("Done", HttpStatus.OK);
			
		}
		catch(DataIntegrityViolationException ex) {
			throw new UserException("ID already exists");
		}
	}
	

/***************************************************************************************************************************
*  Method       :authUser
*  Description  :To logging in the user
*  @PathVariable userId: It will take userId given by a user during login
*  @PathVariable password: It will take password given by a user during login
*  @returns String:It will return Admin if user is admin,return Student if user is student,return Wrong Password if password 
  				   is not verified and return  Id doesn't Exist if userId is not found
*  Created By   :@author DEEPAK
*  Created Date :   
***************************************************************************************************************************/
	
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/login/{userId}")
	public ResponseEntity<String> authUser(@PathVariable long userId , @RequestBody String password) 
	{
		Optional<User>findById=userService.findUserById(userId);
		
		
		if(findById.isPresent()) {
			User user=findById.get();
			if(password.equals(user.getPassword())) {
				
				if(user.getIsAdmin())
					return new  ResponseEntity<String>("Admin", HttpStatus.OK);
				else
					return new  ResponseEntity<String>("Student", HttpStatus.OK);
			}
			else
				return new  ResponseEntity<String>("Wrong Password", HttpStatus.OK);
		}
		else
			return new  ResponseEntity<String>("Id doesn't Exist ", HttpStatus.OK);
		}
			

/***************************************************************************************************************************
*  Method       :deleteuser
*  Description  :To delete the user
*  @PathVariable userId: It will take userId given by a Admin 
*  @returns String:It will return User deleted successfully if user deleted otherwise return  User ID not exists id userId
*                  not present
*  Created By   :@author DEEPAK
*  Created Date :   
***************************************************************************************************************************/
	
	
	
	@CrossOrigin(origins="http://localhost:4200")	
	@DeleteMapping(value = "/deleteuser/{userId}")
	public ResponseEntity<String> deleteuser(@PathVariable long userId)
			throws UserException {
		try {
			
			userService.deleteuser(userId);
			return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);

		} catch (DataIntegrityViolationException ex) {
			throw new UserException("User ID not exists");
		}
	}
}