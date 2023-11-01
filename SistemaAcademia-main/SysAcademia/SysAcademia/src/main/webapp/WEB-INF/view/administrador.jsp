<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/adm.css"/>'>

<title>Area do Administrador</title>
</head>
<body>

    	<H1 class= "teste" align ="center">Olá, Administrador</H1>
      <div align="center">
      <nav id="menu_administrador">
           <ul class="nave">
               <li><a href="manter_atendente">Atendentes</a>    
               <li><a href="manter_personal">Personal</a>  
               <li class= "logOut"><a href="index">Log Out</a>  
           </ul>
      </nav>
      </div>
      <br>
      <br>
      <br>
     
      <div id="container">
  <img src="https://i.pinimg.com/736x/11/7a/e9/117ae951c227db92771bada348063390.jpg" alt="Minha Imagem" id="imagem">
	  </div>
	
</body>
</html>