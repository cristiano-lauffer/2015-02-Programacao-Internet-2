/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Arrays;
import util.Console;
import view.menu.MainMenu;

/**
 *
 * @author Cristiano
 */
public class MainUI {

    public MainUI() {
    }

    public void executar() {
        int opcao = MainMenu.OP_SAIR;
        long idUsuario = -1;

        do {
            try {
                System.out.println(MainMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case MainMenu.OP_LOGIN:
                        idUsuario = (new LoginUI()).executar();
                        break;
                    case MainMenu.OP_MARCACOES:
                        new MarcacaoUI().executar(idUsuario);
                        break;
                    case MainMenu.OP_SAIR:
                        System.out.println("Aplicação finalizada!!!");
                        break;
                    default:
                        System.out.println("Opção inválida..");

                }
            } catch (Exception e) {
                System.out.println("Erro encontrado:");
                System.out.println(Arrays.toString(e.getStackTrace()));
                opcao = MainMenu.OP_ERRO;
            }
        } while (opcao != MainMenu.OP_SAIR);
    }
}
