package com.projet.BackendPfe.Controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.ExpertRepository;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.services.ExpertService;
import com.projet.BackendPfe.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired 	UserRepository repository;
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	UserRepository userRepository;
	@Autowired	ExpertRepository expertRepository;
	@Autowired	GeneralisteRepository genRepository;
	@Autowired	AdminDigitalMangerRepository adminRepository;
	@Autowired ExpertService service ; 

    @Autowired private ExpertService expertService ;
	
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest data) {
		System.out.println("aaaa");
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword())
			
				);
		  System.out.println("bbbbb");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getNomPrenom(), 
												 userDetails.getEmail() , 
												 userDetails.getRole()
											));
	}
	
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") long id) {
		User user = userRepository.findById(id).get() ; 
		return user ; 
	}

	    /****************** Test put ***********
	    @PutMapping( "/updateEx/{id}")
		 public ResponseEntity<Message> saveUser (@PathVariable ("id") long id , @RequestParam("file") MultipartFile file,
				 @RequestParam("expert") String expert) throws JsonParseException , JsonMappingException , Exception
		 {
	    	
	       Expert expert1 = expertRepository.findById(id).get();
	        expert1 =new ObjectMapper().readValue(expert, Expert.class); 
	        expert1.setImage(expertService.compressZLib(file.getBytes()));
	        Expert  resultat  = repository.save(expert1);
	        	return new ResponseEntity<Message>(new Message ("Expert  savrd "),HttpStatus.OK);	
		 }
*/
		  
		  
}