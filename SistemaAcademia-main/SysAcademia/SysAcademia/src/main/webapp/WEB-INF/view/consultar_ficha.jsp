<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/c_Ficha.css"/>'>
<title>Ver Ficha de Treino</title>
</head>
<body>
     <H1 class = "teste" align="center">Olá, Aluno</H1>
      <div>
      <nav id="menu_aluno">
           <ul class= "nave">
               <li><a href="consultar_ficha">Ficha de Treino</a>               
               <li><a href="consultar_mensalidade">Mensalidades</a>
               <li><a href="index">Log Out</a>
           </ul>
      </nav>  
      </div> 
      <br>
      <br>
      <br>
    <form action="consultar_ficha" method="post">  
    <div align="center">
         <table>
                <tr>
                      <td><input type="number" min="0" id="id_aluno" name="id_aluno" placeholder="Informe seu Codigo"></td>
                      <td><button type="submit" id="botao" name="botao" value="Pesquisar">Pesquisar</button></td>
                </tr>
                
         </table>
    </div> 
    <br>
    <br>
    <br>     
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
	    <br>
	  <div class="teste" align="center">
		<c:if test="${not empty fichas }">
			<table border="1">
				<thead>
					<tr>
					    <th>ID da ficha</th>
						<th>Personal Responsavel</th>
						<th>Nome do Aluno</th>
					    <th>Descricao</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${fichas }" var="l">
						<tr>
						    <td><c:out value="${l.id_ficha }"></c:out></td>
							<td><c:out value="${l.nome_personal }"></c:out></td>
                            <td><c:out value="${l.nome_aluno }"></c:out></td>
                            <td><c:out value="${l.descricao }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	 </div>	  
	 
	 <br>    
</body>
</html>