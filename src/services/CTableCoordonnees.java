/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import entites.CPersonne;
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
public class CTableCoordonnees {

    CBDD bdd;

    public int insererPersonne(CPersonne personne) {
        int res = -1;
        if (bdd.connecter()) {
            String requete = "INSERT INTO `coordonnees` (`Nom`, `Prenom`) "
                    + "VALUES ('"
                    + personne.getNom() + "', '"
                    + personne.getPrenom() + "')";

            res = bdd.executerRequeteUpdate(requete);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public CPersonne lirePersonne(int id) {

        if (bdd.connecter()) {
            String requete = "Select * from coordonnees where id=" + id;
            ResultSet rs = bdd.executerRequeteQuery(requete);
            try {
                if (rs.next()) {
//                    CPersonne personne = new CPersonne();
//                    personne.setId(rs.getInt("id"));
//                    personne.setNom(rs.getString("Nom"));
//                    personne.setPrenom(rs.getString("Prenom"));
                    return new CPersonne(rs.getInt("id"), rs.getString("Nom"),
                            rs.getString("Prenom"));
                } else {
                    System.err.println("C'est vide.");
                    JOptionPane.showMessageDialog(null, "C'est Vide.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CTableCoordonnees.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        }
        return null;
    }

    public ArrayList<CPersonne> lirePersonnes() {
        ArrayList<CPersonne> collecPersonnes = new ArrayList();
        if (bdd.connecter()) {
            String requete = "Select * from coordonnees";
            ResultSet rs = bdd.executerRequeteQuery(requete);
            try {
                while (rs.next()) {
                    collecPersonnes.add(
                            new CPersonne(
                                    rs.getInt("id"),
                                    rs.getString("Nom"),
                                    rs.getString("Prenom")
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

    public int modifierPersonne(CPersonne personne) {
        int res = -1;
        if (bdd.connecter()) {
            String requete = "UPDATE `coordonnees` SET "
                    + "`Nom` = '" + personne.getNom() + "', "
                    + "`Prenom` = '" + personne.getPrenom() + "'"
                    + " WHERE `coordonnees`.`id` =" + personne.getId();
            res = bdd.executerRequeteUpdate(requete);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public int supprimerPersonne(CPersonne personne) {
        int res = -1;
        if (bdd.connecter()) {
            String req = "DELETE FROM `coordonnees` WHERE `id` = " + personne.getId();
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public static void main(String[] args) {
        CTableCoordonnees table = new CTableCoordonnees();
        table.bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));

//        CPersonne unePersonne = new CPersonne();
//        unePersonne.setNom("Franky");
//        unePersonne.setPrenom("Victor");
//        table.insererPersonne(unePersonne);

//        System.out.println(table.lirePersonne(3));
//        table.insererPersonne(new CPersonne(1, "JeanGuile", "JeanGuile"));
//        System.out.println(table.lirePersonnes());
//        table.modifierPersonne(new CPersonne(1, "Serres", "Damien"));
//        table.supprimerPersonne(new CPersonne(2, "", ""));
//        System.out.println(table.lirePersonnes());

    }
}
