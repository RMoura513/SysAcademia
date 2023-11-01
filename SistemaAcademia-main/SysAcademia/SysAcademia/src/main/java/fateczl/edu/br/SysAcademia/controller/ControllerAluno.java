package fateczl.edu.br.SysAcademia.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fateczl.edu.br.SysAcademia.model.Ficha;
import fateczl.edu.br.SysAcademia.model.Mensalidade;
import fateczl.edu.br.SysAcademia.persistence.Consultas_AlunoDAO;

@Controller
public class ControllerAluno {
	
	@Autowired
	Consultas_AlunoDAO caDAO;
	
	
	@RequestMapping(name = "aluno", value = "/aluno", method = RequestMethod.GET)
	public ModelAndView init_aluno(ModelMap model) {
		
		return new ModelAndView("aluno");
		
	}
	
	@RequestMapping(name = "consultar_ficha", value = "/consultar_ficha", method = RequestMethod.GET)
	public ModelAndView init_ficha(ModelMap model) {
		
		return new ModelAndView("consultar_ficha");		
	}
	
	@RequestMapping(name = "consultar_mensalidade", value = "/consultar_mensalidade", method = RequestMethod.GET)
	public ModelAndView init_mensalidade(ModelMap model) {
		
		return new ModelAndView("consultar_mensalidade");		
	}
	
	@RequestMapping(name = "consultar_ficha", value = "/consultar_ficha", method = RequestMethod.POST)
	public ModelAndView consultar_ficha(ModelMap model, @RequestParam Map<String, String> param) {
		
		String id_aluno = param.get("id_aluno");
		String botao = param.get("botao");
		
		String saida = "";
		String erro = "";
		
		List<Ficha> fichas = new ArrayList<>();
		
        if(id_aluno == "") {
        	id_aluno = "0";
        }
        
        System.out.println(id_aluno);
        try {
        if (botao.equalsIgnoreCase("pesquisar")) {	
        	
			if (id_aluno == "" || id_aluno == "0" || Integer.parseInt(id_aluno) == 0) {
				saida = "Codigo precisa ser preenchido para pesquisar";
				
			} else if (id_aluno != "" || id_aluno != "0" || Integer.parseInt(id_aluno) != 0) {	
				   int id = Integer.parseInt(id_aluno);
				   fichas = caDAO.consultarFichaDeTreino(id);
				
				   if (fichas.isEmpty()) {
					  saida = "Voce Não Possui Fichas de Treino/Codigo Incorreto";
					
				   }			   
		     }
        }

        } catch(SQLException | ClassNotFoundException e) {
        	erro = e.getMessage();
        } finally {
        	model.addAttribute("saida", saida);
        	model.addAttribute("erro", erro);
        	model.addAttribute("fichas", fichas);
        }
		
		
		return new ModelAndView("consultar_ficha");		
	}
	
	@RequestMapping(name = "consultar_mensalidade", value = "/consultar_mensalidade", method = RequestMethod.POST)
	public ModelAndView consultar_mensalidade(ModelMap model, @RequestParam Map<String, String> param) {
		
		String id_aluno = param.get("id_aluno");
		String botao = param.get("botao");
		
		String saida = "";
		String erro = "";
		
		List<Mensalidade> mensalidades = new ArrayList<>();
		
		if(id_aluno ==  "") {
			id_aluno = "0";
		}
        System.out.println(id_aluno);		
		try {	
			
		if(botao.equalsIgnoreCase("pesquisar")) {
			
			if (id_aluno == "" || id_aluno == "0" || Integer.parseInt(id_aluno) == 0) {
				saida = "Codigo precisa ser preenchido para pesquisar";
				
			} else if (id_aluno != "" || id_aluno != "0" || Integer.parseInt(id_aluno) != 0) {	
				   int id = Integer.parseInt(id_aluno);
				   mensalidades = caDAO.consultarMensalidade(id);
				
				   if (mensalidades.isEmpty()) {
					  saida = "Voce Não Possui Mensalidades/Codigo Incorreto";
					
				   }			   
		     }
		}
			
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
		  model.addAttribute("erro", erro);
		  model.addAttribute("saida", saida);
		  model.addAttribute("mensalidades", mensalidades);
		}
		
		return new ModelAndView("consultar_mensalidade");		
	}
	
}
