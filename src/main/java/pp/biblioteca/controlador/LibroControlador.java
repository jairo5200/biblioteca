package pp.biblioteca.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.biblioteca.excepcion.RecursoNoEncontradoExcepcion;
import pp.biblioteca.modelo.Libro;
import pp.biblioteca.servicio.LibroServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/biblioteca-app
@RequestMapping("biblioteca-app")
@CrossOrigin(value = "http://localhost:4200")
public class LibroControlador {

    private  static final Logger logger = LoggerFactory.getLogger(LibroControlador.class);

    @Autowired
    private LibroServicio libroServicio;

    //http://localhost:8080/biblioteca-app/libros
    @GetMapping("/libros")
    public List<Libro> obtenerLibros(){
        List<Libro> libros = this.libroServicio.listarLibros();
        return libros;
    }

    //http://localhost:8080/biblioteca-app/libros
    @PostMapping("/libros")
    public Libro agregarLibro(@RequestBody Libro libro){
        Libro elLibro = this.libroServicio.guardarLibro(libro);
        return elLibro;
    }

    //http://localhost:8080/biblioteca-app/libros/id
    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable int id) {
        Libro libro = this.libroServicio.buscarLibroPorId(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
             throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
    }

    //http://localhost:8080/biblioteca-app/libros/id
    @PutMapping("/libros/{idLibro}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable int idLibro, @RequestBody Libro libro){
        Libro elLibro = this.libroServicio.buscarLibroPorId(idLibro);
        elLibro.setNombre(libro.getNombre());
        elLibro.setDescripcion(libro.getDescripcion());
        elLibro.setPrecio(libro.getPrecio());
        elLibro.setExistencia(libro.getExistencia());
        this.libroServicio.guardarLibro(elLibro);
        return ResponseEntity.ok(elLibro);
    }

    //http://localhost:8080/biblioteca-app/libros/id
    @DeleteMapping("/libros/{idLibro}")
    public ResponseEntity<Map<String,Boolean>> eliminarLibro(@PathVariable int idLibro){
        Libro elLibro = this.libroServicio.buscarLibroPorId(idLibro);
        if (elLibro != null){
            this.libroServicio.eliminarLibroPorId(elLibro.getIdLibro());
            Map<String,Boolean> respuesta = new HashMap<>();
            respuesta.put("eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        }else{
            throw new RecursoNoEncontradoExcepcion("no existe el libro con el id: " + idLibro);
        }
    }
}
