
import java.awt.Dimension;
import java.rmi.RemoteException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import provider.BlagueProvider;

/**
 * @author Joseph DZIMBALKA
 * @author Julien RISCHE
 */
public class InterfaceGraphique extends JFrame{
	
	/**
	 * blague provider observ?
	 */
	public BlagueProvider bp;
	
	/**
	 * la liste ? gerer pour l'affichage des blagues locales
	 * mise ? jour avec MaJBlagues() 
	 */
	JList blaguesLocales; 
	
	/**
	 * la liste ? gerer pour l'affichage des blagues distantes
	 * mise ? jour avec MaJBlaguesDistantes() 
	 */
	JList blaguesDistantes; 
	
	/**
	 * la liste ? gerer pour l'affichage des serveurs
	 * mise ? jour avec MaJServeurs() 
	 */
	JList serveurs;
	
	
	
	
	
	/**
	 * ajoute l'onglet distant ? l'interface
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
		//ajoute ? la boite
		distant.add(PblaguesDistantes);
		
		// bouton de sauvegarde
		JButton bouton = new JButton("telecharge");
		distant.add(bouton);

		
		//encapsuler dans un jpanel
		pdistant.add(distant);
		return(pdistant);
	}
	
	
	
	/**
	 * ajoute l'onglet local ? l'interface
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
		JTextField nom=new JTextField();
		blocal.add(nom);
		
		//contenu 
		JTextField question=new JTextField();
		blocal.add(question);
		
		//reponse
		JTextArea reponse=new JTextArea();
		reponse.setPreferredSize(new Dimension(300,200));
		blocal.add(reponse);
		
		//bouton de sauvegarde
		JButton bouton=new JButton("sauve");
		blocal.add(bouton);
			
		local.add(blocal);
		return(local);
	}
	
	
	/**
	 * construction de l'interface
	 */
	public InterfaceGraphique(String nom, BlagueProvider bp)
	{
		super("Blaguemule: "+nom);
		
		//mise ? jour du lien vers le modele
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
	
	
	/**
	 * methode charg?e de mettre ? jour l'affichage des blagues
	 * ? partir de bp
	 */
	public void MaJBlagues()
	{
		
	}
	
	/**
	 * methode charg?e de mettre ? jour l'affichage des serveurs
	 * ? partir de bp quand lisRef est modifi?
	 */
	public void MaJServeurs()
	{
		
	}
	
	/**
	 * methode charg?e de mettre ? jour l'affichage des blagues distantes
	 * quand on selectionne un listeref
	 */
	public void MaJBlagueDist()
	{
		
	}
	
	
	public static void main (String args[])
	{
		InterfaceGraphique test=new InterfaceGraphique("toto",new BlagueProvider("toto"));
		
	}
	
	
}