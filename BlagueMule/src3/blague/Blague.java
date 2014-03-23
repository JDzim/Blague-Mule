package blague;

import java.io.Serializable;

/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public class Blague implements Serializable
{
    
    //ATTRIBUTS
    private String nom;
    private String question;
    private String reponse;
    
    
    //CONSTRUCTEUR
    public Blague(String nom, String question, String reponse)
    {
        this.nom = nom;
        this.question = question;
        this.reponse = reponse;
    }
    
    
    //METHODES
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getReponse() {
        return reponse;
    }
    
    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    
}
