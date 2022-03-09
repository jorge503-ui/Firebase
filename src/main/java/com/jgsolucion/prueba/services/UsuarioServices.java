package com.jgsolucion.prueba.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.jgsolucion.prueba.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UsuarioServices {
    public static final String COL_NAME="usuarios";

    public String saveUsuario(Usuario usuario) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionsApiFuture = dbFirestore.collection(COL_NAME).add(usuario);
        usuario.setId(collectionsApiFuture.get().getId());
        return collectionsApiFuture.get().getId();
    }

    public Usuario getUsuario(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Usuario usuario = null;

        if(document.exists()) {
            usuario = document.toObject(Usuario.class);
            usuario.setId(document.getId());
            return usuario;
        }else {
            return null;
        }
    }

    public List<Usuario> getUsuarios() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        List<Usuario> usuarios = new ArrayList<>();

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Usuario u = (document.toObject(Usuario.class));
            u.setId(document.getId());
            usuarios.add(u);
        }
        return usuarios;
    }

    public String updateUsuario(Usuario usuario) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(usuario.getId()).set(usuario);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUsuario(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with Usuario ID "+id+" has been deleted";
    }
}
