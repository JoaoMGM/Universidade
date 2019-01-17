#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "ColorSquares.h"

int main() {

    int tamanho = 0;
    int x = 0;
    int y = 0;
    int Pontuacaofinal = 0;


    printf("Bem-vindo ao ColorSquare\n");
    printf("\n");

    printf("Insira o tamanho do tabuleiro\n");
    scanf("%d", &tamanho);
    //Criar tabuleiro com o tamanho pedido
    int tabuleiro[tamanho][tamanho];
    for (int i = 0; i < tamanho; ++i) {
        for (int j = 0; j < tamanho; ++j) {
            tabuleiro[i][j] = (rand() %4)+1;
        }
    }
//mostrar tabuleiro que foi criado
    mostrar(tamanho,tabuleiro);

    //loop para jogar onde se insere as coordenadas da jogada
    while(tabuleiro[0][0] != 0) {
        printf("Insira x \n");
        scanf("%d", &x);
        printf("Insira y \n");
        scanf("%d", &y);

        Pontuacaofinal += jogada(tamanho,tabuleiro,x,y);
        mostrar(tamanho,tabuleiro);

    }
    printf("Fim do Jogo \n");
    printf("Pontucao Final: %d \n" , Pontuacaofinal);
    return Pontuacaofinal;
}

