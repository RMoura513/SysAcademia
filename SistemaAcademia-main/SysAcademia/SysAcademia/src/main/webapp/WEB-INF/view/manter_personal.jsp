<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/m_Personal.css"/>'>
<title>Gerenciamento de Funcionarios</title>
</head>
<body>
      <H1 class = "teste" align="center">Olá, Administrador</H1>
      <div  align="center">
      <nav id="menu_administrador">
           <ul class ="nave">       
               <li><a href="manter_atendente">Atendentes</a> 
               <li><a href="manter_personal">Personal</a>   
               <li><a href="index">Log Out</a>
           </ul>
      </nav>
      </div>
      <br>
      <br>
      <br>
           <div>
           
           <table>
                  <form action="manter_personal" method="post">
                        <input type="text" id="nome" name="nome" placeholder="Nome"
                        value='<c:out value="${per.nome }"></c:out>'>
                        
                        <input type="date" id="dataNasc" name="dataNasc" placeholder="Data de Nascimento"
                        value='<c:out value="${per.dataNasc }"></c:out>'>
                        
                        <input type="text" id="cep" name="cep" placeholder="CEP"
                        value='<c:out value="${per.cep }"></c:out>'>
                        
                        <input type="text" id="logradouro" name="logradouro" placeholder="Logradouro"
                        value='<c:out value="${per.logradouro }"></c:out>'>
                        
                        <input type="number" id="numero" name="numero" placeholder="Numero endereço"
                        value='<c:out value="${per.numero_end }"></c:out>'>
                        
                        <input type="text" id="usuario" name="usuario" placeholder="Usuario"
                        value='<c:out value="${per.usuario }"></c:out>'>
                        
                        <input type="text" id="senha" name="senha" placeholder="Senha"
                        value='<c:out value="${per.senha }"></c:out>'>
                        <br>
                        <br>
                        
                        <input type="text" id="formacao" name="formacao" placeholder="Formação"
                        value='<c:out value="${per.formacao }"></c:out>'>
                        
                        <input type="text" id="tipoFormacao" name="tipoFormacao" placeholder="Tipo de Formação"
                        value='<c:out value="${per.tipoFormacao }"></c:out>'>
                       
                        
                        <input type="submit" id="botao" name="botao" value ="Inserir">                      
                        <input type="submit" id="botao" name="botao" value ="Listar">
                        <br> 
                        <br>
                        <input type="number" id="id" name="id" placeholder="Informe o Codigo"
                        value='<c:out value="${per.id }"></c:out>'>
                        <input type="submit" id="botao" name="botao" value ="Atualizar">
                        <input type="submit" id="botao" name="botao" value ="Excluir">
                        <input type="submit" id="botao" name="botao" value ="Buscar">
                  </form>
           </table>
      </div>
      
      
 
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
	  <div class="teste" class="table" align="center">
		<c:if test="${not empty lista_personal }">
			<table border="1">
				<thead>
					<tr>
						<th>ID</th>
					    <th>Nome do Personal</th>
					    <th>Data de Nascimento</th>
						<th>CEP</th>					    
						<th>Logradouro</th>
						<th>Numero</th>
						<th>Usuario</th>
						<th>Senha</th>
						<th>Formacao</th>
						<th>Tipo de Formacao</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista_personal }" var="lp">
						<tr>
							<td><c:out value="${lp.id }"></c:out></td>
                            <td><c:out value="${lp.nome }"></c:out></td>
                            <td><c:out value="${lp.dataNasc }"></c:out></td>
                            <td><c:out value="${lp.cep }"></c:out></td>
                            <td><c:out value="${lp.logradouro }"></c:out></td>
                            <td><c:out value="${lp.numero_end }"></c:out></td>
                            <td><c:out value="${lp.usuario }"></c:out></td>
                            <td><c:out value="${lp.senha }"></c:out></td>
                            <td><c:out value="${lp.formacao }"></c:out></td>
                            <td><c:out value="${lp.tipoFormacao }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	 </div>	  
	 
	 <br>
	 

</body>
</html>