//
// Created by jmmar on 08-01-2019.
//

#ifndef COLORSQUARE_COLORSQUARES_H
#define COLORSQUARE_COLORSQUARES_H

void mostrar(int sz, int tabuleiro[sz][sz]);

int marcar( int sz, int x, int y, int tabuleiro[sz][sz]);

void gravidade( int sz, int tabuleiro[sz][sz]);

void coluna( int sz, int tabuleiro[sz][sz]);

int pontuacao(int num_quadrados);

int jogada(int sz, int tabuleiro[sz][sz], int x, int y);


#endif //COLORSQUARE_COLORSQUARES_H
