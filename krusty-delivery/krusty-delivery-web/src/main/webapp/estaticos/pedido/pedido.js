/**
 * 
 */
var isDwr = false;

$(document).ready(function() {
	getProdutos();
	
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
			}).done(function(data, textStatus, jqXHR) {
				setMensagemSucesso();
			}).fail(function(data, textStatus, jqXHR) {
				setMensagemErro();
			}).always(function(data, textStatus, jqXHR) {
				setMensamgeFinalizado():
			});			
		}
	});
});

function setMensagemSucesso(){
	alert('Pedido efetuado com sucesso');
	console.log('Pedido efetuado com sucesso');
}

function setMensagemErro(){
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
		"lines" 		: [ {
			"descricaoProduto" 	: $("#descricaoProduto").val(),
			"quantidade" 		: parseInt($("#quantidade").val()),
			"valor" 			: $("#valor").val(),
			"numeroLinha" 		: parseInt($("#numeroLinha").val())
		} ]
	};
	
	return pedido;
}

function getPedidoDWR(){
	Pedido pedido = new Pedido();
	pedido.dataPedido = $("#dataPedido").val();
	pedido.mesa = $("#mesa").val();
	pedido.cliente = $("#cliente").val();
	
	PedidoLinha linha = new PedidoLinha();
	linha.descricaoProduto = $("#descricaoProduto").val();
	linha.quantidade = parseInt($("#quantidade").val());
	
	pedido.lines = new Array();
	pedido.lines.push(linha);
	
	return pedido;
}

function insert(){
	var pedido = getPedidoDWR();
	if( !pedido ){
		return;
	}
	
	var data = PedidoServices.insert( pedido, {
		callback : function(data) {

		},
		errorHandler : function(message, ex) {
			var dica = 'Verifique as informações do Pedido.';
			var fieldID = 'dataPedido';
			showErrorMessage(null, ex.cause.message, dica, ex, fieldID);
			}
		}
	);	
}

function getProdutos() {
	$("#produto").attr("disabled",true).html("<option selected=\"selected\" value=\"\">Carregando...</option>");

	var data = PedidoServices.getProdutos(  {
			callback : function(data) {
				$("#produto").attr("disabled", false);	
				renderCombobox(data, 'produto');
				listaContratos = data;
			},
			errorHandler : function(message, ex) {					
				var message = 'Produto não encontrado.';
				var dica = 'Por favor selecione um contrato.';
				var fieldID = 'produto';
				showErrorMessage(null, ex.cause.message, dica, ex, fieldID);
			}
		}
	);
}

function showErrorMessage(erro, message, dica, ex, fieldID) {
	$("div#containerError, div.error").show();
	var ms = "<strong>Erro, "+ message +".!</strong>";
	if( !isEmpty( dica ) ){
		ms = ms + "<br><span class='txt10'>Dica: "+dica+".<span>";
	}
	
	$("div.error").html(ms);
	$("body").scrollTo($("div#containerError"), {duration: 300});
	if( !isEmpty( fieldID ) ) {
		$("#"+fieldID+"").css("background","rgb(248, 220, 210)").focus();
	}
}

function renderCombobox(data, IDComponent) {
	var filterForm = "<option value=''>Selecione...</option>";

	if (data && data.length > 0) {
		for ( var i = 0; i < data.length; i++) {
			filterForm += "<option value=\"" + data[i].value + "\">"
					+ data[i].description + "</option>";
		}
	}

	$('#' + IDComponent + '').html(filterForm);
}
