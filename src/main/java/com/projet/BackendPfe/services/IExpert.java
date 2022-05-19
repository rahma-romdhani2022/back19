package com.projet.BackendPfe.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.Consultation;

public interface IExpert {

	public byte[] getImageExpert(long  id) throws Exception;
	
	public void updateImageExpert(long id , MultipartFile file) throws IOException;
}
