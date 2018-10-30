/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("InMemory")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final ConcurrentMap<Tuple<String, String>, Blueprint> blueprints = new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts = new Point[]{new Point(140, 140), new Point(115, 115)};
        Blueprint bp = new Blueprint("sebas", "myBlueprint", pts);
        Point[] pts1 = new Point[]{new Point(0, 0), new Point(0, 0), new Point(2, 15), new Point(5, 15), new Point(2, 2), new Point(5, 15)};
        Blueprint bp1 = new Blueprint("juan", "basic1", pts1);
        Point[] pts2 = new Point[]{new Point(2, 2), new Point(3, 15), new Point(20, 15)};
        Blueprint bp2 = new Blueprint("juan", "basic2", pts2);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(), bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(), bp2.getName()), bp2);
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public synchronized void updateBlueprint(String author, String name, Point p) throws BlueprintPersistenceException {
        try {
            getBlueprint(author, name).addPoint(p);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(InMemoryBlueprintPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        Set<Blueprint> allBlueprints = new HashSet<>();
        Set<Entry<Tuple<String, String>, Blueprint>> view = blueprints.entrySet();
        for (Entry<Tuple<String, String>, Blueprint> v : view) {
            allBlueprints.add(v.getValue());
        }
        return allBlueprints;
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> blueprintsAuthor = new HashSet<>();
        Set<Entry<Tuple<String, String>, Blueprint>> view = blueprints.entrySet();
        for (Entry<Tuple<String, String>, Blueprint> v : view) {
            if (v.getKey().getElem1().equals(author)) {
                blueprintsAuthor.add(v.getValue());
            }
        }
        if (blueprintsAuthor.size() < 1) {
            throw new BlueprintNotFoundException("The given author does not exist: " + author);
        }
        return blueprintsAuthor;
    }

}
