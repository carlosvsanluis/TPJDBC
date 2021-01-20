/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import entites.CPersonne;
import entites.CPersonnels;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Damien
 */
public class CTablePersonnels {
    CBDD bdd;
    
    public int insererPersonnels(CPersonnels personnels) {
        int res = -1;
        if (bdd.connecter()) {
            String requete = "INSERT INTO `personnels` (`Matricule`,`Nom`, `Prenom`,`TauxHoraire`,  ) "
                    + "VALUES ("
                    + personnels.getNom() + "', '"
                    + personnels.getPrenom() + "', '"
                    + personnels.getMatricule() + "')"
                    + personnels.getTauxHoraire() + ",'";
                         
            res = bdd.executerRequeteUpdate(requete);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }   
    
     public CPersonnels lirePersonnel(String matricule ) {

        if (bdd.connecter()) {
            String requete = "Select * from personnels where matricule='"+matricule+"'";
            ResultSet rs = bdd.executerRequeteQuery(requete);
            try {
                if (rs.next()) {

                    return new CPersonnels(rs.getString("Matricule"), rs.getString("Nom"),
                            rs.getString("Prenom"), rs.getFloat("tauxHoraire"));
                } else {
                    System.err.println("C'est vide.");
                    JOptionPane.showMessageDialog(null, "C'est Vide.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CTablePersonnels.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        }
        return null;
    }
     
         public ArrayList<CPersonnels> lirePersonnels() {
        ArrayList<CPersonnels> collecPersonnes = new ArrayList();
        if (bdd.connecter()) {
            String requete = "Select * from personnels";
            ResultSet rs = bdd.executerRequeteQuery(requete);
            try {
                while (rs.next()) {
                    collecPersonnes.add(
                            new CPersonnels(
                                    rs.getString("Matricule"),
                                    rs.getString("Nom"),
                                    rs.getString("Prenom"),
                                    rs.getFloat("TauxHoraire")
                                    
                            )
                    );
                }
                if (collecPersonnes.isEmpty()) {
                    System.err.println("C'est vide.");
                    JOptionPane.showMessageDialog(null, "C'est Vide.");
                }
                return collecPersonnes;

            } catch (SQLException ex) {
                Logger.getLogger(CTableCoordonnees.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        }
        return null;
    }
       public float modifierPersonnels(CPersonnels personnels) {
        int res = -1;
        if (bdd.connecter()) {
            String requete = "UPDATE `personnels` SET "
                    + "nom = '" + personnels.getNom() + "', "
                    + "prenom = '" + personnels.getPrenom() + "',"
                    + "tauxHoraire = '" + personnels.getTauxHoraire()+ "'"
                    + " WHERE `personnels`.`matricule` = '" + personnels.getMatricule()+"'";
            res = bdd.executerRequeteUpdate(requete);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }
     
       
           public float supprimerPersonnels(CPersonnels personnels) {
        int res = -1;
        if (bdd.connecter()) {
            String req = "DELETE FROM `coordonnees` WHERE `matricule` = " + personnels.getMatricule();
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
        
    }
      
      public static void main(String[] args) {
        CTablePersonnels table = new CTablePersonnels();
        table.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
 //        CPersonnels personnels = new CPersonnels(12.1f,  "DORIS", "Greg", "357TUG" );    
 //       table.insererPersonnels(personnels);
 
 //table.modifierPersonnels(new CPersonnels("357TUG", "GG", "Jeano", (float) 12.2));
        

        System.out.println(table.lirePersonnel("a1234567"));


     
//        table.supprimerPersonne(new CPersonne(2, "", ""));
//        System.out.println(table.lirePersonnes());

    }       
           
           
}
