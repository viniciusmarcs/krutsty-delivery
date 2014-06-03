/**
 * 
 */
package br.com.walmart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author marcosvinicius
 *
 */
@Embeddable
public class Produto implements Serializable {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUTO_ID", nullable = false)
	private Long produtoID;
	
	@Column(name="CODIGO", nullable=false )	
	private int codigo;
	
	@Column(name="NOME", nullable=false, length=60)
	private String nome;
	
	@Column(name="PRECO", nullable=false )	
	private double preco;
	
	public Produto(){
		
	}
	
	public Produto( String nome, int codigo, double preco ){
		this.nome = nome;
		this.codigo = codigo;
		this.preco = preco;
		
	}
	
	public String getNome() {
		return nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public double getPreco() {
		return preco;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + this.nome ;
	}
}
