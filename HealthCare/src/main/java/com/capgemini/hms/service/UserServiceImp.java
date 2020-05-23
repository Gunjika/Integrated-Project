package com.capgemini.hms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hms.dao.UserDao;
import com.capgemini.hms.entity.User;

@Service
@Transactional
public class UserServiceImp implements UserServiceInterface{
	@Autowired
	private UserDao userdao;

	
	@Override
	public boolean addUser(User user) {
		User Result= userdao.save(user);
		if(Result!=null) {
			return true;
		}
		else
			return false;
	}
	
   
	@Override
	public Optional<User> findUserById(Long userId) {

		return userdao.findById(userId);
		
	}
	/*public boolean loginId(Long userId,String password)throws UserException{
	{
		Object obj=userdao.findById(userId);
		
		if(obj ==null) {
			throw new RecordNotFoundException("Wrong Id");
		}
		else if(((User) obj).getPassword().equals(password) && ((User) obj).getIsAdmin()==false) {
			return true;
		}
		else if(((User) obj).getPassword().equals(password) && ((User) obj).getIsAdmin()==true) {
			return false;
		}
		else {
		 throw new Error("Password Incorrect");
		}
	}
	}*/
	


	

	
	@Override
	public List<User> showAllUsers() 
	{
		return userdao.findAll();
	}
		
	

	@Override
    public void deleteuser(long userId) {
		userdao.deleteById(userId);
	}


}
