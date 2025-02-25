package br.com.fiap.application;

import br.com.fiap.dao.EstimativaConsumoDao;
import br.com.fiap.dao.SugestaoEconomiaDao;
import br.com.fiap.dao.TipoDispositivoDao;
import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.dto.request.UsuarioLoginRequestDTO;
import br.com.fiap.service.EstimativaConsumoService;
import br.com.fiap.service.SugestaoEconomiaService;
import br.com.fiap.service.TipoDispositivoService;
import br.com.fiap.service.UsuarioService;
import br.com.fiap.util.api.CorsFiltro;
import br.com.fiap.util.database.DatabaseConnection;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class App {

    // DAOs
    public static UsuarioDao usuarioDao;
    public static TipoDispositivoDao tipoDispositivoDao;
    public static EstimativaConsumoDao estimativaConsumoDao;
    public static SugestaoEconomiaDao sugestaoEconomiaDao;

    // Services
    public static UsuarioService usuarioService;
    public static TipoDispositivoService tipoDispositivoService;
    public static EstimativaConsumoService estimativaConsumoService;
    public static SugestaoEconomiaService sugestaoEconomiaService;

    // Server
    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {

        final ResourceConfig rc = new ResourceConfig().packages("br.com.fiap");

        rc.register(new CorsFiltro());

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws Exception {

        // Criando conexao com o BD
        DatabaseConnection databaseConnection = new DatabaseConnection();

        // Criando DAOs
        usuarioDao = new UsuarioDao(databaseConnection);
        tipoDispositivoDao = new TipoDispositivoDao(databaseConnection);
        estimativaConsumoDao = new EstimativaConsumoDao(databaseConnection);
        sugestaoEconomiaDao = new SugestaoEconomiaDao(databaseConnection);

        // Criando services
        usuarioService = new UsuarioService();
        tipoDispositivoService = new TipoDispositivoService();
        estimativaConsumoService = new EstimativaConsumoService();
        sugestaoEconomiaService = new SugestaoEconomiaService();

        // Iniciando servidor
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
