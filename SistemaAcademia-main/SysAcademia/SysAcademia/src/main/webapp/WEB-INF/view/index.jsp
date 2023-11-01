<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/Login.css"/>'>
<title>Login</title>
</head>
<body>

    <div  class="login-box">
    <h2>Login</h2>
<form action="index" method="post">
    <div class="user-box">
        <input type="text" id="usuario" 
        name="usuario" 
         required="required">
        <label>Usuario</label>
        </div>
        
        <div class="user-box">
        <input type="password" id="senha"
         name="senha"  
        required="required">
        <label>Senha</label>
    </div>
      
      <div align = "center" class = "botao">
     <a >
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <button type="submit" id="botao" name="botao" value="Entrar">Entrar</button>
    </a>
           </div> 
                 
</form>
       </div>

	<div align="center" class="mensagem">
		<c:if test="${not empty erro }">
			<c:out value="${erro }" />
		</c:if>
		<c:if test="${not empty acesso }">
		      <c:if test="${acesso == 'ADM' }">
		       <%
		         response.sendRedirect("administrador");

		       %>
		      </c:if>
		</c:if>
		

		<c:if test="${not empty acesso }">
		      <c:if test="${acesso == 'ATEN' }">
		       <%
		         response.sendRedirect("atendente");
		       %>
		      </c:if>
		</c:if>	
		
		<c:if test="${not empty acesso }">
		      <c:if test="${acesso == 'PERSO' }">
		       <%
		         response.sendRedirect("personal");
		       %>
		      </c:if>
		</c:if>			
			
		<c:if test="${not empty acesso }">
		      <c:if test="${acesso == 'ALU' }">
		       <%
		         String usuario = request.getParameter("usuario");
		         session.setAttribute("usuario", usuario);
		         response.sendRedirect("aluno");
		       %>
		      </c:if>
		</c:if>
	</div>
</body>
</html>