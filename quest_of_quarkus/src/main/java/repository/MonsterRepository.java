package repository;


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
public class MonsterRepository extends Repository<MonsterEntity, Integer> {

    public MonsterRepository(){
        super(MonsterEntity.class);
    }

    public List<MonsterEntity> findAllMonstersByDungeonId(Integer dungeonId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MonsterEntity> cq = cb.createQuery(MonsterEntity.class);
        Root<MonsterEntity> root = cq.from(MonsterEntity.class);
        root.fetch("dungeon", JoinType.INNER);
        CriteriaQuery<MonsterEntity> all = cq.select(root);
        all.where(cb.equal(root.get("dungeon"), dungeonId));
        TypedQuery<MonsterEntity> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public List<MonsterEntity> findAllByIdList(List<Integer> ids){
        Query query = entityManager.createQuery("SELECT d from MonsterEntity d where d.id IN (:ids)");
        query.setParameter("ids",ids);
        return query.getResultList();
    }
}
