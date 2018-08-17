package xyz.mizan.chain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.mizan.chain.entity.Person;
import xyz.mizan.chain.repository.PersonRepositoryImpl;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepositoryImpl personRepositoryImpl;
	
	@Override
	public int insetPerson(Person person){
		return personRepositoryImpl.insetPerson(person);
	}
	
	@Override
	public Person updatePerson(Person person){
		return personRepositoryImpl.updatePerson(person);
	}
	
	@Override
	public boolean deletePerson(int id){
		return personRepositoryImpl.deletePerson(id);
		
	}
	
	@Override
	public List<Person> selectAllPerson(){
		return personRepositoryImpl.selectAllPerson();
	}
	
	@Override
	public List<Person> searchPersonById(int id){
		return personRepositoryImpl.searchPersonById(id);
		
	}
	
	@Override
	public List<Person> searchPerson(Person person){
		return personRepositoryImpl.searchPerson(person);
	}

}
