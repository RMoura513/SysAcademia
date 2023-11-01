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

import fateczl.edu.br.SysAcademia.model.Aluno;
import fateczl.edu.br.SysAcademia.model.Mensalidade;
import fateczl.edu.br.SysAcademia.persistence.Atendente_AlunoDAO;
import fateczl.edu.br.SysAcademia.persistence.Atendente_MensalidadeDAO;
import fateczl.edu.br.SysAcademia.persistence.ComboBoxDAO;
import fateczl.edu.br.SysAcademia.persistence.Excluir_AssocitivasDAO;

@Controller
public class ControllerAtendente {
	
	
    @Autowired
	Atendente_AlunoDAO aaDAO;
    
    @Autowired
    Atendente_MensalidadeDAO amDAO;
    
    @Autowired
    ComboBoxDAO cDAO;
    
	@Autowired
	Excluir_AssocitivasDAO eaDAO;
    

	@RequestMapping(name = "atendente", value = "/atendente", method = RequestMethod.GET)
	public ModelAndView init_atendente(ModelMap model) {
		
		return new ModelAndView("atendente");
		
	}	
	
	@RequestMapping(name = "manter_aluno", value = "/manter_aluno", method = RequestMethod.GET)
	public ModelAndView manter_aluno(ModelMap model) {
		
		return new ModelAndView("manter_aluno");		
	}
	
	@RequestMapping(name = "manter_mensalidade", value = "/manter_mensalidade", method = RequestMethod.GET)
	public ModelAndView manter_mensalidade(ModelMap model) {
		List<Aluno> alunos = new ArrayList<>();
		String erro = "";
		try {
			alunos = cDAO.combobox_Alunos();
			
		} catch (SQLException | ClassNotFoundException e ) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("alunos", alunos);
			model.addAttribute("erro", erro);
		}
		
