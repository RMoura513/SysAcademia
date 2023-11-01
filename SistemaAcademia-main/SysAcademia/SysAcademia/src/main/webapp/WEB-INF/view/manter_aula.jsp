<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/m_Aula.css"/>'>
<title>Gerencimento de Aulas</title>
</head>
<body>
     <H1 class = "teste" align="center"> Olá, Personal</H1>
      <nav id="menu_personal" align="center">
      
           <ul class="nave">
               <li><a href="manter_ficha">Fichas</a>               
               <li><a href="manter_aula">Aulas</a>
               <li><a href="index">Log Out</a>
           </ul>
      </nav>
      <br>
      <br>
      <br>
     <div>
           <table>
                  <form action="manter_aula" method="post">
                           <select id="nome_personal" name="nome_personal">
                            <option value="0">Selecione o Personal</option>
                            <c:if test="${not empty personais }">
                                <c:forEach items="${personais }" var="p">
                                          <option><c:out value="${p.nome }"></c:out></option>
                                </c:forEach>
                            </c:if>           
                           </select>
                           
                           <select id="nome_aluno" name="nome_aluno">
                            <option value="0">Selecione um Aluno</option>
                            <c:if test="${not empty alunos }">
                                <c:forEach items="${alunos }" var="a">
                                          <option><c:out value="${a.nome }"></c:out></option>
                                </c:forEach>
                            </c:if>           
                           </select>
                        
                        <input type="text" id="nome" name="nome" placeholder="Nome da Aula"
                        value='<c:out value="${a.nome }"></c:out>'>
                        
                        <input type="time" id="duracao" name="duracao" placeholder="Duração da Aula"
                        value='<c:out value="${a.duracao }"></c:out>'>
                        
                        <input type="submit" id="botao" name="botao" value ="Inserir">                      
                        <input type="submit" id="botao" name="botao" value ="Listar"> 
                        <br>
                        <br>
                        <br>
                        <input type="number" id="id" name="id" placeholder="Informe o Codigo"
                        value='<c:out value="${a.id }"></c:out>'>
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
	    
	  <div class="teste" align="center">
		<c:if test="${not empty lista_aulas }">
			<table border="1">
				<thead>
					<tr>
						<th>ID</th>
					    <th>Nome do Personal</th>
					    <th>Nome do Aluno</th>
						<th>Nome</th>					    
						<th>Duracao</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista_aulas }" var="la">
						<tr>
							<td><c:out value="${la.id }"></c:out></td>
                            <td><c:out value="${la.nome_personal }"></c:out></td>
                            <td><c:out value="${la.nome_aluno }"></c:out></td>
                            <td><c:out value="${la.nome }"></c:out></td>
                            <td><c:out value="${la.duracao }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	 </div>	  
	 
	 <br>
	 

</body>
</html>