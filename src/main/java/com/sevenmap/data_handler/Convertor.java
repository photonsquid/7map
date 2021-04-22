package com.sevenmap.data_handler;

import com.sevenmap.ui.elements.Item;
import com.sevenmap.ui.gfx.Mesh;
import com.sevenmap.ui.gfx.Vertex;
import com.sevenmap.ui.math.Vector3f;
import java.util.*;

public class Convertor {

    /**
     * crée un item à partir d'une feature en lui mettant une épaisseur de trait
     * @return un item 
     */
    public Item convertirRoute(Feature feature, float epaisseur) {
        Vector3f position = new Vector3f(0,0,0);
        Vector3f rotation = new Vector3f(0,0,0);
        Vector3f scale = new Vector3f(0,0,0);
        Point[] points = feature.getPoints();
        int nbPoints = points.length;

        List<Vertex> vertices = new ArrayList<Vertex>();

        Vector3f dir1 = (new Vector3f(points[1].getX() - points[0].getX(), points[1].getY() - points[0].getY(), 0.0F)).normalize();
        Vector3f orthoDir1 = (new Vector3f(dir1.getY(),-dir1.getX(),0.0F)).normalize();
        vertices.add(new Vertex((new Vector3f(points[0].getX(),points[0].getY(),0.0F).add(orthoDir1.multiply(new Vector3f(epaisseur/2.0F,epaisseur/2.0F,epaisseur/2.0F)))), new Vector3f(0.0f,0.0f,0.0f)));
        vertices.add(new Vertex((new Vector3f(points[0].getX(),points[0].getY(),0).sub(orthoDir1.multiply(new Vector3f(epaisseur/2,epaisseur/2,epaisseur/2)))), new Vector3f(0.0f,0.0f,0.0f)));
        for (int i = 1; i <= nbPoints - 2; i++) {
            Vector3f dirPrecedant = (new Vector3f(points[i].getX() - points[i-1].getX(), points[i].getY() - points[i-1].getY(), 0.0F)).normalize();
            Vector3f dirSuivant = (new Vector3f(points[i+1].getX() - points[i].getX(), points[i+1].getY() - points[i].getY(), 0.0F)).normalize();
            Vector3f orthoDir = dirPrecedant.add(dirSuivant);
            vertices.add(new Vertex((new Vector3f(points[i].getX(),points[i].getY(),0).add(orthoDir.normalize().multiply(new Vector3f(epaisseur/2,epaisseur/2,epaisseur/2)))), new Vector3f(0.0f,0.0f,0.0f)));
            vertices.add(new Vertex((new Vector3f(points[i].getX(),points[i].getY(),0).sub(orthoDir.normalize().multiply(new Vector3f(epaisseur/2,epaisseur/2,epaisseur/2)))), new Vector3f(0.0f,0.0f,0.0f)));
        
        }
        Vector3f dirDer = new Vector3f(points[nbPoints-1].getX() - points[nbPoints].getX(), points[nbPoints-1].getY() - points[nbPoints].getY(), 0.0F);
        Vector3f orthoDirDer = new Vector3f(dirDer.getY(),-dirDer.getX(),0.0F);
        vertices.add(new Vertex((new Vector3f(points[0].getX(),points[0].getY(),0).add(orthoDirDer.normalize().multiply(new Vector3f(epaisseur/2,epaisseur/2,epaisseur/2)))), new Vector3f(0.0f,0.0f,0.0f)));
        vertices.add(new Vertex((new Vector3f(points[0].getX(),points[0].getY(),0).sub(orthoDirDer.normalize().multiply(new Vector3f(epaisseur/2,epaisseur/2,epaisseur/2)))), new Vector3f(0.0f,0.0f,0.0f)));
        
        
        List<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i <= nbPoints - 2; i++) {
            indices.add(2*i);
            indices.add(2*i+1);
            indices.add(2*i+2);
            indices.add(2*i+1);
            indices.add(2*i+2);
            indices.add(2*i+3);
        }
        Mesh mesh = new Mesh(vertices, indices);
        return new Item(position, rotation, scale, mesh);
    }


    /**
     * convertit une feature en item
     * la feature doit être convexe
     * @return un item 
     */
    public Item convertirFeature(Feature feature) {
        Vector3f position = new Vector3f(0,0,0);
        Vector3f rotation = new Vector3f(0,0,0);
        Vector3f scale = new Vector3f(0,0,0);
        Point[] points = feature.getPoints();
        int nbPoints = points.length;

        Point barycentre = new Point(0.0F, 0.0F);
        for (int i=0; i < nbPoints; i++) {
            barycentre.add(points[i]);
        }
        barycentre.divide(nbPoints);

        List<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i <= nbPoints - 1; i++) {
            vertices.add(new Vertex((new Vector3f(points[i].getX(),points[i].getY(),0.0F)), new Vector3f(0.0f,0.0f,0.0f)));
        }
        vertices.add(new Vertex((new Vector3f(barycentre.getX(),barycentre.getY(),0.0F)), new Vector3f(0.0f,0.0f,0.0f)));

        List<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i <= nbPoints - 2; i++) {
            indices.add(i);
            indices.add(i+1);
            indices.add(nbPoints);
        }

        Mesh mesh = new Mesh(vertices, indices);
        return new Item(position, rotation, scale, mesh);
    }

    /**
     * crée la "bordure" d'un objet 
     * @return un item 
     */
    public Item bordure(Feature feature, float epaisseur) {
        Vector3f position = new Vector3f(0,0,0);
        Vector3f rotation = new Vector3f(0,0,0);
        Vector3f scale = new Vector3f(0,0,0);
        Point[] points = feature.getPoints();
        int nbPoints = points.length;

        
        List<Vertex> vertices = new ArrayList<Vertex>();

        Vector3f dir1 = (new Vector3f(points[1].getX() - points[0].getX(), points[1].getY() - points[0].getY(), 0.0F)).normalize();
        Vector3f orthoDir1 = (new Vector3f(dir1.getY(),-dir1.getX(),0.0F)).normalize();
        vertices.add(new Vertex((new Vector3f(points[0].getX(),points[0].getY(),0.0F).add(orthoDir1.multiply(new Vector3f(epaisseur,epaisseur,epaisseur)))), new Vector3f(0.0f,0.0f,0.0f)));
        vertices.add(new Vertex((new Vector3f(points[0].getX(),points[0].getY(),0)), new Vector3f(0.0f,0.0f,0.0f)));
        for (int i = 1; i <= nbPoints - 2; i++) {
            Vector3f dirPrecedant = (new Vector3f(points[i].getX() - points[i-1].getX(), points[i].getY() - points[i-1].getY(), 0.0F)).normalize();
            Vector3f dirSuivant = (new Vector3f(points[i+1].getX() - points[i].getX(), points[i+1].getY() - points[i].getY(), 0.0F)).normalize();
            Vector3f orthoDir = dirPrecedant.add(dirSuivant);
            vertices.add(new Vertex((new Vector3f(points[i].getX(),points[i].getY(),0).add(orthoDir.normalize().multiply(new Vector3f(epaisseur,epaisseur,epaisseur)))), new Vector3f(0.0f,0.0f,0.0f)));
            vertices.add(new Vertex((new Vector3f(points[i].getX(),points[i].getY(),0)), new Vector3f(0.0f,0.0f,0.0f)));
        
        }
        Vector3f dirDer = new Vector3f(points[nbPoints-1].getX() - points[nbPoints].getX(), points[nbPoints-1].getY() - points[nbPoints].getY(), 0.0F);
        Vector3f orthoDirDer = new Vector3f(dirDer.getY(),-dirDer.getX(),0.0F);
        vertices.add(new Vertex((new Vector3f(points[0].getX(),points[0].getY(),0).add(orthoDirDer.normalize().multiply(new Vector3f(epaisseur,epaisseur,epaisseur)))), new Vector3f(0.0f,0.0f,0.0f)));
        vertices.add(new Vertex((new Vector3f(points[0].getX(),points[0].getY(),0)), new Vector3f(0.0f,0.0f,0.0f)));
        
        
        List<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i <= nbPoints - 2; i++) {
            indices.add(2*i);
            indices.add(2*i+1);
            indices.add(2*i+2);
            indices.add(2*i+1);
            indices.add(2*i+2);
            indices.add(2*i+3);
        }
        Mesh mesh = new Mesh(vertices, indices);
        return new Item(position, rotation, scale, mesh);
    }
}
