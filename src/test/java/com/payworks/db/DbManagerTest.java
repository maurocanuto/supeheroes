package com.payworks.db;

import com.payworks.exceptions.SuperheroException;
import com.payworks.models.Hero;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Mauro Canuto (mauro.canuto@bsc.es)
 */

@RunWith(MockitoJUnitRunner.class)
public class DbManagerTest {

    private DbManager dbManager = new DbManager();
    private Hero hero;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @Mock
    private Query query;

    @InjectMocks
    private static Hero HeroImpl;


    @Before
    public void setUp(){
        entityManager = mock(EntityManager.class);
        transaction = mock(EntityTransaction.class);
        query = mock(Query.class);

        // Create fake hero
        ArrayList<String> skills = new ArrayList<>();
        ArrayList<String> allies = new ArrayList<>();
        skills.add("Fly");
        skills.add("Invisibility");

        allies.add("allie1Name");
        allies.add("allie2Name");

        hero = new Hero("heroName", "heroPseudonym", "heroPublisher", skills, allies, new Date(2010,01,30));
    }

    @After
    public void tearDown(){
        dbManager.deleteAllTables();
    }

    @Test
    public void addHeroTest() throws SuperheroException {
        dbManager.addHero(hero);
        assertTrue(dbManager.getSuperheroes().size() == 1);
        assertTrue(dbManager.getHero(hero.getName()).getPseudonym().equals(hero.getPseudonym()));
    }

    @Test
    public void removeHeroTest() throws SuperheroException {
        dbManager.addHero(hero);
        dbManager.removeHero(hero.getName());
        assertTrue(dbManager.getSuperheroes().size() == 0);
    }


    @Test
    public void getHeroTest() throws SuperheroException {
        dbManager.addHero(hero);
        Hero h = dbManager.getHero(hero.getName());
        assertTrue(h.getName().equals(hero.getName()));
    }




}
