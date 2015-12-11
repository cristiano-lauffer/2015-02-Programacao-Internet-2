/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.SimpleDateFormat;
import java.util.List;
import model.Marcacao;
import model.Usuario;
import util.Console;
import util.Util;
import view.menu.MarcacaoMenu;
import ws.ClientUsuario;

/**
 *
 * @author Cristiano
 */
public class MarcacaoUI {

    public MarcacaoUI() {
    }

    public void executar(long idUsuario) {
        int opcao = 0;

        do {
            System.out.println(MarcacaoMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case MarcacaoMenu.OP_EXIBIR_MARCACAO_DIA:
                    //idUsuario = efetuarLogin();
                    break;
                case MarcacaoMenu.OP_EDITAR_MARCACAO_DIA:
                    marcacaoDiaria(idUsuario);
                    break;
                case MarcacaoMenu.OP_EXIBIR_MARCACOES:
                    ExibirMarcacoes(idUsuario);
                    break;
                case MarcacaoMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != MarcacaoMenu.OP_VOLTAR);
    }

    private void ExibirMarcacoes(long idUsuario) {
        ClientUsuario cUsuario = new ClientUsuario();
        try {
            List<Marcacao> listaMarcacoes = cUsuario.ExibirMarcacoes(idUsuario);

            for (Marcacao marcacao : listaMarcacoes) {
                System.out.print("Data: [" + (new SimpleDateFormat("dd/MM/yyyy").format(marcacao.getDtMarcacao())) + "]");

                if (marcacao.getDtEntrada() != null) {
                    System.out.print(" - Entrada: [" + (new SimpleDateFormat("HH:mm:ss").format(marcacao.getDtEntrada())) + "]");
                } else {
                    System.out.print(" - Entrada: []");
                }

                if (marcacao.getDtSaida() != null) {
                    System.out.print(" - Saída: [" + (new SimpleDateFormat("HH:mm:ss").format(marcacao.getDtSaida())) + "]");
                } else {
                    System.out.print(" - Saída: []");
                }

                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
        } else {
            System.out.println("Usuário não encontrado!");
        }

        return idUsuario;
    }

    private void marcacaoDiaria(long idUsuario) {
        ClientUsuario cUsuario = new ClientUsuario();
        try {
            String dtEntrada = Console.scanString("Entrada [HH:mm]: ");
            String dtSaida = Console.scanString("Saída [HH:mm]: : ");
            
            Marcacao marcacao = cUsuario.salvarMarcacaoDiaria(idUsuario, dtEntrada, dtSaida);
            
            System.out.println("Foi!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
