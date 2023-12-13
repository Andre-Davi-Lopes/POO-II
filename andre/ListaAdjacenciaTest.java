package com.lopes.andre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaAdjacenciaTest {
	private ListaAdjacencia listaAdjacencia;

	@BeforeEach
	public void setUp() {
		listaAdjacencia = new ListaAdjacencia(5, false);
	}

	@Test
	public void testAdicionarAresta() {
		listaAdjacencia.adicionarAresta(0, 1, 5);
		assertTrue(listaAdjacencia.isVerticeAdjacente(0, 1));
	}

	@Test
	public void testRemoverAresta() {
		listaAdjacencia.adicionarAresta(0, 1, 5);
		listaAdjacencia.removerAresta(0, 1);
		assertFalse(listaAdjacencia.isVerticeAdjacente(0, 1));
	}

	@Test
	public void testBuscaEmLargura() {
		listaAdjacencia.adicionarAresta(0, 1, 5);
		listaAdjacencia.adicionarAresta(1, 2, 5);
		listaAdjacencia.buscaEmLargura(0);
	}

	@Test
	public void testBuscaEmProfundidade() {
		listaAdjacencia.adicionarAresta(0, 1, 5);
		listaAdjacencia.adicionarAresta(1, 2, 5);
		listaAdjacencia.buscaEmProfundidade(0);
	}
}
