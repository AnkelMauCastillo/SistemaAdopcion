package mx.edu.uacm.sistema.adopta.repositorio;

import java.util.List;

public interface UsuarioRepositorio <T>{

    List<T> listar();

    T porId(int id);

    void guardar(T t);

    void eliminar(int id);


}
