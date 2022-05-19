package com.projet.BackendPfe.Controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.IAModel;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.IAModelRepository;
import com.projet.BackendPfe.services.IAModelService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/IAModel")
public class IAModelController {

	@Autowired IAModelRepository repository ; 
	@Autowired AdminDigitalMangerRepository adminrepo ; 
	@Autowired IAModelService service ; 

	@PostMapping("/add/{idAdmin}")
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file
			, @PathVariable("idAdmin") long idAdmin) throws IOException{
		
		service.addFile(file, idAdmin);
		return new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<IAModel> getAll(){
		return repository.findAll() ; 
	}
	
	@DeleteMapping("/delete/{id}")
	public void  deleteIAModele(@PathVariable("id") long id){
		 service.deleteIAModel(id); ; 
	}
}
