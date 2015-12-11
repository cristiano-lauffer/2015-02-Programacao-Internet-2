
import java.util.List;
import javax.ws.rs.core.GenericType;
import model.Marcacao;
import model.Usuario;
import view.MainUI;
import ws.ClientUsuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Cristiano
 */
public class MainUsuario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            ClientUsuario cUsuario = new ClientUsuario();
//            String strRetorno = cUsuario.login((new Usuario("clauffer", "123")));
//            System.out.println(strRetorno);
//            
//            Marcacao marcacao = cUsuario.BuscarMarcacaoDiaria(1L);
//            System.out.println(marcacao.toString());
//            
////
////            System.out.println("strRetorno");
//
////            List<Usuario> usuarios = cUsuario.getUsuarios(new GenericType<List<Usuario>>() {
////            });
////            List<Usuario> usuarios;
////            usuarios = cUsuario.getUsuarios();
////
////            for (Usuario usuario : usuarios) {
////                System.out.println(usuario.toString());
////            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        new MainUI().executar();
    }

}
