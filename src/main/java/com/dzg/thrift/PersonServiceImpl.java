package com.dzg.thrift;


import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface{
    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("CLient Param:"+username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(22);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("CLient Param:"+person);
        System.out.println("CLient Param:"+person.getUsername());
    }
}
