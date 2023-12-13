package com.lopes.andre;

/**
 * Interface que define as operações básicas para um grafo.
 */
public interface IGrafo {

    /**
     * Cria um novo grafo com o número especificado de vértices e indicação de direcionamento.
     *
     * @param nVertices      Número de vértices no grafo.
     * @param direcionado    Indica se o grafo é direcionado (true) ou não (false).
     */
   public void criarGrafo(int nVertices, boolean direcionado);

    /**
     * Adiciona uma aresta ao grafo entre os vértices u e v, com o peso especificado.
     *
     * @param u      Vértice de origem da aresta.
     * @param v      Vértice de destino da aresta.
     * @param peso   Peso da aresta.
     */
   public void adicionarAresta(int u, int v, int peso);

    /**
     * Remove a aresta entre os vértices u e v do grafo.
     *
     * @param u      Vértice de origem da aresta.
     * @param v      Vértice de destino da aresta.
     */
   public void removerAresta(int u, int v);

    /**
     * Exibe o grafo na forma desejada.
     */
   public void exibirGrafo();

    /**
     * Realiza uma busca em largura a partir do vértice inicial no grafo.
     *
     * @param verticeInicial    Vértice inicial da busca em largura.
     */
   public void buscaEmLargura(int verticeInicial);

    /**
     * Realiza uma busca em profundidade a partir do vértice inicial no grafo.
     *
     * @param verticeInicial    Vértice inicial da busca em profundidade.
     */
   public void buscaEmProfundidade(int verticeInicial);
}
