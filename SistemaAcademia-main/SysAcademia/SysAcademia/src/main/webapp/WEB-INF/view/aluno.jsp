<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/aluno.css"/>'>
<title>Area do aluno</title>
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
     
      <div id="container">
  <img src="https://www.actionacademia.com.br/background2.jpg" alt="Minha Imagem" id="imagem">
	  </div>
</body>
</html>