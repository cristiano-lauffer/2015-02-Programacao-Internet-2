/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Marcacao;
import model.Usuario;

/**
 * Jersey REST client generated for REST resource:UsuariosResource
 * [usuarios]<br>
 * USAGE:
 * <pre>
 *        ClientUsuario client = new ClientUsuario();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Cristiano
 */
public class ClientUsuario {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/2015-02-Programacao-Internet-2/webresources";

    public ClientUsuario() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("usuarios");
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public String login(Object requestEntity) throws ClientErrorException {
        String strJson = (new Gson()).toJson(requestEntity);
        Response response = webTarget.path("login").request().post(Entity.entity(strJson, MediaType.APPLICATION_JSON));

        return response.readEntity(String.class);
    }

    public ArrayList<Marcacao> ExibirMarcacoes(long idUsuario) throws ClientErrorException {
        String strJson = (new Gson()).toJson(idUsuario);
        Response response = webTarget.path("marcacoes_usuario").request().post(Entity.entity(strJson, MediaType.APPLICATION_JSON_TYPE));

        String strJsonResponse = response.readEntity(String.class);

        Type collectionType = new TypeToken<ArrayList<Marcacao>>() {
        } // end new
                .getType();
        ArrayList<Marcacao> listaMarcacoes = (new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()).fromJson(strJsonResponse, collectionType);

        return listaMarcacoes;
    }

    public Marcacao salvarMarcacaoDiaria(long idUsuario, String dtEntrada, String dtSaida) throws ClientErrorException {
        Marcacao marcacaoDia = null;
        try {
            Date _dtEntrada = null;
            Date _dtSaida = null;
            String strIdUsuario = (new Gson()).toJson(idUsuario);
            Response response = webTarget.path("buscar_usuario").request().post(Entity.entity(strIdUsuario, MediaType.APPLICATION_JSON_TYPE));

            String strJson = response.readEntity(String.class);
            Usuario usuario = (new Gson()).fromJson(strJson, Usuario.class);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            if (!dtEntrada.trim().equals("")) {
                _dtEntrada = simpleDateFormat.parse("01/01/1970 " + dtEntrada);
            }

            if (!dtSaida.trim().equals("")) {
                _dtSaida = simpleDateFormat.parse("01/01/1970 " + dtSaida);
            }

            marcacaoDia = new Marcacao(new Date(), _dtEntrada, _dtSaida, usuario);

            String strJsonMarcacao = (new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()).toJson(marcacaoDia);
            response = webTarget.path("adiciona_marcacao_diaria").request().post(Entity.entity(strJsonMarcacao, MediaType.APPLICATION_JSON_TYPE));

            marcacaoDia = (new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()).fromJson(response.readEntity(String.class), Marcacao.class);
            System.out.println(marcacaoDia.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return marcacaoDia;
    }

    public Marcacao BuscarMarcacaoDiaria(Long idUsuario) throws ClientErrorException {
        //return webTarget.path("login").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);

        String strJson = (new Gson()).toJson(idUsuario);
        Response response = webTarget.path("marcacao_diaria").request().post(Entity.entity(strJson, MediaType.APPLICATION_JSON));

        String strJsonRetorno = null;//response.readEntity(String.class);
        Object marcacaoRetorno = response.readEntity(Object.class);

        Marcacao marcacao = (new Gson()).fromJson(strJsonRetorno, Marcacao.class);

        return marcacao;
    }

    public <T> T getUsuarios(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        Response resp = resource.request(MediaType.APPLICATION_JSON).get();
        //System.out.println(resp.getStatus());
        //System.out.println(resp.getHeaders().toString());
        //System.out.println(resp.readEntity(String.class));
        //System.out.println(resp.readEntity(List.class));
        //System.out.println(resp.readEntity(responseType));
        //System.out.println(resource.);

        //Gson gson = new Gson();        
        //String strResponse = resp.readEntity(String.class);        
        //return gson.fromJson(strResponse, (Class<T>) responseType.getClass());
        Object objRetorno = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        //return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        return (T) objRetorno;
    }

    public ArrayList<Usuario> getUsuarios() throws ClientErrorException {
        WebTarget resource = webTarget;
        Response resp = resource.request(MediaType.APPLICATION_JSON).get();

        String strJson = resp.readEntity(String.class);

        Type collectionType = new TypeToken<ArrayList<Usuario>>() {
        } // end new
                .getType();

        return (new Gson()).fromJson(strJson, collectionType);
    }

    public void close() {
        client.close();
    }

}
