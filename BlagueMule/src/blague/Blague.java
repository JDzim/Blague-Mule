package blague;

import java.io.Serializable;

public class Blague implements Serializable
{
    private String nom;
    private String question;
    private String reponse;
    
    public Blague(String n, String q, String r)
    {
        nom = n;
        question = q;
        reponse = r;
    }

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
