package com.jgsolucion.prueba.controller;

import com.jgsolucion.prueba.commons.ResponseMessage;
import com.jgsolucion.prueba.model.Usuario;
import com.jgsolucion.prueba.services.UsuarioServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {
    private static final Logger logger = LogManager.getLogger(UsuarioController.class);
    private final ResponseMessage response = new ResponseMessage();

    @Autowired
    UsuarioServices services;

    @GetMapping("/get")
    public ResponseEntity<?> getUsuario(@RequestParam String id ) {
        Map<String, Object> rsp;
        Usuario usuario;
        try {
            usuario = services.findById(id);
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("usuario",usuario);
        }catch (Exception e){
            logger.error("Ocurrio un error al obtener usuario", e);
            rsp = response.messageException(HttpStatus.INTERNAL_SERVER_ERROR.value(),e);
        }
        return response.messageResponse(rsp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getUsuarios() {
        Map<String, Object> rsp;
        logger.info("Obteniendo lista de usuarios");
        List<Usuario> usuarios;
        try {
            usuarios = services.findAll();
            logger.info("Usuarios obtenidos exitosamente");
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("usuarios",usuarios);
        }catch (Exception e){
            logger.error("Ocurrio un error al listar los usuarios", e);
            rsp = response.messageException(HttpStatus.INTERNAL_SERVER_ERROR.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario ) {
        Map<String, Object> rsp;
        logger.info("Creando usuario " + usuario.getNombre());
        logger.info("Creando usuario en BD");
        try {
            services.save(usuario,usuario.getId());
            logger.info("Usuario Creado exitosamente");

            //Respuesta
            logger.info("Generando respuesta");
            rsp = response.messageOk(HttpStatus.OK.value());
            rsp.put("uid",usuario.getId());
        } catch (Exception e) {
            logger.error("Ocurrio un error al crear usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Respuesta: " + rsp.get("uid"));
        return response.messageResponse(rsp);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario  ) {
        Map<String, Object> rsp;
        logger.info("Actualizando usuario " + usuario.getId());
        logger.info("Actualizando usuario en BD");
        try {
            services.update(usuario);
            logger.info("Actualizado correctamente");

            //Respuesta
            rsp = response.messageOk(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error("Ocurrio un error al actualizar usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUsuario(@RequestParam String id){
        Map<String, Object> rsp;
        logger.info("Eliminando usuario " + id);
        logger.info("Eliminando usuario en BD");
        try {
            services.deleteById(id);
            logger.info("Eliminado correctamente en BD");
            //Respuesta
            rsp = response.messageOk(HttpStatus.OK.value());
        }catch (Exception e){
            logger.error("Ocurrio un error al eliminar usuario", e);
            rsp = response.messageException(HttpStatus.BAD_REQUEST.value(),e);
        }
        logger.info("Enviando respuesta");
        return response.messageResponse(rsp);
    }
}
