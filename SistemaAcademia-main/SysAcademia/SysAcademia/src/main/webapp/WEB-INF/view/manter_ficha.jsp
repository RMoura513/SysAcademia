<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/m_Ficha.css"/>'>
<title>Gerenciamento de Fichas</title>
</head>
<body>
      <H1 class ="teste" align="center">Olá, Personal</H1>
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

           <form action="manter_ficha" method="post">
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
                                                 
                        <input type="text" id="descricao" name="descricao" placeholder="Descrição"
                        value='<c:out value="${fic.descricao }"></c:out>'>
                       
                        <input type="submit" id="botao" name="botao" value ="Inserir">                      
                        <input type="submit" id="botao" name="botao" value ="Listar"> 
                        <br>
                        <br>
                        <br>
                        <input type="number" min="0" id="id_ficha" name="id_ficha" placeholder="Informe o Codigo"
                        value='<c:out value="${fic.id_ficha }"></c:out>'>
                        
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
		<c:if test="${not empty lista_fichas }">
			<table border="1">
				<thead>
					<tr>
					    <th>ID</th>
						<th>Nome do Personal</th>
						<th>Nome do Aluno</th>
					    <th>Descricao</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista_fichas }" var="lf">
						<tr>
						    <td><c:out value="${lf.id_ficha }"></c:out></td>
							<td><c:out value="${lf.nome_personal }"></c:out></td>
                            <td><c:out value="${lf.nome_aluno }"></c:out></td>
                            <td><c:out value="${lf.descricao }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	 </div>	  
	 
	 <br>
      
      
</body>
</html>