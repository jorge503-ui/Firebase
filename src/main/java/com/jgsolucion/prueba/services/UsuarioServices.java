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
public class UsuarioServices implements CommonServices<Usuario> {
    public static final String COL_NAME="usuarios";

    @Override
    public List<Usuario> findAll() throws ExecutionException, InterruptedException {
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

    @Override
    public Usuario findById(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Usuario usuario = new Usuario();

        if(document.exists()) {
            usuario = document.toObject(Usuario.class);
            usuario.setId(document.getId());
            return usuario;
        }else {
            return null;
        }
    }

    @Override
    public Usuario save(Usuario entity, String id) {
        return null;
    }

    @Override
    public Usuario save(Usuario entity) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionsApiFuture = dbFirestore.collection(COL_NAME).add(entity);
        entity.setId(collectionsApiFuture.get().getId());
        return entity;
    }

    @Override
    public Usuario update(Usuario entity) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(entity.getId()).set(entity);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
    }
}
