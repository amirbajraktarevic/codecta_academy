package com.codecta.academy.repository;

import com.codecta.academy.repository.entity.ThemePark;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class ThemeParkRepository extends Repository<ThemePark, Integer> {
    public ThemeParkRepository() {
        super(ThemePark.class);
    }

    public List<ThemePark> findByCharacterName(String name)
    {
        Query query = entityManager.createQuery("SELECT distinct t FROM ThemePark t JOIN t.disneyCharacters c where c.name like :name");
        query.setParameter("name", name + '%');
        List resultList = query.getResultList();
        return (List<ThemePark>) resultList;
    }

    public List<ThemePark> findParkByCharacterNameOrGreeting(String name, String greeting)
    {
        Query query = entityManager.createQuery("SELECT distinct t FROM ThemePark t JOIN t.disneyCharacters c where c.name like :name OR c.greeting like :greeting");
        query.setParameter("name", name + '%');
        query.setParameter("greeting", greeting + '%');
        List resultList = query.getResultList();
        return (List<ThemePark>) resultList;
    }
}
