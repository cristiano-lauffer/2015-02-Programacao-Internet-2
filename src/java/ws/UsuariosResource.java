/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import model.Marcacao;
import model.Usuario;

/**
 * REST Web Service
 *
 * @author Cristiano
 */
@Path("usuarios")
public class UsuariosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuariosResource
     */
    public UsuariosResource() {
    }

    /**
     * Retrieves representation of an instance of ws.UsuariosResource
     *
     * @return an instance of model.Usuario
     */
    @GET
    @Produces("application/json")
    public List<Usuario> getJson() {
        List<Usuario> retorno = null;
        try {
            return (new dao.UsuarioDao()).getArrayListUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(UsuariosResource.class.getName()).log(Level.SEVERE, null, ex);
            return retorno;
        }
    }

    /**
     * PUT method for updating or creating an instance of UsuariosResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(Usuario content) {
    }

    @POST
    @Consumes("application/json")
    @Produces("text/plan")
    @Path("/login")
    public String login(Usuario usuario) {
        Usuario objUsuarioRetorno = null;
        try {
            objUsuarioRetorno = (new dao.UsuarioDao()).buscar(usuario.getUsuarioSistema(), usuario.getSenha());
        } catch (Exception e) {
        }

        if (objUsuarioRetorno != null) {
            return Long.toString(objUsuarioRetorno.getId());
        } else {
            return "-1";
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/marcacao_diaria")
    public Marcacao BuscaMarcacaoDiaria(Long idUsuario) {
        Marcacao objRetorno = null;
        try {
            objRetorno = (new dao.MarcacaoDao().BuscarMarcacaoDataAtual((new dao.UsuarioDao().buscarPorId(idUsuario))));
        } catch (Exception e) {
        }
        System.out.println("Teste BuscaMarcacaoDiaria " + objRetorno.toString());

        return objRetorno;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/marcacoes_usuario")
    public List<Marcacao> listarMarcacoes(String idUsuario) {
        //TODO return proper representation object
        List<Marcacao> retorno = null;
        try {
            Usuario usuario = (new dao.UsuarioDao()).buscarPorId(Long.parseLong(idUsuario));

            return (new dao.MarcacaoDao()).getArrayList(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosResource.class.getName()).log(Level.SEVERE, null, ex);
            return retorno;
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/adiciona_marcacao_diaria")
    public Marcacao salvarMarcacaoDiaria(Marcacao marcacao) {
        //TODO return proper representation object
        Marcacao retorno = null;
        try {
            if (util.Util.isDataMaior(marcacao.getDtEntrada(), marcacao.getDtSaida())) {
                return null;
            } else {
                (new dao.MarcacaoDao()).salvar(marcacao);
                retorno = marcacao;
            }

        } catch (Exception ex) {
            Logger.getLogger(UsuariosResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/buscar_usuario")
    public Usuario buscarUsuario(String idUsuario) {
        Usuario retorno = null;
        try {
            return (new dao.UsuarioDao()).buscarPorId(Long.parseLong(idUsuario));
        } catch (Exception ex) {
            Logger.getLogger(UsuariosResource.class.getName()).log(Level.SEVERE, null, ex);
            return retorno;
        }
    }
}
