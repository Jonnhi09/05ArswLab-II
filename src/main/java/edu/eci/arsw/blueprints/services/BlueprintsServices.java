/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.model.Point;
import java.util.List;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {

    @Autowired
    @Qualifier("InMemory")
    BlueprintsPersistence bpp = null;

    @Autowired
    @Qualifier("Redundancia")
    Filter filter = null;

    /**
     *
     * @param bp
     * @throws BlueprintPersistenceException
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    /**
     *
     * @return
     */
    public Set<Blueprint> getAllBlueprints() {
        return bpp.getAllBlueprints();
    }

    /**
     *
     * @param author
     * @param name
     * @return
     * @throws BlueprintNotFoundException
     */
    public List<Point> getFilteredBlueprint(String author, String name) throws BlueprintNotFoundException {
        return filter.filterBlueprints(bpp.getBlueprint(author, name));
    }
    
    /**
     *
     * @param author
     * @return
     * @throws BlueprintNotFoundException
     */
    public List<List<Point>> getFilteredBlueprintByAuthor(String author) throws BlueprintNotFoundException {
        return filter.filterBlueprints(bpp.getBlueprintsByAuthor(author));
    }
}
