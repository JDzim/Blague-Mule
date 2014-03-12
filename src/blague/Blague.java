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
    
    public String getNom()
    {
        return nom;
    }
    
    public String getQuestion()
    {
        return question;
    }
    
    public String getReponse()
    {
        return reponse;
    }
}
