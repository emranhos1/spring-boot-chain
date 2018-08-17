package xyz.mizan.chain.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.mizan.chain.entity.Person;
import xyz.mizan.chain.util.HibernateSessionUtil;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	@Autowired
	HibernateSessionUtil hibernateSessionUtil;
	
	@Autowired
	EntityManagerFactory factory;
	
	@Override
	public List<Person> selectAllPerson() {
		List<Person> personList;
        Session session = hibernateSessionUtil.getSessionFactory(factory).openSession();
        session.beginTransaction();
        personList = session.createQuery("from Person").list();
        session.close();
		return personList;
	}
	
	@Override
	public int insetPerson(Person person) {
		int responce = 0;
        Session session = hibernateSessionUtil.getSessionFactory(factory).openSession();
        session.beginTransaction();
        List empCheck = session.createQuery("from Person").list();
        if(empCheck.isEmpty()) {
        	responce = (Integer) session.save(person);
    		session.getTransaction().commit();
        }else {
        	List phoneCheck = session.createQuery("from Person where phoneNo = '"+person.getPhoneNo()+"'").list();
        	if(phoneCheck.isEmpty()) {
        		responce = (Integer) session.save(person);
        		session.getTransaction().commit();
        	}else {
        		responce = -1;
        	}
        }
        session.close();
        return responce;
    }
	
	@Override
	public Person updatePerson(Person person){
        Session session = hibernateSessionUtil.getSessionFactory(factory).openSession();
        session.beginTransaction();
        List<Person> queryPerson = searchPersonById(person.getId());
        if(!queryPerson.isEmpty()) {
        	if(null == person.getName()) {
        		person.setName(queryPerson.get(0).getName());
        	}
        	if(null == person.getAddress()) {
        		person.setAddress(queryPerson.get(0).getAddress());
        	}
        	if(null == person.getMaritalStatus()) {
        		person.setMaritalStatus(queryPerson.get(0).getMaritalStatus());
        	}
        	if(null == person.getPhoneNo()) {
        		person.setPhoneNo(queryPerson.get(0).getPhoneNo());
        	}
        	if(null == person.getGender()) {
        		person.setGender(queryPerson.get(0).getGender());
        	}
        	session.update(person);
            session.getTransaction().commit();
            session.close();
            return person;
        }
        session.close();
        return null;
    }
	
	@Override
	public boolean deletePerson(int id){
        Session session = hibernateSessionUtil.getSessionFactory(factory).openSession();
        session.beginTransaction();
        List<Person> queryPerson = searchPersonById(id);
        if(!queryPerson.isEmpty()) {
        	session.createQuery("Delete Person where id = "+id).executeUpdate();
            session.getTransaction().commit();
            session.close();
            return true;
        }
        return false;	
    }
	
	
	@Override
	public List<Person> searchPersonById(int id) {
		Session session = hibernateSessionUtil.getSessionFactory(factory).openSession();
        session.beginTransaction();
        List<Person> person = session.createQuery("from Person where id="+id).list();
        session.close();
        return person;
	}
	
	@Override
	public List<Person> searchPerson(Person person){
		List<Person> personList;
		
		String personNo = "";
		String personName = person.getName();
		String personAddress = person.getAddress();
		String personMaritalStatus = person.getMaritalStatus();
		String personGender = person.getGender();
		String personPhoneNo = person.getPhoneNo();
		if(!(person.getId() == 0)){
			personNo = String.valueOf(person.getId());
		}
		if(null == personName) {
			personName = "";
		}
		if(null == personAddress) {
			personAddress = "";
		}
		if(null == personMaritalStatus) {
			personMaritalStatus = "";
		}
		if(null == personGender){
			personGender = "";
		}
		if(null == personPhoneNo) {
			personPhoneNo = "";
		}
		Session session = hibernateSessionUtil.getSessionFactory(factory).openSession();
		session.beginTransaction();
		personList = session.createQuery(
				"from Person where id like '%"+personNo+"%'"
				+ " and name like '%"+personName+"%'"
				+ " and address like '%"+personAddress+"%'"
				+ " and maritalStatus like '%"+personMaritalStatus+"%'"
				+ " and gender like '%"+personGender+"%'"
				+ " and phoneNo like '%"+personPhoneNo+"%'"
				).list();
		session.close();
		return personList;
	}

}
