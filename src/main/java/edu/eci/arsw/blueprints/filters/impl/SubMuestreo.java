/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Prieto
 */
@Service("Submuestreo")
public class SubMuestreo implements Filter {

    @Override
    public List<Point> filterBlueprints(Blueprint blueprint) {
        List<Point> ptsBlueprint = blueprint.getPoints();
        ArrayList<Point> pts = new ArrayList();
        int i = 0;
        for (int p = 0; p < ptsBlueprint.size(); p++) {
            if (i == 0) {
                pts.add(ptsBlueprint.get(p));
                i = 1;
            } else {
                i = 0;
            }
        }
        return pts;
    }

    @Override
    public List<List<Point>> filterBlueprints(Set<Blueprint> blueprints) {
        List<List<Point>> listPts = new ArrayList(new ArrayList());
        for (Blueprint bp : blueprints) {
            listPts.add(filterBlueprints(bp));
        }
        return listPts;
    }

}
