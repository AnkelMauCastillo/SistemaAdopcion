package mx.edu.uacm.sistema.adopta.repositorio;

import mx.edu.uacm.sistema.adopta.conexion.ConexionBD;
import mx.edu.uacm.sistema.adopta.modelo.Usuario;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositorioImplTest {

    @Test
    public void pruebaRepo() {
        try (Connection conn = ConexionBD.getInstance()

        ) {
            UsuarioRepositorio<Usuario> usuarioRepositorio = new UsuarioRepositorioImpl();
            usuarioRepositorio.listar().forEach(System.out::println);

            System.out.println("Busqueda por Id");
            System.out.println(usuarioRepositorio.porId(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void agregarRepo(){
        try (Connection conn = ConexionBD.getInstance()){
            UsuarioRepositorio<Usuario> usuarioRepositorio = new UsuarioRepositorioImpl();
            Usuario usuario = new Usuario();
            usuario.setIdRolUsuario(1);
            usuario.setNombreUsuario("Kari");
            usuario.setEmailUsuario("kariBebe@uacm.edu.mx");
            usuario.setFechaNcimientoUsuario(new Date());
            usuarioRepositorio.guardar(usuario);
            System.out.println("Producto Guardado Con exito");
            pruebaRepo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}