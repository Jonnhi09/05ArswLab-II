/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jonathan Prieto
 */
public interface Filter {
    
    public List<Point> filterBlueprints(Blueprint blueprint);
    
    public List<List<Point>> filterBlueprints(Set<Blueprint> blueprints);
    
}
