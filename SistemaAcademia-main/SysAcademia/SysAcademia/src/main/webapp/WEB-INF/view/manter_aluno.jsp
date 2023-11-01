<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/m_Aluno.css"/>'>
<title>Gerenciamento de Alunos</title>
</head>
<body>
      <H1 class = "teste" align="center">Olá, Atendente</H1>
      <div align="center">
      <nav id="menu_atendente">
           <ul class="nave">
               <li><a href="manter_aluno">Alunos</a>               
               <li><a href="manter_mensalidade">Mensalidades</a>
               <li><a href="index">Log Out</a>
           </ul>
      </nav>
      </div>
      <br>
      <br>
      <br>
     <form action="manter_aluno" method="post">
     <div>
           <table>
                   <tr>
                        <td><input type="text" id="nome" name="nome" placeholder="Nome"
                        value='<c:out value="${alu.nome }"></c:out>'></td>
                        
                        <td><input type="text" id="cpf" name="cpf" placeholder="CPF"
                        value='<c:out value="${alu.cpf }"></c:out>'></td>                       
                        
                        <td><input type="text" id="cep" name="cep" placeholder="CEP"
                        value='<c:out value="${alu.cep }"></c:out>'></td>
                        
                        <td><input type="date" id="dataNasc" name="dataNasc" placeholder="Data de Nascimento"
                        value='<c:out value="${alu.dataNasc }"></c:out>'></td>
                   </tr>  
                   
                   <tr>   
                        <td><input type="text" id="logradouro" name="logradouro" placeholder="Logradouro"
                        value='<c:out value="${alu.logradouro }"></c:out>'></td>
                        
                        <td><input type="number" id="numero" name="numero" placeholder="Numero endereço"
                        value='<c:out value="${alu.numero_end }"></c:out>'></td>
                   </tr>
                   <tr>     
                        <td><input type="text" id="usuario" name="usuario" placeholder="Usuario"
                        value='<c:out value="${alu.usuario }"></c:out>'></td>
                        
                        <td><input type="text" id="senha" name="senha" placeholder="Senha"
                        value='<c:out value="${alu.senha }"></c:out>'></td>
                  </tr>
                  
                  <tr>                          
                        <td>
                            <input type="number" id="id" name="id" placeholder="Informe o Codigo"
                             value='<c:out value="${alu.id }"></c:out>'>
                        </td>
                        <td>
                            <input type="submit" id="botao" name="botao" value ="Buscar">
                            <input type="submit" id="botao" name="botao" value ="Listar">
                        </td>
                 </tr>
                 
                  <tr>
                      <td>                    
                        <input type="submit" id="botao" name="botao" value ="Inserir">                   
                        <input type="submit" id="botao" name="botao" value ="Atualizar">
                        <input type="submit" id="botao" name="botao" value ="Excluir">    
                      </td>
                  </tr>
           </table>
      </div>
      </form>
 
	  <div class="teste" align="center">
		<c:if test="${not empty erro }">
			<H2><c:out value="${erro }" /></H2>
		</c:if>  
	  </div> 
	   
	  <div class="teste" align="center">
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" /></H2>
		</c:if>  
	  </div> 	  
	    <br>
	    <br>
	  <div class="table" align="center">
		<c:if test="${not empty lista_alunos }">
			<table border="1">
				<thead>
					<tr>
						<th>ID</th>
					    <th>Nome do aluno</th>
					    <th>Data de Nascimento</th>
					    <th>CPF</th>
						<th>CEP</th>					    
						<th>Logradouro</th>
						<th>Numero</th>
						<th>Usuario</th>
						<th>Senha</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista_alunos }" var="la">
						<tr>
							<td><c:out value="${la.id }"></c:out></td>
                            <td><c:out value="${la.nome }"></c:out></td>
                            <td><c:out value="${la.dataNasc }"></c:out></td>
                            <td><c:out value="${la.cpf }"></c:out></td>
                            <td><c:out value="${la.cep }"></c:out></td>
                            <td><c:out value="${la.logradouro }"></c:out></td>
                            <td><c:out value="${la.numero_end }"></c:out></td>
                            <td><c:out value="${la.usuario }"></c:out></td>
                            <td><c:out value="${la.senha }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	 </div>	  
	 
	 <br>
	 

</body>
</html>