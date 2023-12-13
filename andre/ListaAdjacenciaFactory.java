package com.lopes.andre;

public class ListaAdjacenciaFactory {
	private static ListaAdjacenciaFactory instance;

	private ListaAdjacenciaFactory() {

	}

	public static synchronized ListaAdjacenciaFactory getInstance() {
		if (instance == null) {
			instance = new ListaAdjacenciaFactory();
		}
		return instance;
	}

	public ListaAdjacencia createListaAdjacencia(int nVertices, boolean direcionado) {
		return new ListaAdjacencia(nVertices, direcionado);
	}
}
