<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/m_Atendente.css"/>'>
<title>Gerenciamento de Funcionarios</title>
</head>
<body>
     <H1 class="teste" align="center">Olá, Administrador</H1>
      <nav id="menu_administrador" align="center">
           <ul class= "nave">
               <li><a href="manter_atendente">Atendentes</a> 
               <li><a href="manter_personal">Personal</a>   
               <li><a href="index">Log Out</a> 
           </ul>
      </nav>
      <br>
      <br>
      <br>
      
      <div>
           <table>
                  <form action="manter_atendente" method="post">
                        <input type="text" id="nome" name="nome" placeholder="Nome do Personal"
                        value='<c:out value="${atend.nome }"></c:out>'>
                        
                        <input type="date" id="dataNasc" name="dataNasc" placeholder="Data de Nascimento"
                        value='<c:out value="${atend.dataNasc }"></c:out>'>
                        
                        <input type="text" id="cep" name="cep" placeholder="CEP"
                        value='<c:out value="${atend.cep }"></c:out>'>
                        
                        <input type="text" id="logradouro" name="logradouro" placeholder="Logradouro"
                        value='<c:out value="${atend.logradouro }"></c:out>'>
                        
                        <input type="number" id="numero" name="numero" placeholder="Numero endereço"
                        value='<c:out value="${atend.numero_end }"></c:out>'>
                        
                        <input type="text" id="usuario" name="usuario" placeholder="Usuario"
                        value='<c:out value="${atend.usuario }"></c:out>'>
                        
                        <input type="text" id="senha" name="senha" placeholder="Senha"
                        value='<c:out value="${atend.senha }"></c:out>'>
                       
                        
                        <input type="submit" id="botao" name="botao" value ="Inserir">                      
                        <input type="submit" id="botao" name="botao" value ="Listar"> 
                        <br>
                        <br>
                        <br>
                        <input type="number" id="id" name="id" placeholder="Informe o codigo"
                        value='<c:out value="${atend.id }"></c:out>'>
                        <input type="submit" id="botao" name="botao" value ="Atualizar">
                        <input type="submit" id="botao" name="botao" value ="Excluir">
                        <input type="submit" id="botao" name="botao" value ="Buscar">
                  </form>
           </table>
      </div>
      <br>
      <br>
      <br>
 
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
	    
	  <div class="teste" align="center">
		<c:if test="${not empty lista_atendentes }">
			<table border="1">
				<thead>
					<tr>
						<th>ID</th>
					    <th>Nome do Atendente</th>
					    <th>Data de Nascimento</th>
						<th>CEP</th>					    
						<th>Logradouro</th>
						<th>Numero</th>
						<th>Usuario</th>
						<th>Senha</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista_atendentes }" var="la">
						<tr>
							<td><c:out value="${la.id }"></c:out></td>
                            <td><c:out value="${la.nome }"></c:out></td>
                            <td><c:out value="${la.dataNasc }"></c:out></td>
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