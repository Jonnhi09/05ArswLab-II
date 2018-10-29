/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@Service
//@CrossOrigin("*")
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bs;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoPlanos() {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bs.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al obtener todos los planos", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{author}")
    public ResponseEntity<?> manejadorGetRecursoPlanosPorAutor(@PathVariable("author") String author) {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bs.getFilteredBlueprintByAuthor(author), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al obtener los planos de: " + author, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{author}/{name}")
    public ResponseEntity<?> manejadorGetRecursoPlanosPorAutor(@PathVariable("author") String author, @PathVariable("name") String name) {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bs.getFilteredBlueprint(author, name), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al obtener el plano: " + name + " de: " + author, HttpStatus.NOT_FOUND);
        }
    }
}
