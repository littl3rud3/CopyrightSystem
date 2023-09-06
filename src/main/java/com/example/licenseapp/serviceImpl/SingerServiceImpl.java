package com.example.licenseapp.serviceImpl;

import java.util.List;
import java.util.Objects;

import com.example.licenseapp.dto.SingerDTO;
import com.example.licenseapp.mapper.SingerMapper;
import com.example.licenseapp.model.Singer;
import com.example.licenseapp.service.SingerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Log4j2
public class SingerServiceImpl implements SingerService {
    
    private final SessionFactory sessionFactory;
    
    private final SingerMapper singerMapper;
    
    @Override
    /** There's a bit of HQL here */
    public List<SingerDTO> getAll() {
        log.debug("Getting all compositions");
        
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        List<Singer> allSingers = session.createQuery("from Singer", Singer.class).list();
        session.getTransaction().commit();
        return singerMapper.ToDtoList(allSingers);
    }
    
    @Override
    public SingerDTO getById(long singerId) {
        log.debug("Getting a composition with id = {}", singerId);
        
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        Singer singerEntity = session.get(Singer.class, singerId);
        session.getTransaction().commit();
        
        if (Objects.isNull(singerEntity)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Singer not found");
        }
        
        return singerMapper.toDTO(singerEntity);
    }
    
    @Override
    public void create(SingerDTO newSinger) {
        log.debug("Adding a new artist {}", newSinger);
        
        Session session = sessionFactory.openSession();
        
        Singer newSingerEntity = singerMapper.toEntity(newSinger);
        
        session.beginTransaction();
        session.save(newSingerEntity);
        session.getTransaction().commit();
        
        log.debug("New artist {} successfully added", newSinger);
    }
    
    @Override
    public void update(SingerDTO updatedSinger) {
        log.debug("Updating artist data with id = {}", updatedSinger.id());
        
        Session session = sessionFactory.openSession();
        
        Singer updatedSingerEntity = singerMapper.toEntity(updatedSinger);
        
        session.beginTransaction();
        session.update(updatedSingerEntity);
        session.getTransaction().commit();
        
        log.debug("Artist data with id = {} successfully updated", updatedSinger.id());
    }
    
    @Override
    public void delete(long singerId) {
        log.debug("Deleting an artist with id = {}", singerId);
        
        Session currentSession = sessionFactory.openSession();
        
        currentSession.beginTransaction();
        Singer singerToDelete = currentSession.get(Singer.class, singerId);
        
        currentSession.delete(singerToDelete);
        currentSession.getTransaction().commit();
        
        log.debug("The performer with id = {} has been successfully deleted", singerId);
    }
}
