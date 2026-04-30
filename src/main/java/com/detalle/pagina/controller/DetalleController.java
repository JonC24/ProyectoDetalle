package com.detalle.pagina.controller;

import com.detalle.pagina.data.CategoriaData;
import com.detalle.pagina.data.ImagenData;
import com.detalle.pagina.data.MomentoData;
import com.detalle.pagina.domain.Momento;
import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DetalleController {

    private final CategoriaData catData = new CategoriaData();
    private final MomentoData momData = new MomentoData();
    private final ImagenData imgData = new ImagenData();

    @GetMapping("/")
    public String mostrarMenu() {
        return "index"; // Esto cargará el menú principal
    }

    // 1. Ruta para ver el formulario (GET /sorpresa)
    @GetMapping("/sorpresa")
    public String mostrarFormulario(Model model) throws SQLException {
        // Cargamos la lista de categorías desde la base de datos [cite: 24]
        model.addAttribute("categorias", catData.listarCategorias());
        return "formulario"; // Esto buscará formulario.html [cite: 33]
    }

    // 2. Ruta para procesar el envío (POST /enviar)
    @PostMapping("/guardarMomento")
    public String registrarMomento(@RequestParam String descripcion,
            @RequestParam int prioridad,
            @RequestParam int idCategoria) throws SQLException {

        // Creamos el objeto con los datos recibidos [cite: 28]
        Momento nuevo = new Momento(0, descripcion, prioridad, idCategoria);

        // Guardamos usando el procedimiento almacenado
        if (momData.insertarMomento(nuevo)) {
            // Codificamos la descripción para que acepte tildes y la 'ñ'
            String detalleCodificado = URLEncoder.encode(descripcion, StandardCharsets.UTF_8);
            return "redirect:/exito?detalle=" + detalleCodificado;
        }
        return "redirect:/sorpresa?error=true";
    }

   @GetMapping("/galeria")
public String mostrarGaleria(Model model) {
    // Ruta a la carpeta física
    File folder = new File("src/main/resources/static/images/galeria/");
    File[] listOfFiles = folder.listFiles();
    
    List<String> nombresFotos = new ArrayList<>();
    if (listOfFiles != null) {
        for (File file : listOfFiles) {
            if (file.isFile()) {
                // Guardamos solo el nombre del archivo
                nombresFotos.add(file.getName());
            }
        }
    }
    
    model.addAttribute("fotos", nombresFotos);
    return "galeria";
}

    // 3. Ruta de éxito
    @GetMapping("/exito")
    public String pantallaExito(@RequestParam String detalle, Model model) {
        model.addAttribute("detalleEnviado", detalle);
        return "confirmacion"; // Esto buscará confirmacion.html [cite: 36]
    }

    // Sección de Poemas
    @GetMapping("/poemas")
    public String verPoemas(Model model) throws SQLException {
        // Aquí filtramos: solo traemos los momentos que pertenecen a la categoría 'Poemas'
        List<Momento> todos = momData.listarMomentos();
        List<Momento> poemas = todos.stream()
                .filter(m -> m.getIdCategoria() == 4)
                .toList();
        model.addAttribute("titulo", "Poemas para Ti ✍️");
        model.addAttribute("momentos", poemas);
        return "lista_textos";
    }

// Sección de Promesas
    @GetMapping("/promesas")
    public String verPromesas(Model model) throws SQLException {
        List<Momento> todos = momData.listarMomentos();
        List<Momento> promesas = todos.stream()
                .filter(m -> m.getIdCategoria() == 3)
                .toList();
        model.addAttribute("titulo", "Mis Promesas 🤝");
        model.addAttribute("momentos", promesas);
        return "lista_textos";
    }

    @GetMapping("/lienzo")
    public String verLienzoCollage() {
        return "lienzo"; // Usaremos un nuevo archivo HTML
    }
    
}
