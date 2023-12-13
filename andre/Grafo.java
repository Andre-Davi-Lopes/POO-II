package com.lopes.andre;

public class Grafo implements IGrafo{
	   private ListaAdjacenciaFactory factory;
	   private ListaAdjacencia listaAdjacencia;

	   public Grafo() {
		   this.factory = ListaAdjacenciaFactory.getInstance();
	   }

	   public void criarGrafo(int nVertices, boolean direcionado) {
	       this.listaAdjacencia = factory.createListaAdjacencia(nVertices, direcionado);
	   }

	   public void adicionarAresta(int u, int v, int peso) {
	       listaAdjacencia.adicionarAresta(u, v, peso);
	   }

	   public void removerAresta(int u, int v) {
	       listaAdjacencia.removerAresta(u, v);
	   }

	   public void exibirGrafo() {
	       listaAdjacencia.mostrarListaAdjacencia();
	   }

	   public void buscaEmLargura(int verticeInicial) {
	       listaAdjacencia.buscaEmLargura(verticeInicial);
	   }

	   public void buscaEmProfundidade(int verticeInicial) {
	       listaAdjacencia.buscaEmProfundidade(verticeInicial);
	   }
	}
