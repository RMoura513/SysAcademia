<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/m_Mensalidade.css"/>'>
<title>Gerenciar Mensalidades</title>
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
     <div>
           <table>
                  <form action="manter_mensalidade" method="post">
                        <input type="number" min="0" step="0.1" id="valor" name="valor" placeholder="Valor"
                        value='<c:out value="${mens.valor }"></c:out>'>
                        
                        <input type="date" id="dataVenc" name="dataVenc" placeholder="Data de Vencimento"
                        value='<c:out value="${mens.dataVenc }"></c:out>'>
                        
                        <input type="text" id="descricao" name="descricao" placeholder="Descrição"
                        value='<c:out value="${mens.descricao }"></c:out>'>
                        
                        
                           <select id="nome_aluno" name="nome_aluno">
                            <option value="0">Selecione um Aluno</option>
                            <c:if test="${not empty alunos }">
                                <c:forEach items="${alunos }" var="a">
                                          <option><c:out value="${a.nome }"></c:out></option>
                                </c:forEach>
                            </c:if>           
                           </select>
                             
                        <input type="submit" id="botao" name="botao" value ="Inserir">                      
                        <input type="submit" id="botao" name="botao" value ="Listar"> 
                        <br>
                        <br>
                        <br>
                        <input type="number" id="id_mensalidade" name="id_mensalidade" placeholder="Informe o Codigo"
                        value='<c:out value="${mens.id_mensalidade }"></c:out>'>
                        
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
		<c:if test="${not empty lista_mensalidades }">
			<table border="1">
				<thead>
					<tr>
						<th>ID da Mensalidade</th>
						<th>Nome do Aluno</th>
						<th>Descricao</th>
						<th>Valor</th>
					    <th>Data de Vencimento</th>	    
					  
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista_mensalidades }" var="lm">
						<tr>
							<td><c:out value="${lm.id_mensalidade }"></c:out></td>
							<td><c:out value="${lm.nome_aluno }"></c:out></td>
							<td><c:out value="${lm.descricao }"></c:out></td>
                            <td><c:out value="${lm.valor }"></c:out></td>
                            <td><c:out value="${lm.dataVenc }"></c:out></td>
                                      

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	 </div>	  
	 
	 <br>
	 

</body>
</html>