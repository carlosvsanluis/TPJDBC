/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.regex.Pattern;

/**
 *
 * @author Damien
 */
public class CPersonnels {
    
    
    private String nom;
    private String prenom;
    private String matricule;
    private float tauxHoraire;

    public CPersonnels(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public CPersonnels(String matricule, String nom, String prenom, float tauxHoraire ) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tauxHoraire = tauxHoraire;
    }

    /**
     * Calcule un salaire à partir d'un nombre d'heures travaillées.
     *
     * @param nbreHeures
     * @return
     */
    public float calculerSalaire(float nbreHeures) {
        return this.tauxHoraire * nbreHeures;
    }

    public float calculerSalaireMensuelTP() {
        return this.tauxHoraire * 151.7f;
    }

    public float getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(float tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public static void main(String[] args) {
//        CPersonnels testBoy = new CPersonnels(500, "Serres", "Damien", "s5555555");
//        System.out.println(testBoy.calculerSalaire(29.2f));
//        System.out.println(testBoy.calculerSalaireMensuelTP());

        System.out.println(Pattern.matches("[atcds][0-9]{7}", "a1234567"));
    }

}
