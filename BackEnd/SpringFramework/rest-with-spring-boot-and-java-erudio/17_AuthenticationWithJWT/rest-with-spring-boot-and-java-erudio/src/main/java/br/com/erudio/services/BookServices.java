package br.com.erudio.services;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.ErudioMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    public List<BookVO> findAll() {
        logger.info("Finding all book!");
        var books =  ErudioMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
        books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getId())).withSelfRel()));
        return books;
    }

    public BookVO findById(Long id) {
        logger.info("Finding one book!");

        var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = ErudioMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO bookVO) {
        if(bookVO == null) throw new RequiredObjectIsNullException();
        logger.info("Finding one book!");
        var entity = ErudioMapper.parseObject(bookVO, Book.class);
        var vo = ErudioMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO bookVO) {
        if(bookVO == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one book!");
        var entity = bookRepository.findById(bookVO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());

        var vo = ErudioMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getId())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one book!");

        var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        bookRepository.delete(entity);
    }
}
