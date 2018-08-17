package xyz.mizan.chain.repository;

import java.util.List;

import xyz.mizan.chain.entity.Person;

public interface PersonRepository {

	public int insetPerson(Person person);
	public Person updatePerson(Person person);
	public boolean deletePerson(int id);
	
	public List<Person> selectAllPerson();
	public List<Person> searchPersonById(int id);
	public List<Person> searchPerson(Person person);
}
