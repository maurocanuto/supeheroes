package com.payworks.db;

import com.payworks.exceptions.SuperheroException;
import com.payworks.models.Hero;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mcanuto on 5/19/16.
 *
 * JPA calls
 */
public class DbManager {

    public List<Hero> getSuperheroes() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        List<Hero> results =  em.createNamedQuery("findAllSuperheroes", Hero.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return results;
    }

    public void addHero(Hero hero) throws SuperheroException {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();

        Hero emp = em.find(Hero.class, hero.getName());
        if (emp != null) {
            em.close();
            throw new SuperheroException("Superhero " + hero.getName() + " is already present");
        }

        em.persist(hero);
        em.getTransaction().commit();
        em.close();

    }


    public void removeHero(String name) throws SuperheroException {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        Hero hero = em.find(Hero.class, name);
        if (hero == null) {
            em.close();
            throw new SuperheroException("Superhero " + name + " is not present: nothing to remove");
        }else{
            em.remove(em.contains(hero) ? hero : em.merge(hero));
            em.getTransaction().commit();
            em.close();
        }


    }


    public Hero getHero(String name) throws SuperheroException {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        Hero h = em.find(Hero.class, name);
        em.getTransaction().commit();

        if (h == null) {
            em.close();
            throw new SuperheroException("Superhero " + name + " is not present");
        }else{
            em.close();
            return h;
        }
    }


    public void deleteAllTables(){

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        Query query1 = em.createNativeQuery("DELETE FROM Hero_Skills");
        Query query2 = em.createNativeQuery("DELETE FROM Hero_Allies");
        Query query3 = em.createNativeQuery("DELETE FROM Hero");
        query1.executeUpdate();
        query2.executeUpdate();
        query3.executeUpdate();
        em.getTransaction().commit();
        em.close();

    }
}
