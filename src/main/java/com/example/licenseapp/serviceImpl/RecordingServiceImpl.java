package com.example.licenseapp.serviceImpl;

import com.example.licenseapp.dto.RecordingDTO;
import com.example.licenseapp.mapper.RecordingMapper;
import com.example.licenseapp.model.Copyright;
import com.example.licenseapp.model.Recording;
import com.example.licenseapp.service.RecordingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Сервис для работы с композициями.
 *
 * Реализация через Criteria API
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class RecordingServiceImpl implements RecordingService {

    private final RecordingMapper recordingMapper;

    @PersistenceContext
    private EntityManager entityManager;

    private static final String ID = "id";

    private static final String TITLE = "title";

    private static final String VERSION = "version";

    private static final String RELEASE_TIME = "release_time";

    @Override
    @Transactional
    public List<RecordingDTO> getAll() {

        log.debug("Получение всех композиций");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Recording> criteriaQuery = criteriaBuilder.createQuery(Recording.class);
        Root<Recording> root = criteriaQuery.from(Recording.class);
        criteriaQuery.select(root);

        TypedQuery<Recording> query = entityManager.createQuery(criteriaQuery);
        List<RecordingDTO> recordingDTOS = recordingMapper.ToDtoList(query.getResultList());

        log.debug("Все композиции успешно выгружены из БД");

        return recordingDTOS;
    }

    @Override
    @Transactional
    public RecordingDTO getById(long recordId) {

        log.debug("Получение композиций с id = {}", recordId);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Recording> criteriaQuery = criteriaBuilder.createQuery(Recording.class);
        Root<Recording> root = criteriaQuery.from(Recording.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get(ID), recordId));

        TypedQuery<Recording> query = entityManager.createQuery(criteriaQuery);
        Recording recording = Optional.ofNullable(query.getSingleResult())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Композиция не найдена"));

        return recordingMapper.toDTO(recording);
    }

    @Override
    @Transactional
    public RecordingDTO update(RecordingDTO updatedRecord) {

        log.debug("Обновление композиции с id = {}", updatedRecord.id());

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Recording> criteriaQuery = criteriaBuilder.createQuery(Recording.class);
        Root<Recording> selectRoot = criteriaQuery.from(Recording.class);

        criteriaQuery
                .select(selectRoot)
                .where(criteriaBuilder.equal(selectRoot.get(ID), updatedRecord.id()));

        TypedQuery<Recording> query = entityManager.createQuery(criteriaQuery);

        if (Objects.isNull(query.getSingleResult())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Такой композиции не существует");
        }

        CriteriaUpdate<Recording> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Recording.class);
        Root<Recording> updateRoot = criteriaUpdate.from(Recording.class);

        if (updatedRecord.title() != null) {
            criteriaUpdate.set(updateRoot.get(TITLE), updatedRecord.title());
        }
        if (updatedRecord.version() != null) {
            criteriaUpdate.set(updateRoot.get(VERSION), updatedRecord.version());
        }
        if (updatedRecord.releaseTime() != null) {
            criteriaUpdate.set(updateRoot.get(RELEASE_TIME), updatedRecord.releaseTime());
        }
        criteriaUpdate.where(criteriaBuilder.equal(updateRoot.get(ID), updatedRecord.id()));

        entityManager.createQuery(criteriaUpdate).executeUpdate();

        log.debug("Композиция с id = {} успешно обновлена", updatedRecord.id());
        return updatedRecord;
    }

    @Override
    @Transactional
    public void create(RecordingDTO newRecording) {

        log.debug("Добавление новой композиции {}", newRecording);

        Recording recording = recordingMapper.toEntity(newRecording);

        entityManager.persist(recording);

        log.debug("Новая композиция {} успешно добавлена", newRecording);
    }

    @Override
    @Transactional
    public void delete(long recordId) {

        log.debug("Удаление композиции с id = {}", recordId);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Recording> criteriaDelete = criteriaBuilder.createCriteriaDelete(Recording.class);
        Root<Recording> root = criteriaDelete.from(Recording.class);

        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), recordId));

        entityManager.createQuery(criteriaDelete).executeUpdate();

        log.debug("Композиция с id = {} успешно удалена", recordId);
    }

    @Override
    public void removeSinger(long recordId) {

        log.debug("Удаление исполнителя у композиции с id = {}", recordId);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Recording> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Recording.class);
        Root<Recording> updateRoot = criteriaUpdate.from(Recording.class);

        criteriaUpdate.set(updateRoot.get("singer"), (Object) null);

        criteriaUpdate.where(criteriaBuilder.equal(updateRoot.get(ID), recordId));

        entityManager.createQuery(criteriaUpdate).executeUpdate();

        log.debug("Исполнитель у композиции с id = {} успешно удалён", recordId);
    }

    @Override
    public List<Long> getPriceForRecording(Long recordId) {

        log.debug("Получение стоимости композиции с id = {}", recordId);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Recording> criteriaQuery = criteriaBuilder.createQuery(Recording.class);
        Root<Recording> root = criteriaQuery.from(Recording.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get(ID), recordId));

        TypedQuery<Recording> query = entityManager.createQuery(criteriaQuery);
        Recording recording = Optional.ofNullable(query.getSingleResult())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Композиция не найдена"));

        return recording
                .getCopyrights()
                .stream()
                .map(Copyright::getCost)
                .toList();
    }
}
