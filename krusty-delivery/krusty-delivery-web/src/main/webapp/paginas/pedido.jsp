<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gerar Pedido</title>
<link rel="stylesheet" type="text/css" href="<c:url value="estaticos/css/style.css" />" media="all">
</head>
<body>
	<div id="container">
		<p align="center">
		<table>
			<thead>
				<tr>
					<th>Nome</th>
					<th>E-mail</th>
					<th>Telefone</th>
					<th>Sabor</th>
					<th>Quantidade</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type="text" id="cliente" maxlength="100" size="35">
					</td>
					<td>
						<input type="text" id="email" maxlength="100" size="40">
					</td>
					<td>
						<input type="text" id="telefone" maxlength="18" size="18">
					</td>
					<td>
						<select id="sabor">
							<option value="Carne">Carne</option>
							<option value="Queijo">Queijo</option>
							<option value="Pizza">Pizza</option>
							<option value="Quatro Queijos">Quatro Queijos</option>
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
	<script type="text/javascript"
		src="<c:url value="js/resources/jquery/jquery-2.0.3.min.js" />">
		
	</script>
	<script type="text/javascript"
		src="<c:url value="js/app/efetuarPedido.js"> </c:url>">
		
	</script>

</body>
</html>