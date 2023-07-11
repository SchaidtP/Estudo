package br.com.erudio.services;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.ErudioMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    @Autowired
    PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<PersonVO> findAll() {
        logger.info("Finding all people!");
        return ErudioMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");

        var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return ErudioMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Finding one person!");
        var entity = ErudioMapper.parseObject(personVO, Person.class);
        var vo = ErudioMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO personVO) {
        logger.info("Updating one person!");
        var entity = personRepository.findById(personVO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getLastName());
        entity.setGender(personVO.getGender());

        var vo = ErudioMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }
}
