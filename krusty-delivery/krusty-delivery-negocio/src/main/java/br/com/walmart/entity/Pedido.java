/**
 * 
 */
package br.com.walmart.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author marcosvinicius - mail.: vinicius.marcs@gmail.com
 * 
 * @date 30/05/2014
 * @class br.com.walmart.entity.Pedido.java
 * @version 1.0
 */
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PEDIDO_ID", nullable = false)
	private Long pedidoid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAPEDIDO", nullable = false)
	private Date dataPedido;

	@Column(name = "NOMECLIENTE", nullable = false, length = 60)
	private String cliente;

	@Column(name = "MESA", nullable = false)
	private int mesa;

	@Column(name = "NUMEROPEDIDO", nullable = false)
	private long numeroPedido;

	@Column(name = "TOTALPEDIDO", nullable = false)
	private double totalPedido;

	@ElementCollection
	@CollectionTable(name = "pedidoLinha", joinColumns = @JoinColumn(name = "PEDIDOLINHA_ID"))
	private List<PedidoLinha> lines;

	// @Enumerated(EnumType.STRING)
	@Column(name = "STATUS", length = 30)
	private String status;

	public Pedido() {
		this.lines = new ArrayList<PedidoLinha>();
	}

	public Pedido(String cliente, int mesa) {
		this();
		this.cliente = cliente;
		this.mesa = mesa;
		this.numeroPedido = System.currentTimeMillis();
		this.status = StatusPedido.N.toString();
		this.dataPedido = new Date();
	}

	public int getMesa() {
		return mesa;
	}

	public long getNumeroPedido() {
		return numeroPedido;
	}

	public List<PedidoLinha> getLines() {
		return lines;
	}

	public double getTotalPedido() {
		return totalPedido;
	}

	public String getStatus() {
		return status;
	}

	public void addLine(PedidoLinha line) {
		if (this.lines == null) {
			this.lines = new ArrayList<PedidoLinha>();
		}

		this.lines.add(line);
		this.totalPedido = this.totalPedido + line.getValor();
	}

	public void novoPedido() {
		this.status = StatusPedido.N.toString();
	}

	public void andamentoPedido() {
		this.status = StatusPedido.A.toString();
	}

	public void finalizaPedido() {
		this.status = StatusPedido.F.toString();
	}

	public void fechaPedido() {
		this.status = StatusPedido.E.toString();
	}

	public void canceladoPedido() {
		this.status = StatusPedido.C.toString();
	}

	public String toString() {
		return "Mesa.: " + this.mesa + " - Pedido.: " + this.numeroPedido
				+ " - Status.: " + this.status + " - Total.: "
				+ this.totalPedido;
	}
}
