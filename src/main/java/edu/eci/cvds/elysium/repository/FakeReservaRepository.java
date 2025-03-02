package edu.eci.cvds.elysium.repository;

import edu.eci.cvds.elysium.model.ReservaModel;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public class FakeReservaRepository implements ReservaRepository {

    private Map<String, ReservaModel> store = new ConcurrentHashMap<>();

    @Override
    public <S extends ReservaModel> S save(S entity) {
        if (entity.getIdReserva() == null || entity.getIdReserva().isEmpty()) {
            entity.setIdReserva(UUID.randomUUID().toString());
        }
        store.put(entity.getIdReserva(), entity);
        return entity;
    }

    @Override
    public <S extends ReservaModel> List<S> saveAll(Iterable<S> entities) {
        List<S> saved = new ArrayList<>();
        for (S e : entities) {
            saved.add(save(e));
        }
        return saved;
    }

    @Override
    public Optional<ReservaModel> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return store.containsKey(id);
    }

    @Override
    public List<ReservaModel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List findAllById(Iterable<String> ids) {
        List<ReservaModel> found = new ArrayList<>();
        for (String id : ids) {
            if (store.containsKey(id)) {
                found.add(store.get(id));
            }
        }
        return found;
    }

    @Override
    public long count() {
        return store.size();
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
    }

    @Override
    public void delete(ReservaModel entity) {
        if (entity != null && entity.getIdReserva() != null) {
            store.remove(entity.getIdReserva());
        }
    }

    @Override
    public void deleteAll(Iterable<? extends ReservaModel> entities) {
        for (ReservaModel e : entities) {
            delete(e);
        }
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

    @Override
    public List<ReservaModel> findByIdSalonAndFechaReserva(String idSalon, LocalDate fechaReserva) {
        List<ReservaModel> result = new ArrayList<>();
        for (ReservaModel r : store.values()) {
            // Se asume que el campo idSalon es el identificador del laboratorio/salón
            if (r.getIdSalon().equals(idSalon) && r.getFechaReserva().equals(fechaReserva)) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public <S extends ReservaModel> S insert(S entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public <S extends ReservaModel> List<S> insert(Iterable<S> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public <S extends ReservaModel> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends ReservaModel> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public List<ReservaModel> findAll(Sort sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Page<ReservaModel> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends ReservaModel> long count(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public <S extends ReservaModel> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }

    @Override
    public <S extends ReservaModel> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends ReservaModel, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    @Override
    public <S extends ReservaModel> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }
}
