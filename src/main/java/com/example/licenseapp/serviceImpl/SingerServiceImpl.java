package com.example.licenseapp.serviceImpl;

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

import java.util.List;
import java.util.Objects;

/**
 * Сервис для работы с исполнителями.
 *
 * SessionFactory
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class SingerServiceImpl implements SingerService {

    private final SessionFactory sessionFactory;

    private final SingerMapper singerMapper;

    @Override
    /** Здесь немного HQL */
    public List<SingerDTO> getAll() {
        log.debug("Получение всех композиций");

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        List<Singer> allSingers = session.createQuery("from Singer", Singer.class).list();
        session.getTransaction().commit();
        return singerMapper.ToDtoList(allSingers);
    }

    @Override
    public SingerDTO getById(long singerId) {
        log.debug("Получение композиции с id = {}", singerId);

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Singer singerEntity = session.get(Singer.class, singerId);
        session.getTransaction().commit();

        if (Objects.isNull(singerEntity)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Исполнитель не найден");
        }

        return singerMapper.toDTO(singerEntity);
    }

    @Override
    public void create(SingerDTO newSinger) {
        log.debug("Добавление нового исполнителя {}", newSinger);

        Session session = sessionFactory.openSession();

        Singer newSingerEntity = singerMapper.toEntity(newSinger);

        session.beginTransaction();
        session.save(newSingerEntity);
        session.getTransaction().commit();

        log.debug("Новый исполнитель {} успешно добавлен", newSinger);
    }

    @Override
    public void update(SingerDTO updatedSinger) {
        log.debug("Обновление данных исполнителя с id = {}", updatedSinger.id());

        Session session = sessionFactory.openSession();

        Singer updatedSingerEntity = singerMapper.toEntity(updatedSinger);

        session.beginTransaction();
        session.update(updatedSingerEntity);
        session.getTransaction().commit();

        log.debug("Данные исполнителя с id = {} успешно обновлены", updatedSinger.id());
    }

    @Override
    public void delete(long singerId) {
        log.debug("Удаление исполнителя с id = {}", singerId);

        Session currentSession = sessionFactory.openSession();

        currentSession.beginTransaction();
        Singer singerToDelete = currentSession.get(Singer.class, singerId);

        currentSession.delete(singerToDelete);
        currentSession.getTransaction().commit();

        log.debug("Исполнитель с id = {} успешно удалён", singerId);
    }
}
