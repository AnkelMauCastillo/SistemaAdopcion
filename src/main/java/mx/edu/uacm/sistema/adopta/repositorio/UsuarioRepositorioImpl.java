package mx.edu.uacm.sistema.adopta.repositorio;

import mx.edu.uacm.sistema.adopta.conexion.ConexionBD;
import mx.edu.uacm.sistema.adopta.modelo.Usuario;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorioImpl implements UsuarioRepositorio<Usuario>{

    private Connection geConnection() throws SQLException {
        return ConexionBD.getInstance();
    }
    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Statement stmt = geConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT  * FROM usuarios")){
            while (rs.next()){
                Usuario u = crearUsuario(rs);
                usuarios.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
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

    private static Usuario crearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setIdRolUsuario(rs.getInt("id_rol_usuario"));
        u.setNombreUsuario(rs.getString("nombre_usuario"));
        u.setApellidoPaterno(rs.getString("apellido_paterno_usuario"));
        u.setApellidoMaterno(rs.getString("apellido_materno_usuario"));
        u.setFechaNcimientoUsuario(rs.getDate("fecha_nacimiento"));
        u.setGeneroUsuario(rs.getString("genero_usuario"));
        u.setEmailUsuario(rs.getString("email_usuario"));
        u.setEdadUsuario(rs.getInt("edad_usuario"));
        u.setCalleUsuario(rs.getString("calle_usuario"));
        u.setCodigoPostalUsuario(rs.getInt("codigo_postal_usuario"));
        u.setAlcaldia(rs.getString("alcaldia"));
        u.setColonia(rs.getString("colonia"));
        u.setNumeroInterior(rs.getInt("num_interior"));
        u.setNumeroExterior(rs.getInt("num_exterior"));
        u.setCelUsuario(rs.getInt("cel_usuario"));
        u.setTelFijoUsuario(rs.getInt("tel_fijo_usuario"));
        u.setComprobanteDomicilioFile((File) rs.getBlob("comp_domicilio_arch"));
        u.setIdentificacionOficialFile((File) rs.getBlob("identificacion_arch"));
        return u;
    }
}
