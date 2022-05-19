package com.projet.BackendPfe.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.BackendPfe.Entity.Consultation;
import com.projet.BackendPfe.repository.ConsultationRepository;

@Service
public class ConsultationService {
@Autowired ConsultationRepository repository ; 





/******  Images Oeil droite *********/
public byte[] getImageDroite1(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage1_Droite() ; 
	Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
}
public byte[] getImageDroite2(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage2_Droite() ; 
	Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
}
public byte[] getImageDroite3(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage3_Droite() ; 
	Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
}
public byte[] getImageDroite4(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage4_Droite() ; 
	Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
}
public byte[] getImageDroite5(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage5_Droite() ; 
		Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
	
}


/************ Images oeil gauche *******/
public byte[] getImageGauche1(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage1_Gauche() ; 
	Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
}
public byte[] getImageGauche2(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage2_Gauche() ; 
	Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
}
public byte[] getImageGauche3(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage3_Gauche() ; 
	Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
}
public byte[] getImageGauche4(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage4_Gauche() ; 
	Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
}
public byte[] getImageGauche5(long  id) throws Exception{
	String nomImage =repository.findById(id).get().getImage5_Gauche() ; 
	Path p =Paths.get(System.getProperty("user.home")+"/files/",nomImage);
	return Files.readAllBytes(p);
	
}








	/**
	 * @return ************/
	public Consultation   getDiffTime (long idConsult , LocalDateTime dateAct , LocalDateTime dateConsult) {
		     
		    Consultation  consulta = repository.findById(idConsult).get() ;
	    	Duration duration = Duration.between(LocalDateTime.now(), dateConsult);
	        long difM  =Math.abs(duration.toMinutes()) ; 

		 
	        if(difM == 0 ) {
	        long difS  =Math.abs(duration.toMillis())/1000 ; 
	        consulta.setDecalageHoraire(difS+" "+"s");
 	        repository.save(consulta) ; 
	        }
	        else if(difM <60) {
	    	     consulta.setDecalageHoraire(difM +" "+"min");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM < 1440) {
		    	   consulta.setDecalageHoraire(difM/60 +" "+"heure");
			       repository.save(consulta) ; 
			      
		       }
	       
	        else if( difM >= 1440 && difM <2880) {
	    	     consulta.setDecalageHoraire("1 jour");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM >= 2880 && difM <4320) {
	    	     consulta.setDecalageHoraire("2 jour");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM >= 4320 && difM <5760) {
	    	     consulta.setDecalageHoraire("3 jour");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM >= 5760 && difM <7200) {
	    	     consulta.setDecalageHoraire("4 jour");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM >= 7200 && difM <8640) {
	    	     consulta.setDecalageHoraire("5 jour");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM >= 8640 && difM <10080) {
	    	     consulta.setDecalageHoraire("6 jour");
	  	        repository.save(consulta) ; 
	  	      
	       }
	       
	        else if( difM < 10080) {
	    	     consulta.setDecalageHoraire("1 semaine");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM <20160) {
	    	     consulta.setDecalageHoraire("2 semaine");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM < 30240) {
	    	     consulta.setDecalageHoraire("3 semaine");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM < 40320) {
	    	     consulta.setDecalageHoraire("4 semaine");
	  	        repository.save(consulta) ; 
	  	      
	       }
	        else if( difM >= 50400){
	        	 DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	   		   String formatDateTime = (consulta.getDateDemandeAvis()).format(format); 
	        	 consulta.setDecalageHoraire(formatDateTime);
			       repository.save(consulta) ;
	        }
	       
  System.out.println( "diff M egal ="+difM) ;
	     return consulta ;   
	       }
}
