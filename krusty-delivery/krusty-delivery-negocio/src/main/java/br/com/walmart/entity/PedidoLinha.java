/**
 * 
 */
package br.com.walmart.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
*
* @author marcosvinicius - mail.: vinicius.marcs@gmail.com
* 
* @date 30/05/2014
* @class br.com.walmart.entity.PedidoLinha.java
* @version 1.0
*/
@Embeddable
public class PedidoLinha implements Serializable {
	
	private static int linha;
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="PEDIDOLINHA_ID", nullable=false)	
	private Long pedidoLinhaID;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Produto.class, optional = false)
    @JoinColumn(name = "PRODUTO_ID", referencedColumnName = "PRODUTO_ID", nullable = false, insertable = true, updatable = true, unique = false)
	private Produto produto;

	@Column(name="NUMEROLINHA", nullable=false)
	private int numeroLinha;
	
	@Column(name="VALOR", nullable=false)
	private double valor;
	
	@Column(name="QUANTIDADE", nullable=false)
	private int quantidade;
	
	private String descricaoProduto;
	
	public PedidoLinha(){
		
	}
	
	public PedidoLinha( Produto produto ){
		this.produto = produto;
		this.valor = produto.getPreco();
		this.numeroLinha = this.linha++;
	}
	
	public PedidoLinha( String descricaoProduto, int quantidade ){
		this.descricaoProduto = descricaoProduto;
		this.quantidade = quantidade;
		// comentar para fazer Build
		//this.valor = getScale( RandomUtils.nextDouble() );
		this.numeroLinha = this.linha++;
	}

	public int getLinha() {
		return linha;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getNumeroLinha() {
		return numeroLinha;
	}

	public double getValor() {
		return valor;
	}

	public Long getPedidoLinhaID() {
		return pedidoLinhaID;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public double getScale( double value){
		return new BigDecimal( value ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub 
		// teste descricaoProduto usaria StringUtils commons lang mas
		// da erro Build Maven
		return  "Linha No.: " + this.numeroLinha + " - Produto.: " + (this.descricaoProduto == null && this.descricaoProduto.length() == 0 ? this.produto : this.descricaoProduto ) + " - Valor R$ .: " + this.valor;
	}
}
