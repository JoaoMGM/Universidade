#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include "ColorSquares.h"

int main() {
    int x = 0;
    int y = 0;
    int PontuacaoFinal = 0;


    FILE *ptr_file;
    int r;
    int nrLinhas =0;
    int nrJogadas =0;
    ptr_file =fopen("output.txt", "r");
    if (ptr_file == NULL){
        printf("file does not exists ");
        return 0;
    }



    char line [ 128 ];
    fgets ( line, sizeof line, ptr_file );
    nrLinhas = atoi(line);

    int tabuleiro[nrLinhas][nrLinhas];

    for (int l = nrLinhas - 1 ; l >= 0; --l) {
        fgets ( line, sizeof line, ptr_file );
        for(int i = 0 /*colunas*/ ; i < nrLinhas ; i++){
            int a = line[i] - 48;  // Because decimal value of char '0' is 48
            tabuleiro[l][i] = a;
        }
        //printf("%c%c", line[0],line[0]);
    }



    nrJogadas = atoi(fgets ( line, sizeof line, ptr_file ));
    char* jogadas[nrJogadas];
    fgets ( line, sizeof line, ptr_file );
    char *token = strtok(line, ",");
    int j = 0;
    while(token != NULL && j < nrJogadas ){
        jogadas[j] = token;
        j++;
        token = strtok(NULL, ",");
    }
    char* play = jogadas[0];
    x = play[0] - 48;
    y = play[2] - 48;
    PontuacaoFinal += jogada(nrLinhas,tabuleiro,x,y);
    //fazer primeira jogada
    for (int k = 1; k < nrJogadas ; ++k) { //restantes
        char* play = jogadas[k];
        x = play[1] - 48;
        y = play[3] - 48;
        PontuacaoFinal += jogada(nrLinhas,tabuleiro,x,y);
    }
    fflush(ptr_file);
    fclose(ptr_file);
    printf("Pontuacao Final: %d \n",PontuacaoFinal);

    return PontuacaoFinal;
}