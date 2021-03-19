package ba.codecta.springlearning.repository;

import org.springframework.web.context.annotation.ApplicationScope;
import ba.codecta.springlearning.repository.entity.MapEntity;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Repository
@ApplicationScope
@Transactional(Transactional.TxType.MANDATORY)
public class MapRepository extends Repository<MapEntity, Integer> {

    public MapRepository(){
        super(MapEntity.class);
    }

    public List<MapEntity> findAllMapsByMapId(Integer mapId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MapEntity> cq = cb.createQuery(MapEntity.class);
        Root<MapEntity> root = cq.from(MapEntity.class);
        root.fetch("dungeon", JoinType.INNER);
        CriteriaQuery<MapEntity> all = cq.select(root);
        all.where(cb.equal(root.get("dungeon"), mapId));
        TypedQuery<MapEntity> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public List<MapEntity> findAllByIdList(List<Integer> ids){
        Query query = entityManager.createQuery("SELECT d from MapEntity d where d.id IN (:ids)");
        query.setParameter("ids",ids);
        return query.getResultList();
    }
}
