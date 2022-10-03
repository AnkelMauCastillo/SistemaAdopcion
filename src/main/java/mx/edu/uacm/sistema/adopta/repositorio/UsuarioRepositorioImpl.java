package mx.edu.uacm.sistema.adopta.repositorio;

import mx.edu.uacm.sistema.adopta.conexion.ConexionBD;
import mx.edu.uacm.sistema.adopta.modelo.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRepositorioImpl implements UsuarioRepositorio<Usuario>{

    private Connection geConnection() throws SQLException {
        return ConexionBD.getInstance();
    }
    @Override
    public List<Usuario> listar() {
        return null;
    }

    @Override
    public Usuario porId(int id) {
        return null;
    }

    @Override
    public void guardar(Usuario usuario) {

    }

    @Override
    public void eliminar(int id) {

    }
}
