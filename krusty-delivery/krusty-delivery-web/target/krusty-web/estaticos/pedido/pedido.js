/**
 * 
 */
$(document).ready(function() {
	$("#enviar").click(function() {
		var json = {
			"cliente" : $("#cliente").val(),
			"itens" : [ {
				"sabor" : $("#sabor").val(),
				"quantidade" : parseInt($("#quantidade").val())
			} ],
			"email" : $("#email").val(),
			"telefone" : $("#telefone").val()
		};
		
		$.ajax({
			url : './pedido',
			type : 'POST',
			accepts : 'application/json',
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			data : JSON.stringify(json),
			timeout : 10000
		}).done(function(data, textStatus, jqXHR) {
			alert('Pedido efetuado com sucesso');
			console.log('Pedido efetuado com sucesso');
		}).fail(function(data, textStatus, jqXHR) {
			alert('Ocorreu um erro ao efetuar o pedido: ' + jqXHR);
			console.log('Ocorreu um erro ao efetuar o pedido: ' + jqXHR);
		}).always(function(data, textStatus, jqXHR) {
			console.log('Finalizando efetuar pedido');
		});
	});
});
