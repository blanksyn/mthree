package com.mthree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {
	
	@Autowired
	private DvdService service;

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String retrieveDVD(Model model) {
//		System.out.println("Inside menu()");
		List<DVD> allDvd = service.findAll();
		model.addAttribute("dvd",allDvd);
//		System.out.println(allDvd);
		return "menu";
	}
	
	@RequestMapping(value = "/add_dvd.htm", method = RequestMethod.POST)
	public String addDvd(@ModelAttribute("dvd")DVD dvd) {
//		System.out.println("Inside add_dvd()");
		service.saveDvd(dvd);
		return "redirect:/menu.htm";
	}
	
	@RequestMapping(value = "/add_dvd", method = RequestMethod.GET)
	public String showAddDVDForm(Map<String, DVD> map,Map<String, Integer> id) {
//		System.out.println("Inside showAddDVDForm()");
		id.put("nextId", service.findAll().size()+1);
		map.put("dvd", new DVD());
		System.out.println("Entry added");
		return "add_dvd"; //prefix and suffix already given in mthree-servlet.xml
	}
	
	@RequestMapping(value = "/edit_dvd.htm", method = RequestMethod.POST)
	public String editDvd(@ModelAttribute("dvd")DVD dvd) {
//		System.out.println("Inside add_dvd()");
		service.editDvd(dvd);
		System.out.println("Entry edited");
		return "redirect:/menu.htm";
	}
	
	@RequestMapping(value = "/edit_dvd.htm", method = RequestMethod.GET)
	public String showEditDVDForm(@RequestParam(value = "id") int id, Map<String, DVD> map) {
//		System.out.println("Inside showAddDVDForm()");
		DVD dvd = service.dispEditDvd(id);
		map.put("dvd", dvd);

		return "edit_dvd"; //prefix and suffix already given in mthree-servlet.xml
	}
	
	
	@RequestMapping(value = "/delete_dvd.htm", method = RequestMethod.GET)
	public String showDeleteDVDForm(@RequestParam(value = "id") int id, Map<String, DVD> map) {
//		System.out.println("Inside showAddDVDForm()");
		service.deleteDvd(id);
		System.out.println("Entry deleted successfully");
		return "redirect:/menu.htm"; //prefix and suffix already given in mthree-servlet.xml
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.POST)//this is a duplicate
	public String search(String title, Model model,Map<String, String> msg) {
//		System.out.println("Inside search()");
		System.out.println("Searched word" + title);
		List<DVD> allDvd = service.findByTitle(title);
		if(allDvd.size()<1) {
			msg.put("msg", "Invalid input/ No records found");
			allDvd = service.findAll();
		}else {
			msg.put("msg", "");
		}
		System.out.println(allDvd);
		model.addAttribute("dvd",allDvd);
		return "menu"; //prefix and suffix already given in mthree-servlet.xml
	}
}
