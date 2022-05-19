package com.projet.BackendPfe.services;


import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface InterfaceIAModel {

	
	public byte[] getFile(long  id) throws Exception;
	void addFile(MultipartFile file, long id) throws IOException;
	public void deleteIAModel(long id );
}
