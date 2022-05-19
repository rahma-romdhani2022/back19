package com.projet.BackendPfe.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.IAModel;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.IAModelRepository;
@Service
public class IAModelService implements InterfaceIAModel{

	@Autowired AdminDigitalMangerRepository adminrepo ;
	@Autowired IAModelRepository repository ;
	
	@Override
	public byte[] getFile(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFile(MultipartFile file, long id) throws IOException {
		 AdminDigitalManager admin =adminrepo.findById(id).get();	
		 File myFile = new File(System.getProperty("user.home")+"/files/",file.getOriginalFilename()+"");
		myFile.createNewFile();
		FileOutputStream fos =new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
		IAModel  model = new IAModel();
		model.setDate_hebergement(LocalDate.now());
		model.setUrlFile(myFile.getName()) ;
		model.setAdminD(admin);
		repository.save(model) ;
	}

	@Override
	public void deleteIAModel(long id) {
		repository.deleteById(id);
		
	}


}
