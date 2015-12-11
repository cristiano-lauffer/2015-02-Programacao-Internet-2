/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 *
 * @author Cristiano
 */
public class MarcacaoMenu {

    public static final int OP_EXIBIR_MARCACAO_DIA = 1;
    public static final int OP_EDITAR_MARCACAO_DIA = 2;
    public static final int OP_EXIBIR_MARCACOES = 3;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + OP_EXIBIR_MARCACAO_DIA + "- Exibir Marcação do dia\n"
                + OP_EDITAR_MARCACAO_DIA + "- Editar Marcação do dia\n"
                + OP_EXIBIR_MARCACOES + "- Exibir Marcações\n"
                + OP_VOLTAR + "- Voltar"
                + "\n--------------------------------------");
    }
}
