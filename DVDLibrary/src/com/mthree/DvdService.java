package com.mthree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DvdService {
	@Autowired
//	@Qualifier(value ="JpaRepo")
	@Qualifier(value ="inMemory")
	private IDao dao;
	public List<DVD> findAll(){
//		System.out.println("Inside findAll() in userService");
		return dao.findAll();
	}
	
	public DVD saveDvd(DVD dvd) {
//		System.out.println("inside save() of Service");
		DVD d = dao.save(dvd);
		return d;
	}
	
	public DVD editDvd(DVD dvd) {
//		System.out.println("inside editDvd() of Service");
		DVD d = dao.edit(dvd);
		return d;
	}
	
	public DVD dispEditDvd(int id) {
//		System.out.println("inside dispEditDvd() of Service");
		DVD d = dao.dispEdit(id);
		return d;
	}

	public List<DVD> deleteDvd(int id) {
//		System.out.println("Inside deleteDvd() in userService");
		return dao.deleteById(id);
	}
	
	public List<DVD> findByTitle(String title) {
		
		try {
//			System.out.println("Inside findByTitle() in userService");
			List<Optional<DVD>> dvds = dao.findByTitle(title);
			List<DVD> result = new ArrayList<DVD>();
			for(Optional<DVD> u: dvds) {
				result.add(u.get());
			}
			return result;
		}catch(Exception e) {
			return null;
		}
	}

}
