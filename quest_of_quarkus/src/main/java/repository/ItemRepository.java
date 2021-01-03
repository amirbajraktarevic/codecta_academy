package repository;

import repository.entity.ItemEntity;

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
public class ItemRepository extends Repository<ItemEntity, Integer> {

    public ItemRepository(){
        super(ItemEntity.class);
    }

    public List<ItemEntity> findAllWeaponsByWeaponId(Integer mapId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemEntity> cq = cb.createQuery(ItemEntity.class);
        Root<ItemEntity> root = cq.from(ItemEntity.class);
        root.fetch("dungeon", JoinType.INNER);
        CriteriaQuery<ItemEntity> all = cq.select(root);
        all.where(cb.equal(root.get("dungeon"), mapId));
        TypedQuery<ItemEntity> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public List<ItemEntity> findAllByIdList(List<Integer> ids){
        Query query = entityManager.createQuery("SELECT d from ItemEntity d where d.id IN (:ids)");
        query.setParameter("ids",ids);
        return query.getResultList();
    }
}
