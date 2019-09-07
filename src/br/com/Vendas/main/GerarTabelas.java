package br.com.Vendas.main;

import br.com.Vendas.util.HibernateUtil;

public class GerarTabelas {
	
	public static void main (String[] args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
		
	}

}
