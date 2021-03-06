/**
 * 
 */
var isDwr = false;
$(document).ready(function() {
//	getProdutos();
	
	$("#enviar").click(function() {
		var json = getPedido();
		
		if( isDwr ){
			insert();
		} else {
			$.ajax({
				url : './pedido',
				type : 'POST',
				accepts : 'application/json',
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				data : JSON.stringify(json),
				timeout : 10000
			})	.done(function(data, textStatus, jqXHR) {
				setMensagemSucesso();
			}).fail(function(data, textStatus, jqXHR) {
				setMensagemErro(jqXHR);
			})	.always(function(data, textStatus, jqXHR) {
				setMensamgeFinalizado();
			});	
		}
	});
});

function setMensagemSucesso(){
	alert('Pedido efetuado com sucesso');
	console.log('Pedido efetuado com sucesso');
}

function setMensagemErro( jqXHR ){
	alert('Ocorreu um erro ao efetuar o pedido: ' + jqXHR);
	console.log('Ocorreu um erro ao efetuar o pedido: ' + jqXHR);	
}

function setMensamgeFinalizado(){
	console.log('Finalizando efetuar pedido');	
}

function getPedido(){
	var pedido = {
		"dataPedido" 	: $("#dataPedido").val(),
		"mesa" 			: $("#mesa").val(),
		"cliente" 		: $("#cliente").val(),
		"lines" 		: [{	"pedidoLinhaID":123,
			"produto":{"produtoID":123,"codigo":1,"nome":"Pizza","preco":10.0},
			"numeroLinha":1,
			"valor":10.0,
			"quantidade":1,
			"descricaoProduto": "Pizza"}]
	};
	
	return pedido;
}

function getProdutos(){
	
}