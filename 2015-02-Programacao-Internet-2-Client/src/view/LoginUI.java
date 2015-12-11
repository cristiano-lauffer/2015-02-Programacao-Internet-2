/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Usuario;
import util.Console;
import util.Util;
import view.menu.LoginMenu;
import ws.ClientUsuario;

/**
 *
 * @author Cristiano
 */
public class LoginUI {

    public LoginUI() {
    }

    public long executar() {
        int opcao = 0;
        long idUsuario = -1;
        do {
            System.out.println(LoginMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case LoginMenu.OP_LOGIN:
                    idUsuario = efetuarLogin();
                    break;
                case LoginMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != LoginMenu.OP_VOLTAR);

        return idUsuario;
    }

    private long efetuarLogin() {
        long idUsuario = -1;
//        ClienteDao dao = new ClienteDao();
//        String cpf = Console.scanString("CPF: ");
//
//        if (Util.isCPF(cpf)) {
//            if (dao.clienteExiste(cpf)) {
//                System.out.println("CPF já existente no cadastro");
//            } else {
//                String nome = Console.scanString("Nome: ");
//                String e_mail = Console.scanString("E-mail: ");
//                dao.inserir(new Cliente(nome, cpf.trim(), e_mail));
//                System.out.println("Cliente (" + nome + ") cadastrado com sucesso!");
//            }
//        } else {
//            System.out.println("CPF inválido!");
//        }

        ClientUsuario cUsuario = new ClientUsuario();
        String usuario = Console.scanString("Usuario: ");
        String senha = Console.scanString("Senha: ");

        System.out.println("Efetuando login...");
        String strRetorno = cUsuario.login((new Usuario(usuario, senha)));
        System.out.println(strRetorno);

        if (!strRetorno.equals("")) {
            System.out.println("Usuário " + strRetorno + " logado.");
            idUsuario = Integer.parseInt(strRetorno);
        } else{
            System.out.println("Usuário não encontrado!");
        }

        return idUsuario;
    }

}
