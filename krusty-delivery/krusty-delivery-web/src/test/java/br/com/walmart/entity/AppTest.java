package br.com.walmart.entity;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 * 
 * @data 29/05/2014
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		System.out.println("Teste para insercao de novo Pedido \n");
		Pedido order = new Pedido("Marcos Vinicius", 2);
		// Produto product = new Produto("hamburger", 2, 10.0);
		// order.addLine(new PedidoLinha(product));
		int i = 1;
		for( ProdutosEnum e : ProdutosEnum.values()
				){
			order.addLine(new PedidoLinha(e.toString(), ++i));
		}
		
		
		for (PedidoLinha linha : order.getLines()) {
			System.out.println(linha);
		}
		
		assertTrue(order.getTotalPedido() >= 0);
		assertTrue(order.getTotalPedido() == order.getTotal());

		assertTrue(order.getStatus().equals(StatusPedido.N.toString()));
		System.out.println(order);
		
		// Teste para validacao da alteracao do estatus do Pedido
		order.andamentoPedido();
		assertTrue(order.getStatus().equals(StatusPedido.A.toString()));
		System.out.println(order);
		
		for( ProdutosEnum e : ProdutosEnum.values()
				){
			order.addLine(new PedidoLinha(e.toString(), ++i));
		}
		assertTrue(order.getTotalPedido() == order.getTotal());
		assertFalse(order.getTotalPedido() != order.getTotal());
		
		order.finalizaPedido();
		assertTrue(order.getStatus().equals(StatusPedido.F.toString()));
		System.out.println(order);
		
		for( ProdutosEnum e : ProdutosEnum.values()
				){
			order.addLine(new PedidoLinha(e.toString(), ++i));
		}
		
		order.fechaPedido();
		assertTrue(order.getStatus().equals(StatusPedido.E.toString()));
		System.out.println(order);
		
		// Teste Enum StatusPedido
		StatusPedidoTest();
	}
	
	public void StatusPedidoTest(){
		for( StatusPedido s : StatusPedido.values() ){
			System.out.println(s.toString());
		}
	}

}
