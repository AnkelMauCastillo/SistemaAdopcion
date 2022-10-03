package mx.edu.uacm.sistema.adopta.conexion;

import mx.edu.uacm.sistema.adopta.modelo.Usuario;
import mx.edu.uacm.sistema.adopta.repositorio.UsuarioRepositorio;
import mx.edu.uacm.sistema.adopta.repositorio.UsuarioRepositorioImpl;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ConexionBDTest {


    @Test
    public void pruebaInstancia(){
        try(Connection conn = ConexionBD.getInstance();
            Statement stmt = conn.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * from USUARIOS")

        ) {
            while (resultado.next()){
                System.out.print(resultado.getInt("id_usuario"));
                System.out.print("|");
                System.out.print(resultado.getInt("id_rol_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("nombre_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("apellido_paterno_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("apellido_materno_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("fecha_nacimiento"));
                System.out.print("|");
                System.out.print(resultado.getString("genero_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("email_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("edad_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("calle_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("codigo_postal_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("alcaldia"));
                System.out.print("|");
                System.out.print(resultado.getString("colonia"));
                System.out.print("|");
                System.out.print(resultado.getString("num_exterior"));
                System.out.print("|");
                System.out.print(resultado.getString("num_interior"));
                System.out.print("|");
                System.out.print(resultado.getString("cel_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("tel_fijo_usuario"));
                System.out.print("|");
                System.out.print(resultado.getString("comp_domicilio_arch"));
                System.out.print("|");
                System.out.println(resultado.getInt("identificacion_arch"));



            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void pruebaRepo() {
        try (Connection conn = ConexionBD.getInstance()

        ) {
            UsuarioRepositorio<Usuario> usuarioRepositorio = new UsuarioRepositorioImpl();
            usuarioRepositorio.listar().forEach(System.out::println);

            System.out.println("Busqueda por Id");
            //System.out.println(usuarioRepositorio.porId(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}