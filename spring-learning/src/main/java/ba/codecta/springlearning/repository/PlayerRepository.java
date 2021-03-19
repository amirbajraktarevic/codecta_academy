package ba.codecta.springlearning.repository;

import org.springframework.web.context.annotation.ApplicationScope;
import ba.codecta.springlearning.repository.entity.PlayerEntity;

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
public class PlayerRepository extends Repository<PlayerEntity, Integer> {

    public PlayerRepository(){
        super(PlayerEntity.class);
    }

    public List<PlayerEntity> findAllPlayersByPlayerId(Integer mapId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PlayerEntity> cq = cb.createQuery(PlayerEntity.class);
        Root<PlayerEntity> root = cq.from(PlayerEntity.class);
        root.fetch("dungeon", JoinType.INNER);
        CriteriaQuery<PlayerEntity> all = cq.select(root);
        all.where(cb.equal(root.get("dungeon"), mapId));
        TypedQuery<PlayerEntity> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public List<PlayerEntity> findAllByIdList(List<Integer> ids){
        Query query = entityManager.createQuery("SELECT d from PlayerEntity d where d.id IN (:ids)");
        query.setParameter("ids",ids);
        return query.getResultList();
    }
}
