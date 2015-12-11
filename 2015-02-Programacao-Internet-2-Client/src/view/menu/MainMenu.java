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
public class MainMenu {

    public static final int OP_LOGIN = 1;
    public static final int OP_MARCACOES = 2;
    public static final int OP_ERRO = 3;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + OP_LOGIN + "- Login\n"
                + OP_MARCACOES + "- Menu Marcações\n"
                + OP_SAIR + "- Sair da Aplicação"
                + "\n--------------------------------------");
    }
}
