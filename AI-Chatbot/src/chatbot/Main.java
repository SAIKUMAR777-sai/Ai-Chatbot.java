/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package chatbot;

import chatbot.controller.ChatController;
import chatbot.model.ChatbotModel;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {

        ChatbotModel model = new ChatbotModel();
        ChatController controller = new ChatController(model);

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0
        );

        server.createContext("/", controller::handlePage);
        server.createContext("/chat", controller::handleChat);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop(0);
            System.out.println("Chatbot server stopped.");
        }));

        server.start();

        System.out.println("Chatbot running at http://localhost:8080");
    }
}