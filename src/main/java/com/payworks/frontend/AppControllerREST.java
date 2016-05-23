package com.payworks.frontend;

import com.payworks.core.SuperheroesManager;
import com.payworks.exceptions.SuperheroException;
import com.payworks.models.Hero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mauro Canuto (mauro.canuto@bsc.es)
 *
 * This class is responsible of REST calls
 *
 */

@RestController
public class AppControllerREST {

    SuperheroesManager superheroesManager = new SuperheroesManager();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Testing Spring Boot!";
    }

    /**
     * Get all the superheroes
     * @return a list of Superheroes
     */
    @RequestMapping(value="/getAllSuperheroes",method = RequestMethod.GET, produces = "application/json")
    public List<Hero> getAllSuperheroes(){
        return superheroesManager.getAllSuperheroes();
    }

    @RequestMapping(value = "/addSuperhero", method = RequestMethod.POST)
    public ResponseEntity addSuperhero(@RequestBody Hero hero){
        try {
            superheroesManager.addSuperhero(hero);
            return ResponseEntity
                    .status(HttpStatus.CREATED).build();

        } catch (SuperheroException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Hero " + hero.getName() + "is already present!\n");
        }

    }

    /**
     * Get info about a specific hero
     * @param name
     * @return Hero info
     * @throws SuperheroException
     */

    @RequestMapping(value = "/getHero/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getHero(@PathVariable("name") String name) throws SuperheroException {
        try {
            Hero h = superheroesManager.getHero(name);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(h);

        } catch (SuperheroException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Hero " + name + " is not present!\n");

        }
    }

    /**
     * Remove a specific hero
     * @param name
     * @throws SuperheroException
     */
    @RequestMapping(value = "/removeHero/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity removeHero(@PathVariable("name") String name) throws SuperheroException {
        try {
            superheroesManager.removeHero(name);

            return ResponseEntity
                    .status(HttpStatus.CREATED).build();
        } catch (SuperheroException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Hero " + name + " is not present: nothing to remove\n");
        }
    }


}
