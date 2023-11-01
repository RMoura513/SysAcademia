package fateczl.edu.br.SysAcademia.controller;

import java.sql.SQLException;
import java.time.LocalTime;
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

import fateczl.edu.br.SysAcademia.model.Aluno;
import fateczl.edu.br.SysAcademia.model.Aula;
import fateczl.edu.br.SysAcademia.model.Ficha;
import fateczl.edu.br.SysAcademia.model.Personal;
import fateczl.edu.br.SysAcademia.persistence.ComboBoxDAO;
import fateczl.edu.br.SysAcademia.persistence.Personal_AulaDAO;
import fateczl.edu.br.SysAcademia.persistence.Personal_FichaDAO;

@Controller
public class ControllerPersonal {
	
	@Autowired
	Personal_FichaDAO pFichaDAO;
	
	@Autowired
	Personal_AulaDAO pAulaDAO;
	
	@Autowired
	ComboBoxDAO cDAO;
	
	@RequestMapping(name = "personal", value = "/personal", method = RequestMethod.GET)
	public ModelAndView init_personal(ModelMap model) {
		
		return new ModelAndView("personal");
		
	}
	
	@RequestMapping(name = "manter_ficha", value = "/manter_ficha", method = RequestMethod.GET)
	public ModelAndView manter_ficha(ModelMap model) {
		List<Aluno> alunos = new ArrayList<>();
		List<Personal> personais = new ArrayList<>();
		String erro = "";
		try {
			personais = cDAO.combobox_Personal();
			alunos = cDAO.combobox_Alunos();
			
			
		} catch (SQLException | ClassNotFoundException e ) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("personais", personais);
			model.addAttribute("alunos", alunos);
			model.addAttribute("erro", erro);
		}		
		return new ModelAndView("manter_ficha");
		
	}
	
	@RequestMapping(name = "manter_aula", value = "/manter_aula", method = RequestMethod.GET)
	public ModelAndView manter_aula(ModelMap model) {
		List<Aluno> alunos = new ArrayList<>();
		List<Personal> personais = new ArrayList<>();
		String erro = "";
		try {
			personais = cDAO.combobox_Personal();
			alunos = cDAO.combobox_Alunos();
			
			
		} catch (SQLException | ClassNotFoundException e ) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("personais", personais);
			model.addAttribute("alunos", alunos);
			model.addAttribute("erro", erro);
		}		
		return new ModelAndView("manter_aula");
		
	}
	
	@RequestMapping(name = "manter_ficha", value = "/manter_ficha", method = RequestMethod.POST)
	public ModelAndView manter_ficha(ModelMap model, @RequestParam Map<String, String> param) {
		
		manter_ficha(model);
		
		List<Ficha> lista_fichas = new ArrayList<>();
		String id_ficha = param.get("id_ficha");
		String nome_personal = param.get("nome_personal");
		String nome_aluno = param.get("nome_aluno");
		String descricao = param.get("descricao");
		
		String botao = param.get("botao");
		
		String erro = "";
		String saida = "";
		
		Ficha fic = new Ficha();
		
		System.out.println(id_ficha);
		if(id_ficha == "") {
			id_ficha = "0";
		}
		
	try {
		
		if(botao.equalsIgnoreCase("inserir")) {
				
			fic.setNome_personal(nome_personal);
			fic.setNome_aluno(nome_aluno);
			fic.setDescricao(descricao);
			
			saida = pFichaDAO.cadastrar(fic);
		}
		
		if(botao.equalsIgnoreCase("listar")) {
			lista_fichas = pFichaDAO.lista();
		}
		
		if(botao.equalsIgnoreCase("atualizar")) {
			
			fic.setId_ficha(Integer.parseInt(id_ficha));
			fic.setDescricao(descricao);			
			
			saida = pFichaDAO.atualizar(fic);
		}
		
		if(botao.equalsIgnoreCase("excluir")) {
			
			fic.setId_ficha(Integer.parseInt(id_ficha));
			System.out.println(fic.getId_ficha());
			saida = pFichaDAO.excluir(fic);
			
			
		}
		
		if (botao.equalsIgnoreCase("buscar")) {
			
			if(id_ficha == "" || id_ficha == "0" || Integer.parseInt(id_ficha) == 0) {
				saida = "Codigo necessario para pesquisar";
				
			} else if (id_ficha != "" || id_ficha !="0" || Integer.parseInt(id_ficha) != 0) {
			    int ID = Integer.parseInt(id_ficha);   
			    fic = pFichaDAO.buscar(ID);
			    System.out.println(fic);
			    
			    if (fic.getId_ficha() == 0 && fic.getNome_aluno() == null && fic.getNome_personal() == null && fic.getDescricao() == null) {
			    	saida = "Ficha não encontrada";
				}			    
			}	
		}		
	} catch(SQLException | ClassNotFoundException e) {
		erro = e.getMessage();
	} finally {
		model.addAttribute("erro", erro);
		model.addAttribute("saida", saida);
		model.addAttribute("fic", fic);
		model.addAttribute("lista_fichas", lista_fichas);
		
		
	}
	
	return new ModelAndView("manter_ficha");
	
}
	
	@RequestMapping(name = "manter_aula", value = "/manter_aula", method = RequestMethod.POST)
	public ModelAndView manter_aula(ModelMap model, @RequestParam Map<String, String> param) {
		
		manter_aula(model);
		
		List<Aula> lista_aulas = new ArrayList<>();
		String id = param.get("id");
		String nome_personal = param.get("nome_personal");
		String nome_aluno = param.get("nome_aluno");
		String nome = param.get("nome");
		String duracao = param.get("duracao");
		
		String botao = param.get("botao");
		
		String erro = "";
		String saida = "";
		
				
		Aula al = new Aula();
		
		if(id == "") {
			id = "0";
		}
		
		if(duracao == "") {
			duracao = "00:00";
		}
		
		
		try {
			
			LocalTime tempo = LocalTime.parse(duracao);
			System.out.println(tempo);
			
			if(botao.equalsIgnoreCase("inserir")) {
	
				
				al.setId(Integer.parseInt(id));
				al.setNome_personal(nome_personal);
				al.setNome_aluno(nome_aluno);
				al.setNome(nome);
				al.setDuracao(tempo);
				
				saida = pAulaDAO.cadastrar(al);
			}
			
			if(botao.equalsIgnoreCase("listar")) {
				lista_aulas = pAulaDAO.lista();
				System.out.println(lista_aulas);
				
				if(lista_aulas.isEmpty()) {
					saida = "Não há aulas cadastradas no momento";
				}
			}
			
			if(botao.equalsIgnoreCase("atualizar")) {
				
				
				al.setId(Integer.parseInt(id));
				al.setNome(nome);
				al.setDuracao(tempo);		
				
				saida = pAulaDAO.atualizar(al);
			}
			
			if(botao.equalsIgnoreCase("excluir")) {
				
				al.setId(Integer.parseInt(id));
				
				saida = pAulaDAO.excluir(al);
			}
			
			if (botao.equalsIgnoreCase("buscar")) {
				
				if(id == "" || id =="0" || Integer.parseInt(id) == 0) {
					saida = "Codigo necessario para pesquisar";
				} else  if (id != "" || id != "0" || Integer.parseInt(id) != 0){
				
			    int ID = Integer.parseInt(id);
			    
			    al = pAulaDAO.buscar(ID);
			    System.out.println(al);
			          if(al.getId() == 0 && al.getNome_aluno() == null && al.getNome_personal() == null && al.getNome() == null && al.getDuracao() == null) {
			    	saida = "Aula não encontrada";
			          }
			    }
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("a", al);
			model.addAttribute("lista_aulas", lista_aulas);
			
			
		}
		
		return new ModelAndView("manter_aula");
		
	}	

}
