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
public class LoginMenu {

    public static final int OP_LOGIN = 1;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + OP_LOGIN + "- Efetuar Login\n"
                + OP_VOLTAR + "- Voltar"
                + "\n--------------------------------------");
    }
}