		return new ModelAndView("manter_mensalidade");
		
	}
	
	@RequestMapping(name = "manter_aluno", value = "/manter_aluno", method = RequestMethod.POST)
	public ModelAndView manter_aluno(ModelMap model, @RequestParam Map<String, String> param) {
		
		List<Aluno> lista_alunos = new ArrayList<>();
		String id = param.get("id");
		String nome = param.get("nome");
		String dataNasc = param.get("dataNasc");
		String cpf = param.get("cpf");
		String cep = param.get("cep");
		String logradouro = param.get("logradouro");
		String numero = param.get("numero");
		String usuario = param.get("usuario");
		String senha = param.get("senha");
		
		String botao = param.get("botao");
		
		String erro = "";
		String saida = "";
		
				
		Aluno alu =  new Aluno();
		
		System.out.println(id);
		System.out.println(nome);
		System.out.println(dataNasc);
		System.out.println(cpf);
		System.out.println(cep);
		System.out.println(logradouro);
		System.out.println(numero);
		System.out.println(usuario);
		System.out.println(senha);
		
		try {
			
			
			if(botao.equalsIgnoreCase("inserir")) {
				if(nome == "" ||dataNasc == ""|| cpf == ""|| cep == ""|| logradouro == ""|| numero == ""|| usuario == ""|| senha == "")	{
					saida = "Todos os campos sao obrigatorios para Cadastro"; 
				} else {
				LocalDate data = (LocalDate.parse(dataNasc));			
				System.out.println(dataNasc);
				
				
				alu.setNome(nome);
				alu.setDataNasc(data);
				alu.setCpf(cpf);
				alu.setCep(cep);
				alu.setLogradouro(logradouro);
				alu.setNumero_end(Integer.parseInt(numero));
				alu.setUsuario(usuario);
				alu.setSenha(senha);	
				
				saida = aaDAO.cadastrar(alu);
			}
			}
			
			if(botao.equalsIgnoreCase("listar")) {
				lista_alunos = aaDAO.lista();
				if (lista_alunos.isEmpty()) {
					saida = "Não há alunos cadastrados";
				}
			}
			
			if(botao.equalsIgnoreCase("atualizar")) {
				if(id == "" || dataNasc == ""|| cpf == ""|| cep == ""|| logradouro == ""|| numero == ""|| usuario == ""|| senha == "")	{
					saida = "Campos precisam ser preenchidos"; 
				} else {
				LocalDate data = (LocalDate.parse(dataNasc));
				System.out.println(dataNasc);
				
				
				alu.setId(Integer.parseInt(id));
				alu.setNome(nome);
				alu.setDataNasc(data);
				alu.setCpf(cpf);
				alu.setCep(cep);
				alu.setLogradouro(logradouro);
				alu.setNumero_end(Integer.parseInt(numero));
				alu.setUsuario(usuario);
				alu.setSenha(senha);
				
				saida = aaDAO.atualizar(alu);
				
				}
				
			}
			
			if(botao.equalsIgnoreCase("excluir")) {
				
				System.out.println(id);
				if(id == "" || id == "0" ||  Integer.parseInt(id) == 0) {
					saida = "Codigo Necessario para exclusão";
					
				} else {
				    int ID = Integer.parseInt(id);	
				
				    eaDAO.excluir_aluno_aula(ID);
				    eaDAO.excluir_aluno_ficha(ID);
				    eaDAO.excluir_aluno_mensalidade(ID);
				    alu.setId(ID);
				    saida = aaDAO.excluir(alu);
				
				}
			}
			
			if (botao.equalsIgnoreCase("buscar")) {
				
			    if(id == "0" || id == "" || Integer.parseInt(id) == 0) {
			    	saida = "Codigo necessario para Pesquisa";
			    } else {
				
			    int ID = Integer.parseInt(id);
			    alu = aaDAO.buscar(ID);
			    }
				
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("alu", alu);
			model.addAttribute("lista_alunos", lista_alunos);
			
			
		}
		
		return new ModelAndView("manter_aluno");
		
    }
	

	
	@RequestMapping(name = "manter_mensalidade", value = "/manter_mensalidade", method = RequestMethod.POST)
	public ModelAndView manter_mensalidade(ModelMap model, @RequestParam Map<String, String> param) {
		
		manter_mensalidade(model);
		
		List<Mensalidade> lista_mensalidades = new ArrayList<>();
		String id = param.get("id_mensalidade");
		String nome_aluno = param.get("nome_aluno");
		String valor = param.get("valor");
		String dataVenc = param.get("dataVenc");
		String descricao = param.get("descricao");
		
		String botao = param.get("botao");
		
		String erro = "";
		String saida = "";
		
				
		Mensalidade men = new Mensalidade();
		
		if(id == "") {
			id = "0";
		}
		
		if(valor == "") {
			valor = "0.0";
		}
		
		if(dataVenc == "") {
			dataVenc = LocalDate.now().toString();
		}
	System.out.println(id + "\n" 
		+ nome_aluno  + "\n" 
		+ valor       + "\n" 
		+ dataVenc    + "\n" 
		+ descricao);
	
	try {
		
		if(botao.equalsIgnoreCase("inserir")) {
			
			LocalDate data = (LocalDate.parse(dataVenc));			
		
			
			System.out.println(dataVenc);
			
			
			men.setValor(Float.parseFloat(valor));
			men.setDataVenc(data);
			men.setDescricao(descricao);
			men.setNome_aluno(nome_aluno);
	
			
			saida = amDAO.cadastrar(men);
		}
		
		if(botao.equalsIgnoreCase("listar")) {
			lista_mensalidades = amDAO.lista();
			
			if(lista_mensalidades.isEmpty()) {
				saida = "Não há mensalidades cadastradas";
			}
		}
		
		if(botao.equalsIgnoreCase("atualizar")) {
			LocalDate data = (LocalDate.parse(dataVenc));
	
		
			
			System.out.println(dataVenc);
			
			
			men.setId_mensalidade(Integer.parseInt(id));
			men.setValor(Float.parseFloat(valor));
			men.setDataVenc(data);
			men.setDescricao(descricao);
		
			
			saida = amDAO.atualizar(men);
		}
		
		if(botao.equalsIgnoreCase("excluir")) {
			
			men.setId_mensalidade(Integer.parseInt(id));
			
			saida = amDAO.excluir(men);
		}
		
		if (botao.equalsIgnoreCase("buscar")) {
			
			if(id == "" || id == "0"|| Integer.parseInt(id) == 0) {
				saida = "Codigo necessario para pesquisa";
				
			} else {
				
			int ID = Integer.parseInt(id);
		    
		    men = amDAO.buscar(ID); 
				
			}			
		}		
	
		
	} catch(SQLException | ClassNotFoundException e) {
		erro = e.getMessage();
	} finally {
		model.addAttribute("erro", erro);
		model.addAttribute("saida", saida);
		model.addAttribute("mens", men);
		model.addAttribute("lista_mensalidades", lista_mensalidades);
		
		
	}
	
	return new ModelAndView("manter_mensalidade");
	
}	
}
