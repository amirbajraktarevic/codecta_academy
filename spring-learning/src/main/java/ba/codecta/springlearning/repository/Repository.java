package ba.codecta.springlearning.repository;


import org.springframework.beans.factory.annotation.Autowired;
import ba.codecta.springlearning.repository.entity.ModelObject;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Repository<T extends ModelObject, PK extends Serializable> {

    private Class<T> entityClass;

    protected Repository(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    @Autowired
    EntityManager entityManager;


    public T add(T modelObject){
        modelObject.setCreatedOn(LocalDateTime.now());
        entityManager.persist(modelObject);
        return modelObject;
    }

    public List<T> findAll(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(this.entityClass);
        CriteriaQuery<T> all = cq.select(root);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public T findById(PK id){
        T result = entityManager.find(this.entityClass, id);
        if(result !=null){
            return result;
        }
        return null;
    }

    public T save (T modelObject){
        T result = null;
        PK id = (PK)modelObject.getId();
        if(id!=null){
            result=findById(id);
        }
        if (id == null || result!= null){
            entityManager.persist(modelObject);
            return modelObject;
        }
        return null;
    }
}