package com.jgsolucion.prueba.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FBInitialize {


    @PostConstruct
    public void initialize() {
        try {
            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            if(firebaseApps!=null && !firebaseApps.isEmpty()){
                for (FirebaseApp app:
                     firebaseApps) {
                    if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
                        firebaseApps= (List<FirebaseApp>) app;
                }
            }else {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .setDatabaseUrl("https://reslyapp.firebaseio.com/")
                        .build();

                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
