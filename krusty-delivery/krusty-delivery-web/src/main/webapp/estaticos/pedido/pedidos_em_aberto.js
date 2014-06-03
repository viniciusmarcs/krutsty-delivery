/**
 * 
 */
var tabelaTemplate = 
		'<p align="center">' 
			+ '<table id="pedidos">'
				+ '<thead>'
					+ '<tr>' 
						+ '<th>ID do pedido</th>' 
						+ '<th>Data do pedido</th>'
						+ '<th>Sabor</th>' 
						+ '<th>Quantidade</th>' 
						+ '<th>Cliente</th>'
						+ '<th>Status</th>'
					+ '</tr>'
				+ '</thead>' 
				+ '<tbody>' 
					+ '<% _.each(pedidos, function(pedido) { %>'
						+ '<tr>' 
							+ '<td><%= pedido.id %></td>'
							+ '<td><%= _.formatdate(pedido.dataPedido) %></td>'
							+ '<td><%= pedido.lines[0].valor %></td>'
							+ '<td><%= pedido.lines[0].quantidade %></td>'
							+ '<td><%= pedido.cliente %></td>' 
							+ '<td><%= _.status(pedido.status) %></td>'
						+ '</tr>'
					+ '<% }); %>' 
				+ '</tbody>' 
			+ '</table>' 
		+ '</p>';

function montarTabela(json) {
	if (json) {
		$('#container').empty();
		var template = _.template(tabelaTemplate, json);
		$('#container').append(template);
	} else {
		console.log('JSON invalido');
	}
}

function atender(id, status) {
	console.log('Iniciando o atendimento do pedido ' + id);
	$.ajax({
		url : './atendimento',
		type : 'POST',
		accepts : 'application/json',
		data : 'id=' + id,
		timeout : 10000
	}).done(function(data, textStatus, jqXHR) {
		alert('Pedido em processo de atendimento');
		console.log('Pedido em processo de atendimento');
	}).fail(function(data, textStatus, jqXHR) {
		alert('Ocorreu um erro ao atender o pedido: ' + jqXHR);
		console.log('Ocorreu um erro ao atender o pedido: ' + jqXHR);
	}).always(function(data, textStatus, jqXHR) {
		console.log('Finalizando requisição de atendimento do pedido');
	});
}
$(document)
		.ready(
				function() {
					(function() {
						$
								.ajax({
									url : './pedidos',
									type : 'GET',
									accepts : 'application/json',
									timeout : 10000
								})
								.done(
										function(data, textStatus, jqXHR) {
											montarTabela(data);
											console
													.log('Pedidos consultados com sucesso');
										})
								.fail(
										function(data, textStatus, jqXHR) {
											console
													.log('Ocorreu um erro ao buscar os pedidos cadastrados: '
															+ jqXHR);
										})
								.always(
										function(data, textStatus, jqXHR) {
											console
													.log('Finalizando busca de pedidos');
										});
					})();
				});

/**
 * Novo atendimento
 * 
 * @param id
 * @param status
 */
function atender(id, status) {
	console.log('Iniciando o atendimento do pedido ' + id + ' e status '
			+ status);
	$.ajax({
		url : './atendimento',
		type : 'POST',
		accepts : 'application/json',
		data : 'id=' + id,
		timeout : 10000
	}).done(function(data, textStatus, jqXHR) {
		alert('Pedido em processo de atendimento');
		console.log('Pedido em processo de atendimento');
	}).fail(function(data, textStatus, jqXHR) {
		alert('Ocorreu um erro ao atender o pedido: ' + jqXHR);
		console.log('Ocorreu um erro ao atender o pedido: ' + jqXHR);
	}).always(function(data, textStatus, jqXHR) {
		console.log('Finalizando requisicao de atendendimento do pedido');
	});
}

/**
 * responsavel por trabalhar com a classe AtualizacaoWebSocket
 * servico de comunicacao bidirecional
 * 
 * @param endpoint
 * @param onMessage
 */
function init(endpoint, onMessage) {
	ws = new WebSocket(endpoint);
	ws.onopen = function() {
		console.log('Conexão aberta com sucesso');
	};
	if (onMessage) {
		ws.onmessage = onMessage;
	}
	ws.onclose = function() {
		console.log('Conexão fechada com sucesso');
	};
	ws.onerror = function(evt) {
		console.log('Ocorreu um erro no WebSocket' + evt.data);
	};
}

/**
 * 
 * @param evt
 */
function atualizarTabela(evt) {
	if (evt.data) {
		var json = $.parseJSON(evt.data);
		montarTabela(json);
	} else {
		console.log('WebSocket não retornou dados');
	}
}

/**
 * 
 */
$(document).ready(function() {
	(function(){ 
		window.addEventListener("load", 
				init( "ws://localhost:8080/pastelaria-online/notificacao", atualizarTabela), false); 
		})
	();
}

