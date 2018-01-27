package br.com.emiron.springwsdemo.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.com.emiron.springwsdemo.repository.PersonRepository;
import emiron.demo.find_person.GetPersonRequest;
import emiron.demo.find_person.GetPersonResponse;

@Endpoint
public class PersonEndpoint{
     private static final String NAMESPACE_URI = "http://emiron/demo/find-person";

     @Autowired
     private PersonRepository personRepository;

     @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
     @ResponsePayload
     public GetPersonResponse getCountry(@RequestPayload GetPersonRequest request){
          GetPersonResponse response = new GetPersonResponse();
          response.setPerson(this.personRepository.findPerson(request.getId()));

          return response;
     }
}