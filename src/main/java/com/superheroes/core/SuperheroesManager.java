package com.superheroes.core;

import com.superheroes.db.DbManager;
import com.superheroes.exceptions.SuperheroException;
import com.superheroes.models.Hero;

import java.util.List;

/**
 * Created by mcanuto on 5/19/16.
 *
 * This class contains methods that return results to the REST calls
 */
public class SuperheroesManager {
    DbManager dbManager = new DbManager();

    public SuperheroesManager() {
    }

    public List<Hero> getAllSuperheroes() {
        return dbManager.getSuperheroes();
    }

    public void addSuperhero(Hero hero) throws SuperheroException {
        dbManager.addHero(hero);
    }
    public void removeHero(String name) throws SuperheroException {
        dbManager.removeHero(name);
    }

    public Hero getHero(String name) throws SuperheroException {
        return dbManager.getHero(name);
    }
}
