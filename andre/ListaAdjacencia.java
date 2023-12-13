package com.lopes.andre;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Aresta {
	int origem;
	int destino;
	int peso;
	
	public Aresta(int origem, int destino, int peso) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}
}

public class ListaAdjacencia {
	
	private int nVertices;
	private List<List<Aresta>> adjacencias;
	private boolean direcionado;
	
	public ListaAdjacencia (int nVerices, boolean direcionado) {
		this.nVertices = nVerices;
		this.direcionado = direcionado;
		this.adjacencias = new ArrayList<>(nVertices);
		
		for(int i = 0; i < nVertices; i++) {
			adjacencias.add(new ArrayList<>());
		}
	}
	
	/**
	 * 
	 * @param u
	 * @param v
	 * @param peso
	 */
	public void adicionarAresta (int u, int v, int peso) {
		Aresta aresta = new Aresta(u, v, peso);
		adjacencias.get(u).add(aresta);
		
		if (!direcionado) {
			Aresta arestaInvertida = new Aresta(v, u, peso);
			adjacencias.get(v).add(arestaInvertida);
		}
	}
	
	
	/**
	 * 
	 * @param u
	 * @param v
	 */
	public void adicionarAresta(int u, int v) {
		adicionarAresta(u, v, 1);
	}
	
