package mx.edu.uacm.sistema.adopta.repositorio;

import mx.edu.uacm.sistema.adopta.conexion.ConexionBD;
import mx.edu.uacm.sistema.adopta.modelo.Usuario;

import java.io.File;
import java.sql.*;
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
    public Usuario porId(Long id) {
        Usuario usuario = null;
        try (PreparedStatement stmt = geConnection().prepareStatement("SELECT  * FROM  usuarios WHERE ID_USUARIO= ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = crearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }



    @Override
    public void guardar(Usuario usuario) {
        String sql;
        if ((usuario.getIdUsuario() != null) && usuario.getIdUsuario()>0) {
            sql = "UPDATE usuarios Set `id_rol_usuario`=?,`nombre_usuario`=?, `fecha_nacimiento`=?   where id_usuario=?";
        } else {
            sql = "INSERT INTO `usuarios` (`id_rol_usuario`, `nombre_usuario`,  `fecha_nacimiento`,  `email_usuario`) VALUES (?, ?, ?, ?)";
        }
        try(PreparedStatement stmt = geConnection().prepareStatement(sql)) {
            stmt.setLong(1, usuario.getIdRolUsuario());
            stmt.setString(2, usuario.getNombreUsuario());
            stmt.setDate(3, new Date(usuario.getFechaNcimientoUsuario().getTime()));

            if ((usuario.getIdUsuario() != null) && usuario.getIdUsuario()>0) {
                stmt.setLong(4, usuario.getIdUsuario());
            } else {
                stmt.setString(4, usuario.getEmailUsuario());

            }

            stmt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    @Override
    public void eliminar(Long id) {
        try (PreparedStatement stmt = geConnection().prepareStatement("DELETE From usuarios WHERE  id_usuario=?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }

    private static Usuario crearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getLong("id_usuario"));
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
