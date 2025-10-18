package br.com.adotaflix.server.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUtil {
	
	@Value("${images.dir}")
	private String imagesDir;
	
	public List<String> getImagens(String subpath, Long id){
		File pasta = new File(imagesDir + subpath + "/" + id);
        List<String> urls = new ArrayList<>(); 
        if(!pasta.exists()) {
        	return urls;
        }
        for (File arquivo : Objects.requireNonNull(pasta.listFiles())) {
            if (arquivo.isFile()) {
                urls.add("/imagens" + subpath + "/"+ id + "/" + arquivo.getName());
            }
        }
        return urls;
	}
	
	public String getImagem(String subpath, Long id){
		File pasta = new File(imagesDir + subpath + "/" + id);
		if(!pasta.exists()) {
			return "";
		}
        for (File arquivo : Objects.requireNonNull(pasta.listFiles())) {
            if (arquivo.isFile()) {
                return "/imagens" + subpath + "/"+ id + "/" + arquivo.getName();
            }
        }
        return "";
	}
}
