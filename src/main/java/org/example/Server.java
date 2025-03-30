package org.example;
import static spark.Spark.after;
import static spark.Spark.get;

import spark.Spark;


public class Server {



  public static void main(String[] args) {
    int port = 6464;
    Spark.port(port);

    after(
        (request, response) -> {
          response.header("Access-Control-Allow-Origin", "*");
          response.header("Access-Control-Allow-Methods", "*");
        });

    ImageFile imageFile1 = new ImageFile();
    ImageFile candidateImage = new ImageFile();

    Spark.get("loadimage",new LoadImageHandler(imageFile1,candidateImage));
    Spark.get("viewimage", new ViewImageHandler(imageFile1));
    Spark.get("viewimage2", new ViewImageHandler(candidateImage));
    Spark.get("runscanner",new RunScannerHandler(imageFile1,candidateImage));

    Spark.init();
    Spark.awaitInitialization();

    System.out.println("Server started at http://localhost:" + port);
  }

}