	/**
	 * Mostra a Lista de Adjacências
	 */
	public void mostrarListaAdjacencia() {
		for(int i = 0; i < nVertices; i++) {
			System.out.print("Vértice " + i + ": ");
			for(Aresta aresta : adjacencias.get(i)) {
				System.out.print("(" + aresta.destino + ", Peso:" + aresta.peso + ")");
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 * @param u
	 * @param v
	 */
	public void removerAresta(int u, int v) {
		List<Aresta> arestaU = adjacencias.get(u);
		for (Aresta aresta : arestaU) {
			if (aresta.destino == v) {
				arestaU.remove(aresta);
				break;
			}
		}
		
		if (!direcionado) {
			List<Aresta> arestaV = adjacencias.get(v);
			for(Aresta aresta : arestaV) {
				if (aresta.destino == u) {
					arestaV.remove(aresta);
					break;
				}
			}
		}
	}
	
	public boolean isVerticeAdjacente(int u, int v) {
		for(Aresta aresta : adjacencias.get(u)) {
			if (aresta.destino == v) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param vertice
	 */
	public void mostrarListaAdjacenteDoVertice(int vertice) {
		System.out.println("Vértice " + vertice + ":");
		for (Aresta aresta : adjacencias.get(vertice)) {
			System.out.print("(" + aresta.destino + ", Peso:" + aresta.peso + ")");
		}
		System.out.println();
	}
	
	
	/**
	 * 
	 * @param verticeInicial
	 */
	public void buscaEmLargura(int verticeInicial) {
        boolean[] visitado = new boolean[nVertices];
        //Fila para busca em largura
        Queue<Integer> fila = new LinkedList<>();

        visitado[verticeInicial] = true;
        fila.add(verticeInicial);

        System.out.println("Resultado da busca em largura a partir do vértice " + verticeInicial + ":");
        while (!fila.isEmpty()) {
            int verticeAtual = fila.poll();
            System.out.print(verticeAtual + " ");

            for (Aresta aresta : adjacencias.get(verticeAtual)) {
                int vizinho = aresta.destino;
                if (!visitado[vizinho]) {
                    visitado[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }
        System.out.println();
    }
	
	/**
	 * 
	 * @param verticeInicial
	 */
	public void buscaEmProfundidade(int verticeInicial) {
        boolean[] visitado = new boolean[nVertices];
        Stack<Integer> pilha = new Stack<>();

        visitado[verticeInicial] = true;
        pilha.push(verticeInicial);

        System.out.println("Resultado da busca em profundidade a partir do vértice " + verticeInicial + ":");
        while (!pilha.isEmpty()) {
            int verticeAtual = pilha.pop();
            System.out.print(verticeAtual + " ");

            for (Aresta aresta : adjacencias.get(verticeAtual)) {
                int vizinho = aresta.destino;
                if (!visitado[vizinho]) {
                    visitado[vizinho] = true;
                    pilha.push(vizinho);
                }
            }
        }
        System.out.println();
    }
	
	/**
	 * 
	 * @param v
	 */
	public void removerVertice(int v) {
	    for (int i = 0; i < nVertices; i++) {
	        List<Aresta> arestas = adjacencias.get(i);
	        arestas.removeIf(aresta -> aresta.origem == v || aresta.destino == v);
	    }

	    adjacencias.remove(v);
	    nVertices--;

	    for (int i = 0; i < nVertices; i++) {
	        List<Aresta> arestas = adjacencias.get(i);
	        for (Aresta aresta : arestas) {
	            if (aresta.origem > v) {
	                aresta.origem--;
	            }
	            if (aresta.destino > v) {
	                aresta.destino--;
	            }
	        }
	    }
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isConexo() {
	    boolean[] visitado = new boolean[nVertices];
	    Stack<Integer> pilha = new Stack<>();

	    visitado[0] = true;
	    pilha.push(0);

	    while (!pilha.isEmpty()) {
	        int verticeAtual = pilha.pop();

	        for (Aresta aresta : adjacencias.get(verticeAtual)) {
	            int vizinho = aresta.destino;
	            if (!visitado[vizinho]) {
	                visitado[vizinho] = true;
	                pilha.push(vizinho);
	            }
	        }
	    }

	    for (boolean v : visitado) {
	        if (!v) {
	            return false;
	        }
	    }

	    return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCompleto() {
	    int totalArestas = 0;
	    int arestasEsperadas = 0;
	    for (List<Aresta> arestas : adjacencias) {
	        totalArestas = totalArestas + arestas.size();
	    }
	    
	    if (!direcionado) {
	        totalArestas = totalArestas / 2;
	    }
	    
	    if (direcionado) {
	        arestasEsperadas = nVertices * (nVertices - 1);
	    } else {
	        arestasEsperadas = nVertices * (nVertices - 1) / 2;
	    }
	    
	    return totalArestas == arestasEsperadas;
	}
	
	/**
	 * 
	 * @param origem
	 */
	public void dijkstra(int origem) {
	    int[] distancias = new int[nVertices];
	    boolean[] visitado = new boolean[nVertices];

	    for (int i = 0; i < nVertices; i++) {
	        distancias[i] = Integer.MAX_VALUE;
	        visitado[i] = false;
	    }

	    distancias[origem] = 0;

	    for (int count = 0; count < nVertices - 1; count++) {
	        int u = minDistancia(distancias, visitado);

	        visitado[u] = true;

	        for (Aresta aresta : adjacencias.get(u)) {
	            int v = aresta.destino;
	            if (!visitado[v] && distancias[u] != Integer.MAX_VALUE && distancias[u] + aresta.peso < distancias[v]) {
	                distancias[v] = distancias[u] + aresta.peso;
	            }
	        }
	    }

	    printDistancias(distancias, origem);
	}

	/**
	 * 
	 * @param distancias
	 * @param visitado
	 * @return
	 */
	private int minDistancia(int[] distancias, boolean[] visitado) {
	    int min = Integer.MAX_VALUE;
	    int minIndex = -1;

	    for (int v = 0; v < nVertices; v++) {
	        if (!visitado[v] && distancias[v] <= min) {
	            min = distancias[v];
	            minIndex = v;
	        }
	    }

	    return minIndex;
	}

	/**
	 * 
	 * @param distancias
	 * @param origem
	 */
	private void printDistancias(int[] distancias, int origem) {
	    System.out.println("Menor distância do vértice de origem (" + origem +  ") para outros vértices: ");
	    for (int i = 0; i < nVertices; i++) {
	        System.out.println(origem + " -> " + i + " Distância: " + distancias[i]);
	    }
	}
	
	public String isEuleriano() {
	    if (!isConexo()) {
	        return "Não Euleriano";
	    }

	    int verticesImpares = 0;
	    for (List<Aresta> arestas : adjacencias) {
	        if (arestas.size() % 2 != 0) {
	            verticesImpares++;
	        }
	    }

	    if (verticesImpares > 2) {
	        return "Não Euleriano";
	    }
	    
	    if (verticesImpares == 2) {
	        return "Semi-Euleriano";
	    } else {
	        return "Euleriano";
	    }
	}
	
	/**
	 * 
	 * @return
	 */
	public String isHamiltonian() {
	    boolean[] visitado = new boolean[nVertices];

	    if (cicloHamiltonian(visitado, 0)) {
	        return "Hamiltoniano";
	    } else if (caminhoHamiltonian(visitado, 0)) {
	        return "Semi-Hamiltoniano";
	    } else {
	        return "Não Hamiltoniano";
	    }
	}

	private boolean cicloHamiltonian(boolean[] visitado, int v) {
	    if (verticesVisitados(visitado)) {
	        for (Aresta aresta : adjacencias.get(v)) {
	            if (aresta.destino == 0) {
	                return true;
	            }
	        }
	        return false;
	    }

	    for (Aresta aresta : adjacencias.get(v)) {
	        if (!visitado[aresta.destino]) {
	        	visitado[aresta.destino] = true;
	            if (cicloHamiltonian(visitado, aresta.destino)) {
	                return true;
	            }
	            visitado[aresta.destino] = false;
	        }
	    }

	    return false;
	}

	/**
	 * 
	 * @param visitado
	 * @param v
	 * @return
	 */
	private boolean caminhoHamiltonian(boolean[] visitado, int v) {
	    if (verticesVisitados(visitado)) {
	        return true;
	    }

	    for (Aresta aresta : adjacencias.get(v)) {
	        if (!visitado[aresta.destino]) {
	        	visitado[aresta.destino] = true;
	            if (caminhoHamiltonian(visitado, aresta.destino)) {
	                return true;
	            }
	            visitado[aresta.destino] = false;
	        }
	    }

	    return false;
	}

	/**
	 * 
	 * @param visitado
	 * @return
	 */
	private boolean verticesVisitados(boolean[] visitado) {
	    for (boolean v : visitado) {
	        if (!v) {
	            return false;
	        }
	    }
	    return true;
	}
}
