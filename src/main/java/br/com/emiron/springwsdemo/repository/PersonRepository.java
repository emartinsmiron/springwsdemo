package br.com.emiron.springwsdemo.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import emiron.demo.find_person.Person;

@Component
public class PersonRepository{
     private static final Map<String, Person> people = new HashMap<>();

     @PostConstruct
     public void initData(){
          Person person1 = new Person();
          person1.setName("Eduardo");
          person1.setSurname("Miron");
          person1.setId("33989082841");

          person1.setBirthdate(this.getXmlBirthDate("05/03/1989"));
          people.put(person1.getId(), person1);
     }

     private XMLGregorianCalendar getXmlBirthDate(String birthDate){
          GregorianCalendar cal = new GregorianCalendar();
          try{
               SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

               cal.setTime(sdf.parse(birthDate));
               return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR),
                         cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), 0);

          } catch (ParseException e){
               System.out.println("Wrong Date Format");
          } catch (DatatypeConfigurationException e){
               System.out.println("Wrong Configuration");
          }
          return null;
     }

     public Person findPerson(String id){
          Assert.notNull(id, "The person id must not be null");
          return people.get(id);
     }
}