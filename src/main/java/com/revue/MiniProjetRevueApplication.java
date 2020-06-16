package com.revue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.revue.repositories.ArticleAuteurAssoRepository;
import com.revue.repositories.ArticleRepository;
import com.revue.repositories.AuteurRepository;
import com.revue.services.AccountService;
import com.revue.services.ArticleService;

@SpringBootApplication
@ComponentScan(basePackages = { "com.revue" })
public class MiniProjetRevueApplication implements CommandLineRunner {
	@Autowired
	ArticleRepository articleRepo;
	@Autowired
	AuteurRepository auteurRepo;
	@Autowired
	ArticleAuteurAssoRepository articleAuteurAssoRepo;
	@Autowired 
	ArticleService service;
	@Autowired 
	AccountService accountService;
	

 
	public static void main(String[] args) {
		
		SpringApplication.run(MiniProjetRevueApplication.class, args);
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
	}
	@Override
	public void run(String... args) throws Exception {
		/*accountService.saveRole(new AppRole(null,Roles.AUTEUR));
		accountService.saveRole(new AppRole(null,Roles.MEMBRE_COMITE_EDITORIAL));
		accountService.saveRole(new AppRole(null,Roles.REFEREE));
		accountService.saveRole(new AppRole(null,Roles.UTILISATEUR));
		accountService.saveRole(new AppRole(null,Roles.ADMIN));
		accountService.saveUser(new AppUser(null,"user","1234", null));
		accountService.saveUser(new AppUser(null,"admin","1234", null));
		accountService.addRoleToUser("user", Roles.UTILISATEUR);
		accountService.addRoleToUser("admin", Roles.UTILISATEUR);
		accountService.addRoleToUser("admin", Roles.ADMIN);
		accountService.saveRole(new AppRole(null,"USER"));
		accountService.saveUser(new AppUser(null,"user","1234", null));
		accountService.addRoleToUser("user", "USER");
		Article aa = new Article("resume1", "mot cle1", "contenu1", EtatArticle.POSTED);
		articleRepo.save(aa);
		
		Auteur auteur = new Auteur("ilyass", "ejjaouchi", "ensa agadir");
		Auteur a = auteurRepo.save(auteur);
		Article ar = articleRepo.getPostedArticles().get(0);
		ArticleAuteurAsso ass = new ArticleAuteurAsso(ar, a, true);
		
		Auteur auteur1 = new Auteur("ayoub", "ejjaouchi", "ensa agadir");
		Auteur a1 = auteurRepo.save(auteur1);
		Article ar1 = articleRepo.getPostedArticles().get(0);
		ArticleAuteurAsso ass1 = new ArticleAuteurAsso(ar1, a1, false);
		
		Auteur auteur2 = new Auteur("aya", "ejjaouchi", "ensa agadir");
		Auteur a2 = auteurRepo.save(auteur2);
		Article ar2 = articleRepo.getPostedArticles().get(0);
		ArticleAuteurAsso ass2 = new ArticleAuteurAsso(ar2, a2, false);
		
		articleAuteurAssoRepo.save(ass);
		articleAuteurAssoRepo.save(ass1);
		articleAuteurAssoRepo.save(ass2);
		
		Article ar2 = articleRepo.getPostedArticles().get(0);
		List<ArticleAuteurAsso> auteurs = articleAuteurAssoRepo.findByArticle(ar2);
		for (ArticleAuteurAsso aut : auteurs) {
			if (aut.isAuteurCorrespondant()) {
				System.out.println("auteur-correpondant" + aut.getAuteur().getNom() +  aut.getAuteur().getPrenom());
			}else {
			System.out.println("co-auteurs" + aut.getAuteur().getNom() +  aut.getAuteur().getPrenom());
		}
	}*/
		
		}

}
