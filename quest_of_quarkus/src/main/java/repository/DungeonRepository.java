package repository;

import repository.entity.DungeonEntity;
import repository.entity.MonsterEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DungeonRepository extends Repository<DungeonEntity, Integer> {

    public DungeonRepository(){
        super(DungeonEntity.class);
    }

    public List<DungeonEntity> findAllDungeonsByDungeonId(Integer mapId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DungeonEntity> cq = cb.createQuery(DungeonEntity.class);
        Root<DungeonEntity> root = cq.from(DungeonEntity.class);
        root.fetch("map", JoinType.INNER);
        CriteriaQuery<DungeonEntity> all = cq.select(root);
        all.where(cb.equal(root.get("map"), mapId));
        TypedQuery<DungeonEntity> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public List<DungeonEntity> findAllByIdList(List<Integer> ids){
        Query query = entityManager.createQuery("SELECT d from DungeonEntity d where d.id IN (:ids)");
        query.setParameter("ids",ids);
        return query.getResultList();
    }
}
