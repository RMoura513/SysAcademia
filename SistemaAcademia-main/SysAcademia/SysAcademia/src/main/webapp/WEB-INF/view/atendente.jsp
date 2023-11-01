<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/atendente.css"/>'>
<title>Area do Atendente</title>
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
     
      <div id="container">
  <img src="https://img.freepik.com/fotos-gratis/halteres-no-chao-de-uma-academia-ai-generative_123827-23744.jpg" alt="Minha Imagem" id="imagem">
	  </div>
</body>
</html>