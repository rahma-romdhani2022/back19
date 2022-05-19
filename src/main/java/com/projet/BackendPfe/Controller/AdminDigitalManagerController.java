package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.Entity.AdminMedicalManager;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.request.RegisterRequestAdmin;
import com.projet.BackendPfe.request.RegisterRequestExpert;
import com.projet.BackendPfe.services.AdminDigitalManagerService;
import com.projet.BackendPfe.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adminDigital")

public class AdminDigitalManagerController {

	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	AdminDigitalMangerRepository repository;
	@Autowired	UserRepository userRepository; 
	@Autowired	private  AdminDigitalManagerService service  ;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest data) {
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword()));	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getNomPrenom(), 
												 userDetails.getEmail() , 
												 userDetails.getRole()
											));}
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterRequestAdmin signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));	}
		
		if (repository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));
		}
		AdminDigitalManager admin = new AdminDigitalManager(
				signUpRequest.getUsername(), 
			    signUpRequest.getEmail(),
				signUpRequest.getNomPrenom(),
				 encoder.encode(signUpRequest.getPassword()),
						 signUpRequest.getImage(),LocalDate.now() , "Admin Digital Manager");
		repository.save(admin);

		return ResponseEntity.ok(new Message("Admin  registered successfully!"));
	}	  
	
	
	  @PutMapping("/update/{id}")
	  public ResponseEntity<AdminDigitalManager> update(@PathVariable("id") long id, @RequestBody Expert Utilisateur) {
	    System.out.println("Update Admin  with ID = " + id + "...");
	 
	    AdminDigitalManager  admin = repository.findById(id).get();
	    	//utilisateur.setTelephone(Utilisateur.getTelephone());
	    	//utilisateur.setGender(Utilisateur.getGender());
	    	     //  utilisateur.getEmail();
	        // utilisateur.getUsername();
	    	
	      return new ResponseEntity<>(repository.save(admin), HttpStatus.OK);
	    } 
	  
	  @PutMapping("/updateImage/{id}")
		public String updateImage1DD(@PathVariable("id") long id  , @RequestParam("file") MultipartFile file ) throws IOException {
				service.updateImageAdminDigitalManager(id,  file);
				
			return "Done pour image2  Droite!!!!" ; 
		}
	  @GetMapping( "/getAdmin/{id}" )
		public AdminDigitalManager getAdmin(@PathVariable("id") long id) throws IOException {

		  AdminDigitalManager admin = repository.findById(id).get();
				return admin;
		}
	  
	  @GetMapping( path="/getImage/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
		public byte[] getImage(@PathVariable("id") long id)throws Exception{
		  AdminDigitalManager admin = repository.findById(id).get();
          return  service.getImageAdminDigitalManager(admin.getId());
			
		}
}
