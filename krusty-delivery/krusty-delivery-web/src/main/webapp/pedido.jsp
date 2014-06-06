<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-BR">
<head>

	<title>Gerar Pedido</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		
	<link rel="stylesheet" type="text/css" href="<c:url value="estaticos/css/style.css" />" media="all">

	<script type="text/javascript" src='<c:url value="/dwr/interface/PedidoServices.js"/>' ></script>
	<script type="text/javascript" src='<c:url value="/dwr/engine.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/dwr/util.js"/>'></script>
		
	<script type='text/javascript' src='<c:url value="/estaticos/js/resources/jquery/jquery-2.0.3.min.js" />' ></script>
	<script type='text/javascript' src='<c:url value="/estaticos/pedido/pedido.js" />' ></script>
</head>
<body>
	<div id="container">
		<p align="center">
		<table>
			<thead>
				<tr>
					<th>Data Pedido</th>
					<th>Mesa</th>
					<th>Cliente</th>
					<th>Produto</th>
					<th>Quantidade</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type="text" id="dataPedido" maxlength="100" size="40">
					</td>				
					<td>
						<input type="text" id="mesa" maxlength="100" size="35">
					</td>
					<td>
						<input type="text" id="cliente" maxlength="100" size="40">
					</td>
					<td>
						<select id="produto"  >
						</select>
					</td>
					<td>
						<input type="text" id="quantidade" maxlength="3" size="3">
					</td>
					<td>
						<input type="button" value="Enviar" id="enviar">
					</td>
				</tr>
			</tbody>
		</table>
		</p>
	</div>


	
</body>
</html>