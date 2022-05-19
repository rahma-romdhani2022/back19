package com.projet.BackendPfe.Entity;


import javax.persistence.*;

@Entity
public class AvisExpert { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int graviteDroite;
	private int graviteGauche;
	/*@OneToMany(targetEntity = Consultation.class, mappedBy = "avisExpert", fetch = FetchType.LAZY)
	private List<Consultation> avisExpert = new ArrayList<Consultation>();*/
	
	@OneToOne(mappedBy = "avisExpert" , cascade = CascadeType.ALL)
	private AutoDetection autoDetection;
	
	@ManyToOne()
	private Expert expert;
	
	public AvisExpert(String commentaireGauche, String maladieGauche, int graviteGauche) {
		super();
		this.commentaireGauche = commentaireGauche;
		this.maladieGauche = maladieGauche;
		this.graviteGauche = graviteGauche;}
	

	public AvisExpert(String commentaireDroite,  int graviteDroite,String maladieDroite) {
		super();
		this.commentaireDroite = commentaireDroite;
		this.maladieDroite = maladieDroite;
		this.graviteDroite = graviteDroite;
	}

	public AvisExpert(String commentaireGauche, String commentaireDroite, String maladieDroite, String maladieGauche,
			int graviteDroite, int graviteGauche) { // pour update  {
		super();
		this.commentaireGauche = commentaireGauche;
		this.commentaireDroite = commentaireDroite;
		this.maladieDroite = maladieDroite;
		this.maladieGauche = maladieGauche;
		this.graviteDroite = graviteDroite;
		this.graviteGauche = graviteGauche;
	}
	private String commentaireGauche;
	private String commentaireDroite;
	
	public String getCommentaireGauche() {
		return commentaireGauche;
	}

	public void setCommentaireGauche(String commentaireGauche) {
		this.commentaireGauche = commentaireGauche;
	}

	public String getCommentaireDroite() {
		return commentaireDroite;
	}

	public void setCommentaireDroite(String commentaireDroite) {
		this.commentaireDroite = commentaireDroite;
	}


	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	public AvisExpert() {
		super();
	}

	public AvisExpert(Expert expert) {
		super();
		this.expert = expert;
	}


	public AvisExpert(String maladieDroite, String maladieGauche, Expert expert, int graviteDroite, int graviteGauche,String commentaireGauche,String commentaireDroite) {
		super();
		this.maladieDroite = maladieDroite;
		this.maladieGauche = maladieGauche;
		this.graviteDroite = graviteDroite;
		this.graviteGauche = graviteGauche;
		this.expert = expert;
		this. commentaireGauche= commentaireGauche;
		this. commentaireDroite= commentaireDroite;

	}

	private String maladieDroite;
	private String maladieGauche;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMaladieDroite() {
		return maladieDroite;
	}

	public void setMaladieDroite(String maladieDroite) {
		this.maladieDroite = maladieDroite;
	}

	public String getMaladieGauche() {
		return maladieGauche;
	}

	public void setMaladieGauche(String maladieGauche) {
		this.maladieGauche = maladieGauche;
	}

	public int getGraviteDroite() {
		return graviteDroite;
	}

	public void setGraviteDroite(int graviteDroite) {
		this.graviteDroite = graviteDroite;
	}

	public int getGraviteGauche() {
		return graviteGauche;
	}

	public void setGraviteGauche(int graviteGauche) {
		this.graviteGauche = graviteGauche;
	}


	
}
