package pp.biblioteca.servicio;

import pp.biblioteca.modelo.Libro;

import java.util.List;

public interface ILibroServicio {

    public List<Libro> listarLibros();

    public Libro buscarLibroPorId(Integer idLibro);

    public void guardarLibro(Libro libro);

    public void eliminarLibroPorId(Integer idLibro);
}
