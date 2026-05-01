class Main {

    // Função que verifica se é seguro colocar um número na posição [row][col]
    static boolean isSafe(int[][] mat, int row, int col, int num) {

        for (int x = 0; x < 9; x++)  // Verifica se já há o num na linha
            if (mat[row][x] == num)
                return false;

        for (int x = 0; x < 9; x++)  // Verifica se já há o num na coluna
            if (mat[x][col] == num)
                return false;

        int startRow = row - (row % 3);   // Verifica se já existe numero no bloco 3x3
        int startCol = col - (col % 3);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (mat[i + startRow][j + startCol] == num)
                    return false;

        return true; // Se passou em todas as verificaçoes, é válido
    }

    static boolean solveSudokuRec(int[][] mat, int row, int col) { // Função RECURSIVA principal

        if (row == 8 && col == 9)       // CONDIÇÃO DE PARADA:
            return true;                // Se chegou ao final da matriz, solução encontrada

        if (col == 9) {                 // Se chegou ao fim da coluna, vai para a próxima linha
            row++;
            col = 0;
        }

        if (mat[row][col] != 0)         // Se a célula possui um valor que seja diferente de 0, vai para a proxima
            return solveSudokuRec(mat, row, col + 1);

        for (int num = 1; num <= 9; num++) { // TestE de números de 1-9

            // Verifica se a escolha é válida
            if (isSafe(mat, row, col, num)) {

                //inseri o numero e avança recursivamente
                mat[row][col] = num;

                // Se a recursao a partir daqui tiver resultado, retorna true
                if (solveSudokuRec(mat, row, col + 1))
                    return true;

                /* Caso a solução nao levou a um resultado váçido o BACKTRACKING (desfaz a escolha)
                 o for testa um outro numero*/
                mat[row][col] = 0;
            }
        }

        return false;  // Caso nenhum nao funcione, retorna false
    }

    // Função principal que inicia o processo
    static void solveSudoku(int[][] mat) {
        solveSudokuRec(mat, 0, 0);
    }

    public static void main(String[] args) {

        int[][] mat = {                         // Matriz representando o tabuleiro inicial
                {3, 0, 6, 5, 0, 8, 4, 0, 0},    // 0 representa a celula vazia
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        solveSudoku(mat); // resolve o sudoku

        for (int i = 0; i < mat.length; i++) {      //Imprime o tabuleiro final
            for (int j = 0; j < mat[i].length; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }
}














