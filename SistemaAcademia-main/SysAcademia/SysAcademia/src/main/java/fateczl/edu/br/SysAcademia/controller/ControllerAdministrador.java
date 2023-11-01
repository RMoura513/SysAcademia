package fateczl.edu.br.SysAcademia.controller;

import java.sql.SQLException;
import java.time.LocalDate;
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

import fateczl.edu.br.SysAcademia.model.Atendente;
import fateczl.edu.br.SysAcademia.model.Personal;
import fateczl.edu.br.SysAcademia.persistence.Administrador_AtendenteDAO;
import fateczl.edu.br.SysAcademia.persistence.Administrador_PersonalDAO;
import fateczl.edu.br.SysAcademia.persistence.Excluir_AssocitivasDAO;

@Controller
public class ControllerAdministrador {
	
	@Autowired
	Administrador_AtendenteDAO andAtenDAO;
	
	@Autowired
	Administrador_PersonalDAO andPersonDAO;	
	
	@Autowired
	Excluir_AssocitivasDAO eaDAO;
	
	@RequestMapping(name = "administrador", value = "/administrador", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		
		return new ModelAndView("administrador");
		
	}
	
	
	@RequestMapping(name = "manter_atendente", value = "/manter_atendente", method = RequestMethod.GET)
	public ModelAndView init_atendente(ModelMap model) {
		
		return new ModelAndView("manter_atendente");
		
	}
	
	@RequestMapping(name = "manter_personal", value = "/manter_personal", method = RequestMethod.GET)
	public ModelAndView init_personal(ModelMap model) {
		
		return new ModelAndView("manter_personal");
	}
	
