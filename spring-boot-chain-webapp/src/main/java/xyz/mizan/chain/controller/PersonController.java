package xyz.mizan.chain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.mizan.chain.entity.Person;
import xyz.mizan.chain.service.PersonServiceImpl;

@RestController
@RequestMapping(value="/person")
public class PersonController {

	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Map<String, Object> selectAllPerson(){
		Map<String, Object> response = new HashMap<String, Object>();
		 List<Person> personList = personServiceImpl.selectAllPerson();
		 if(personList.isEmpty()){
			 response.put("success", false);
			 response.put("data", personList);
			 response.put("massage", "There is no Person");
		 }else{
			 response.put("success", true);
			 response.put("data", personList);
			 response.put("massage", "All Person are selected");
		 }
		 return response;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Map<String, Object> insetPerson(@RequestBody Person person){
		Map<String, Object> response = new HashMap<String, Object>();
		if(null == person.getName() || null == person.getPhoneNo()) {
			response.put("success", false);
			response.put("massage", "Field is not properly fill");
		}else {
			int pat = personServiceImpl.insetPerson(person);
			if(pat == -1) {
				response.put("success", false);
				response.put("massage", "Phone number is alrady exist");
			}else {
				if(pat == 0){
					response.put("success", false);
					response.put("data", person);
					response.put("massage", "There are no Person");
				}else{
					response.put("success", true);
					response.put("data", person);
					response.put("massage", "Person is inserted");
				}
			}
		}
		 return response;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public Map<String, Object> updatePerson(@RequestBody Person person){
		Map<String, Object> response = new HashMap<String, Object>();
		 Person pat = personServiceImpl.updatePerson(person);
		 if(pat == null){
			 response.put("success", false);
			 response.put("data", pat);
			 response.put("massage", "There are no Person");
		 }else{
			 response.put("success", true);
			 response.put("data", pat);
			 response.put("massage", "Person Updated");
		 }
		 return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Map<String, Object> deletePerson(@PathVariable int id){
		Map<String, Object> response = new HashMap<String, Object>();
		if(personServiceImpl.deletePerson(id) == true) {
			response.put("success", true);
			response.put("massage", "Patient Deleted");
		}else {
			response.put("Success", false);
			response.put("massage", "Patient not found");
		}
		 return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Map<String, Object> searchPersonById(@PathVariable int id){
		Map<String, Object> response = new HashMap<String, Object>();
		List<Person> person = personServiceImpl.searchPersonById(id);
		if(person.isEmpty()) {
			response.put("success", false);
			response.put("data", person);
			response.put("massage", "Person not present");
		}else {
			response.put("success", true);
			response.put("data", person);
			response.put("massage", "Person is found");
		}
		 return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "search", method = RequestMethod.GET)
    public Map<String, Object> searchPerson(Person person) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Person> query = personServiceImpl.searchPerson(person);
		if(query.isEmpty()) {
			response.put("success", false);
			response.put("data", query);
			response.put("massage", "Person not found");
		}else {
			response.put("success", true);
			response.put("data", query);
			response.put("massage", "Person found");
		}
        return response;
    }
}
