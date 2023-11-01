<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/c_Mensalidade.css"/>'>
<title>Ver Mensalidade</title>
</head>
<body>
      <H1 class = "teste" align="center">Olá, Aluno</H1>
      <div align="center">
      <nav id="menu_aluno" >
           <ul class="nave">
               <li><a href="consultar_ficha">Ficha de Treino</a>               
               <li><a href="consultar_mensalidade">Mensalidades</a>
               <li><a href="index">Log Out</a>
           </ul>
      </nav>
      </div>
      <br>
      <br>
      <br>
      <br>
     <form action="consultar_mensalidade" method="post"> 
     <div align="center">
           <table>
                  <tr>
                      <td><input type="number" id="id_aluno" name="id_aluno" placeholder="Informe seu Codigo"></td>
                      <td><button type="submit" id="botao" name="botao" value ="Pesquisar">Pesquisar</button></td>
                  </tr>
           </table>
      </div>
      </form>
      <br>
      <br>
      <br>
      <br>
	  <div class="teste" align="center">
		<c:if test="${not empty erro }">
			<H2><c:out value="${erro }" /></H2>
		</c:if>  
	  </div> 
	   
	  <div class="teste"  align="center">
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" /></H2>
		</c:if>  
	  </div> 	  
	    
	  <div  class="teste" align="center">
		<c:if test="${not empty mensalidades }">
			<table border="1">
				<thead>
					<tr>
					
						
						<th>Descricao</th>
						<th>Valor</th>
					    <th>Data de Vencimento</th>	    
					  
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${mensalidades }" var="lm">
						<tr>
							<td><c:out value="${lm.descricao }"></c:out></td>
                            <td><c:out value="${lm.dataVenc }"></c:out></td>  
                            <td><c:out value="${lm.valor }"></c:out></td>          

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	 </div>	  
	 
	 <br>           
</body>
</html>