	@RequestMapping(name = "manter_atendente", value = "/manter_atendente", method = RequestMethod.POST)
	public ModelAndView manter_atendente(ModelMap model, @RequestParam Map<String, String> param) {
		
		List<Atendente> lista_atendentes = new ArrayList<>();
		String id = param.get("id");
		String nome = param.get("nome");
		String dataNasc = param.get("dataNasc");
		String cep = param.get("cep");
		String logradouro = param.get("logradouro");
		String numero = param.get("numero");
		String usuario = param.get("usuario");
		String senha = param.get("senha");
		
		String botao = param.get("botao");
		
		String erro = "";
		String saida = "";
		
			
		
		
		Atendente atend =  new Atendente();
		
		if (id == "") {
			id = "0";
		}
		
		if (dataNasc == "") {
	        
	        dataNasc = LocalDate.now().toString(); 
	        System.out.println(dataNasc);
			
		}
		
		if (cep == "") {
			cep = "0";
		}
		if (numero == "") {
			numero = "0";
		}
		
		
		try {
			
			
			
			if(botao.equalsIgnoreCase("inserir")) {
				
				
				
				LocalDate data = (LocalDate.parse(dataNasc));			
			
				
				System.out.println(dataNasc);
				
				
				atend.setNome(nome);
				atend.setDataNasc(data);
				atend.setCep(cep);
				atend.setLogradouro(logradouro);
				atend.setNumero_end(Integer.parseInt(numero));
				atend.setUsuario(usuario);
				atend.setSenha(senha);	
				
				saida = andAtenDAO.cadastrar(atend);
			}
			
			if(botao.equalsIgnoreCase("listar")) {
				lista_atendentes = andAtenDAO.lista();
				
				if (lista_atendentes.isEmpty()) {
					saida = "Não há Atendente Cadastrados";
				}
			}
			
			if(botao.equalsIgnoreCase("atualizar")) {
				
				if(id == "0" || nome == "" || dataNasc == LocalDate.now().toString() || cep == "" || logradouro == "" || numero == "0" || usuario == "" || senha == "") {
					saida = "Todos os obrigatorios para Atualização";
				} else {
				
				    LocalDate data = (LocalDate.parse(dataNasc));
				
					System.out.println(dataNasc);
					
					
					atend.setId(Integer.parseInt(id));
					atend.setNome(nome);
					atend.setDataNasc(data);
					atend.setCep(cep);
					atend.setLogradouro(logradouro);
					atend.setNumero_end(Integer.parseInt(numero));
					atend.setUsuario(usuario);
					atend.setSenha(senha);				
					
					saida = andAtenDAO.atualizar(atend);
				
				}
			}
			
			if(botao.equalsIgnoreCase("excluir")) {
				
			    if (id == "" || id == "0" || Integer.parseInt(id) == 0) {
			    	saida = "Codigo necessario para Exclusão";
			    } else {
				
				      atend.setId(Integer.parseInt(id));
				
				      saida = andAtenDAO.excluir(atend);
			      }
			}
			
			if (botao.equalsIgnoreCase("buscar")) {
				
				if (id == "" || id == "" || Integer.parseInt(id) == 0) {
					saida = "Codigo obrigatorio para pesquisa";
				} else {
				
			          int ID = Integer.parseInt(id);
			    
			          atend = andAtenDAO.buscar(ID);
			          System.out.println(atend);
			          if (atend.getId() == 0 || atend.getNome() == null || atend.getDataNasc() == null || atend.getCep() == null || atend.getLogradouro() == null || atend.getNumero_end() == 0 || atend.getUsuario() == null || atend.getSenha() == null) {
			        	  saida = "Atendente não encontrado/Codigo Invalido";
			          }
				 }
				
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("atend", atend);
			model.addAttribute("lista_atendentes", lista_atendentes);
			
			
		}
		
		return new ModelAndView("manter_atendente");
		
	}	
	
	@RequestMapping(name = "manter_personal", value = "/manter_personal", method = RequestMethod.POST)
	public ModelAndView manter_personal(ModelMap model, @RequestParam Map<String, String> param) {
		
		List<Personal> lista_personal = new ArrayList<>();
		String id = param.get("id");
		String nome = param.get("nome");
		String dataNasc = param.get("dataNasc");
		String cep = param.get("cep");
		String logradouro = param.get("logradouro");
		String numero = param.get("numero");
		String usuario = param.get("usuario");
		String senha = param.get("senha");
		String formacao = param.get("formacao");
		String tipoFormacao = param.get("tipoFormacao");
		
		String botao = param.get("botao");
		
	
		
		String erro = "";
		String saida = "";
		
				
		Personal per =  new Personal();
		
		if (id == "") {
			id = "0";
		}
		
		if (dataNasc == "") {
	        
	        dataNasc = "" + LocalDate.now() + ""; 
	        System.out.println(dataNasc);
			
		}
		
		if (cep == "") {
			cep = "0";
		}
		if (numero == "") {
			numero = "0";
		}
		
		try {
			
			if(botao.equalsIgnoreCase("inserir")) {
				LocalDate data = (LocalDate.parse(dataNasc));			
			
				
				System.out.println(dataNasc);
				
				
				per.setNome(nome);
				per.setDataNasc(data);
				per.setCep(cep);
				per.setLogradouro(logradouro);
				per.setNumero_end(Integer.parseInt(numero));
				per.setUsuario(usuario);
				per.setSenha(senha);	
				per.setFormacao(formacao);
				per.setTipoFormacao(tipoFormacao);
				
				saida = andPersonDAO.cadastrar(per);
			}
			
			if(botao.equalsIgnoreCase("listar")) {
				lista_personal = andPersonDAO.lista();
				
				if (lista_personal.isEmpty()) {
					saida = "Não há Personais Cadastrados";
				}
			}
			
			if(botao.equalsIgnoreCase("atualizar")) {

				if (id == "0" || nome == "" || dataNasc == LocalDate.now().toString() || cep == "" || logradouro == "" || numero == "0" || usuario == "" || senha == "" || formacao == "" || tipoFormacao == "") {
					saida = "Campos precisam ser preenchidos para atualização";
					
				} else {					
						LocalDate data = (LocalDate.parse(dataNasc));
						
						System.out.println(dataNasc);						
						
						per.setId(Integer.parseInt(id));
						per.setNome(nome);
						per.setDataNasc(data);
						per.setCep(cep);
						per.setLogradouro(logradouro);
						per.setNumero_end(Integer.parseInt(numero));
						per.setUsuario(usuario);
						per.setSenha(senha);
						per.setFormacao(formacao);
						per.setTipoFormacao(tipoFormacao);
						
						saida = andPersonDAO.atualizar(per);
				 }
			}
			
			if(botao.equalsIgnoreCase("excluir")) {
				if (id == "" || id == "0" || Integer.parseInt(id) == 0) {
					saida = "Codigo Necessario Para Exclusão";
				} else {
						int ID = Integer.parseInt(id);
						
						eaDAO.excluir_personal_aula(ID);
						eaDAO.excluir_personal_ficha(ID);
						
						per.setId(ID);
						
						saida = andPersonDAO.excluir(per);
				 }
			}
			
			if (botao.equalsIgnoreCase("buscar")) {
				
				if (id == "" || id == "0" || Integer.parseInt(id) == 0) {
					saida = "Codigo Necessario para Pesquisa";
				} else {
				
			    int ID = Integer.parseInt(id);
			    
			    per = andPersonDAO.buscar(ID);
				if (per.getId() == 0 || per.getNome() == null || per.getDataNasc() == null || per.getFormacao() == null || per.getTipoFormacao() == null || per.getNumero_end() == 0 || per.getLogradouro() == null || per.getUsuario() == null || per.getSenha() == null ) {
					   saida = "Personal não encontrado/Codigo Invalido";	
					}
				}
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("per", per);
			model.addAttribute("lista_personal", lista_personal);
			
			
		}

		
		
		return new ModelAndView("manter_personal");
		
	}	
}
