package fateczl.edu.br.SysAcademia.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fateczl.edu.br.SysAcademia.persistence.FazerLogin;

@Controller
public class ControllerLogin {
	
	@Autowired
	FazerLogin fl;
	
	@RequestMapping(name = "index", value = "/index", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		
		return new ModelAndView("index");
		
	}
	
	@RequestMapping(name = "index", value = "/index", method = RequestMethod.POST)
	public ModelAndView index(ModelMap model, @RequestParam Map<String, String> param) {
		
		String botao = param.get("botao");
		String usuario = param.get("usuario");
		String senha = param.get("senha");
		
		String erro = "";
		String acesso = "";
		
		System.out.println(usuario);
		System.out.println(senha);
		
		try {
			
			if(botao.equalsIgnoreCase("entrar")) {	
			acesso = fl.fazerLogin(usuario, senha);
			
			System.out.println(usuario);
			System.out.println(senha);
			System.out.println(acesso);
			
			}
			
		} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("acesso", acesso);
		}
		
		return new ModelAndView("index");
		
	}	
}