package com.jgsolucion.prueba.controller;

import com.jgsolucion.prueba.model.Usuario;
import com.jgsolucion.prueba.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioServices usuarioService;

    @GetMapping("/get")
    public Usuario getUsuario(@RequestParam String id ) throws InterruptedException, ExecutionException {
        return usuarioService.getUsuario(id);
    }

    @GetMapping("/getAll")
    public List<Usuario> getUsuarios() throws InterruptedException, ExecutionException {
        return usuarioService.getUsuarios();
    }

    @PostMapping("/create")
    public String createUsuario(@RequestBody Usuario usuario ) throws InterruptedException, ExecutionException {
        return usuarioService.saveUsuario(usuario);
    }

    @PutMapping("/update")
    public String updateUsuario(@RequestBody Usuario usuario  ) throws InterruptedException, ExecutionException {
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("/delete")
    public String deleteUsuario(@RequestParam String id){
        return usuarioService.deleteUsuario(id);
    }
}
