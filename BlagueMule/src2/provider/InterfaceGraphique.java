package provider;

import blague.Blague;
import codebase.BlagueAbsenteException;
import codebase.BlagueProviderP2P;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public class InterfaceGraphique extends JFrame {
	
    //ATTRIBUTS
	/**
	 * blague provider observé
	 */
	public BlagueProvider bp;
	
	/**
	 * la liste à gerer pour l'affichage des blagues locales
	 * mise à jour avec MaJBlagues() 
	 */
	JList blaguesLocales; 
	
	/**
	 * la liste à gerer pour l'affichage des blagues distantes
	 * mise à jour avec MaJBlaguesDistantes() 
	 */
	JList blaguesDistantes; 
	
	/**
	 * la liste à gerer pour l'affichage des serveurs
	 * mise à jour avec MaJServeurs() 
	 */
	JList serveurs;
	
    
    //CONSTRUCTEUR
    /**
	 * construction de l'interface
	 */
	public InterfaceGraphique(String nom, BlagueProvider bp)
	{
		super("Blaguemule: "+nom);
		
		//mise à jour du lien vers le modele
		this.bp=bp;
		
		//construction de l'interface
		JTabbedPane onglets=new JTabbedPane();
		onglets.addTab("local", ongletLocal());
		onglets.addTab("distant",ongletDistant());
		
		//affichage du JFRAME
		setContentPane(onglets);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
    
	
	//METHODES
	/**
	 * ajoute l'onglet distant à l'interface
	 */
	public JPanel ongletDistant()
	{
		
		JPanel pdistant=new JPanel();
		
		//la boite contenant tout
		Box distant=new Box(BoxLayout.Y_AXIS);
		
		//etiquette 1
		distant.add(new JLabel("Serveur distant"));
				
		//la partie serveur distant
		JPanel Pserveurs=new JPanel();
		serveurs=new JList();
		serveurs.setPreferredSize(new Dimension(300,200));
		Pserveurs.add(serveurs);
		//ajouter boite
		distant.add(Pserveurs);

		//etiquette2
		distant.add(new JLabel("Blague sur serveur selectionne"));
		
		//la partie blague distante
		JPanel PblaguesDistantes=new JPanel();
		blaguesDistantes=new JList();
		blaguesDistantes.setPreferredSize(new Dimension(300,200));
		PblaguesDistantes.add(blaguesDistantes);
		//ajoute à la boite
		distant.add(PblaguesDistantes);
		
		// bouton de sauvegarde
		JButton bouton = new JButton("telecharge");
		
                bouton.addActionListener
                (
                    new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent ae) 
                        {
                            try 
                            {
                                String blague = (String) blaguesDistantes.getSelectedValue();
                                BlagueProvider proxy = (BlagueProvider) bp.getRepertoireProxy().get((String) serveurs.getSelectedValue());
                                bp.telechargerBlague(blague, proxy);
                            } 
                            catch (Exception e) 
                            {
                                e.printStackTrace();
                            }
                            
                            //this.notify();
                            //this.notifyAll();
                        }
                    }
                );
                
                distant.add(bouton);

		
		//encapsuler dans un jpanel
		pdistant.add(distant);
		return(pdistant);
	}
	
	/**
	 * ajoute l'onglet local à l'interface
	 */
	public JPanel ongletLocal()
	{
		//l'onglet local
		JPanel local=new JPanel();
		Box blocal=new Box(BoxLayout.Y_AXIS);
		
		//etiquette 1
		blocal.add(new JLabel("Blagues connues en local"));
		
		//blagues locales
		JPanel PblaguesLocales=new JPanel();
		blaguesLocales=new JList();
		MaJBlagues();
		blaguesLocales.setPreferredSize(new Dimension(300,200));
		PblaguesLocales.add(blaguesLocales);
		//ajout dans boite
		blocal.add(PblaguesLocales);
		
		//etiquette2
		blocal.add(new JLabel("Information blague locale"));
				
		//les informations sur les blagues
		//nom de la blague
		final JTextField nom=new JTextField();
		blocal.add(nom);
		
		//contenu 
		final JTextField question=new JTextField();
		blocal.add(question);
		
		//reponse
		final JTextArea reponse=new JTextArea();
		reponse.setPreferredSize(new Dimension(300,200));
		blocal.add(reponse);
		
		//bouton de sauvegarde
		JButton bouton=new JButton("sauve");
                
                bouton.addActionListener
                (
                    new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent ae) 
                        {
                            bp.addBlague(new Blague(nom.getText(), question.getText(), reponse.getText()));     
                            this.notify();
                            this.notifyAll();
                        }
                    }
                );
                
		blocal.add(bouton);
			
		local.add(blocal);
		return(local);
	}
	
	/**
	 * methode chargée de mettre à jour l'affichage des blagues
	 * à partir de bp
	 */
	public void MaJBlagues()
	{
                //blaguesLocales
                String[] blag;
//                try {
                    blag = bp.getAllName();
                    DefaultListModel dlm = new DefaultListModel();
                    blaguesLocales.removeAll();

                    for (String s : blag)
                    {
                        dlm.addElement(s);
                    }

                    blaguesLocales.setModel(dlm);
//                } catch (RemoteException re) {
//                    re.printStackTrace();
//                }
                
	}
	
	/**
	 * methode chargée de mettre à jour l'affichage des serveurs
	 * à partir de bp quand lisRef est modifié
	 */
	public void MaJServeurs()
	{
		//serveurs
                
                HashMap<String,BlagueProviderP2P> serv = null;
                DefaultListModel dlm = new DefaultListModel();
            
                try
                {
                    serv = bp.getRepertoireProxy();
                    serveurs.removeAll();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
                for (Entry<String,BlagueProviderP2P> entry : serv.entrySet())
                {
                    dlm.addElement(entry.getKey());
                }
                
                serveurs.setModel(dlm);
	}
	
	/**
	 * methode chargée de mettre à jour l'affichage des blagues distantes
	 * quand on selectionne un listeref
	 */
	public void MaJBlagueDist()
	{
		//blaguesDistantes
                try
                {
                    String[] blag = bp.getRepertoireProxy().get(serveurs.getSelectedValue()).getAllName();    
                    DefaultListModel dlm = new DefaultListModel();
                    blaguesDistantes.removeAll();

                    for (String s : blag)
                    {
                        dlm.addElement(s);
                    }

                    blaguesDistantes.setModel(dlm);
                }
                catch (Exception e)
                {
                    System.out.println("Exception");
                    e.printStackTrace();
                }
	}
	
	
    //MAIN
	public static void main (String args[])
	{
            try
            {
                String titre1 = "";
                String titre2 = "";
                String titre3 = "";
                
                if (args[0].compareTo("prov1") == 0) 
                {
                    titre1 = "AAAAA";
                    titre2 = "BBBBB";
                    titre3 = "CCCCC";
                }
                if (args[0].compareTo("prov2") == 0) 
                {
                    titre1 = "XXXXX";
                    titre2 = "YYYYY";
                    titre3 = "ZZZZZ";
                }
                
                HashMap<String,Blague> listeBlagues = new HashMap();
                listeBlagues.put(titre1, new Blague(titre1,"Qui suis-je ?","Une 1ere blague !"));
                listeBlagues.put(titre2, new Blague(titre2,"Qui suis-je ?","Une 2nde blague !"));
                listeBlagues.put(titre3, new Blague(titre3,"Qui suis-je ?","Une 3eme blague !"));
                
                BlagueProvider bp = new BlagueProvider(args[0],listeBlagues);
                InterfaceGraphique ihm = new InterfaceGraphique(args[0],bp);

                HashMap<String,BlagueProviderP2P> autresNomsProviders = new HashMap<>();

                Registry reg = LocateRegistry.getRegistry();
                BlagueProviderP2P proxy = (BlagueProviderP2P) UnicastRemoteObject.exportObject(bp,0);
                reg.rebind(args[0],proxy);
                
                System.in.read();
                System.out.println("Enregistrement effectue");
                
                for (int i = 1 ; i < args.length ; i++)
                {
                    autresNomsProviders.put(args[i],(BlagueProviderP2P) reg.lookup(args[i]));
                }

                bp.setRepertoireProxy(autresNomsProviders);
                
                System.in.read();
                System.out.println("Obtention des proxys effectuee");
            }
            catch (Exception e)
            {
                System.out.println("Exception");
                e.printStackTrace();
            }
	}
	
}
