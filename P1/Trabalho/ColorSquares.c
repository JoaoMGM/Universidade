//
// Created by jmmar on 08-01-2019.
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#include "ColorSquares.h"


void mostrar(int sz, int tabuleiro[sz][sz]){
    for (int k = sz - 1;k > -1; --k) {
        for (int i = 0;i<sz;++i) {
            printf("%d", tabuleiro[k][i]);
            printf(" ");
        }
        printf("\n");
    }
    printf("\n");
}

int marcar( int sz, int x, int y, int tabuleiro[sz][sz]){
    if ( x<sz && x>-1 && y<sz && y>-1 ){
        int count = 1;
        int cor = tabuleiro[y][x];
            tabuleiro[y][x] = 0;
            if(cor == 0){
                return 0;
            }
            //cima
            if (cor == tabuleiro[y + 1][x]) {
                count += marcar(sz, x, y + 1, tabuleiro);
            }
            //baixo
            if (cor == tabuleiro[y - 1][x]) {
                count += marcar(sz, x, y - 1, tabuleiro);
            }
            //direita
            if (cor == tabuleiro[y][x + 1]) {
                count += marcar(sz, x + 1, y, tabuleiro);
            }
            //esquerda
            if (cor == tabuleiro[y][x - 1]) {
                count += marcar(sz, x - 1, y, tabuleiro);
            }
            return count;

    }
        //se tiver fora do tabuleiro
    else{
        return 0;
    }

}

void gravidade( int sz, int tabuleiro[sz][sz]){
    for (int coluna = 0; coluna < sz; coluna++){
        int Proximalinha = 0;
        int LinhaVerificar = Proximalinha;
        while(LinhaVerificar <= sz-1 && Proximalinha <= sz-1){              // verificar se tas dentro do limite do tabuleiro
            while(Proximalinha <= sz-1 && tabuleiro[Proximalinha][coluna] != 0){    //andar de linha em linha ate achar um 0
                Proximalinha++;
            }
            if(Proximalinha <= sz-1){                                       //quando encontra o 0 * - verifica se ta nos limites
                LinhaVerificar = Proximalinha + 1;                          //  *ve a proxima linha
                while (LinhaVerificar <= sz-1 && tabuleiro[LinhaVerificar][coluna] == 0){   //  andar de linha em linha ate achar o 0 mais acima
                    LinhaVerificar++;
                }
                if(LinhaVerificar <= sz-1){// verifica se ta nos limites
                    tabuleiro[Proximalinha][coluna]= tabuleiro[LinhaVerificar][coluna];     // substitui o valor da debaixo pelo de cima
                    tabuleiro[LinhaVerificar][coluna]= 0;
                }
            }
        }
    }


}



void coluna( int sz, int tabuleiro[sz][sz]){
    int ProximaCol = sz -1;
    int VerificarCol = ProximaCol;
    while(tabuleiro[0][ProximaCol]==0){
        ProximaCol--;
    }
    while( ProximaCol >= 0 && VerificarCol >= 0){
        while(ProximaCol >= 0 && tabuleiro[0][ProximaCol] != 0){
            ProximaCol--;
        }
        if(ProximaCol >= 0){
            VerificarCol = ProximaCol + 1;
            if(VerificarCol >= 0 & VerificarCol < sz){
                for(int linha = 0; linha < sz; linha ++){
                    tabuleiro[linha][ProximaCol]=tabuleiro[linha][VerificarCol];
                    tabuleiro[linha][VerificarCol] = 0;
                }
                for(int i = sz; i >= 0; i--){
                    if(tabuleiro[0][i] == 0) {
                        coluna(sz, tabuleiro);
                    }
                }
            }
        }
    }
}

int pontuacao(int num_quadrados){
    int pontos = (num_quadrados*(num_quadrados+1))/2;
    return pontos;
}

int jogada(int sz, int tabuleiro[sz][sz], int x, int y){
    int pontos =marcar(sz,x,y,tabuleiro);
    gravidade(sz,tabuleiro);
    coluna(sz,tabuleiro);
    return pontuacao(pontos);
}

