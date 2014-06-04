<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pedidos em Aberto</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="estaticos/css/style.css" />" media="all">
</head>
<body>
	<div id="container"></div>
	<script type='text/javascript' src='<c:url value="/dwr/engine.js" />' ></script>
	<script type='text/javascript' src='<c:url value="/dwr/util.js" />' ></script>
	<script type='text/javascript' src='<c:url value="/dwr/interface/PedidoServices.js" />'></script>
	<script type="text/javascript" src="<c:url value="estaticos/js/resources/jquery/jquery-2.0.3.min.js" />"/>
	<script type="text/javascript" src="<c:url value="estaticos/js/resources/underscore/underscore.js" />"/>
	<script type="text/javascript" src="<c:url value="estaticos/util.js"></c:url>"/>
	<script type="text/javascript" src="<c:url value="estaticos/pedido/pedidos_em_aberto.js"> </c:url>"/>

</body>
</html>