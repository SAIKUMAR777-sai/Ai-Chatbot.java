/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatbot.controller;

import chatbot.model.ChatbotModel;
import chatbot.view.ChatView;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/** CONTROLLER: connects the browser (View) with the chatbot (Model). */
public class ChatController {

    private final ChatbotModel model;

    public ChatController(ChatbotModel model) {
        this.model = model;
    }

    // Browser opens the page -> send the View
    public void handlePage(HttpExchange ex) throws IOException {
        send(ex, "text/html; charset=utf-8", ChatView.renderPage());
    }

    // Browser sends a message -> ask the Model -> send back the reply
    public void handleChat(HttpExchange ex) throws IOException {
        String message = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        String reply = model.reply(message);
        send(ex, "text/plain; charset=utf-8", reply);
    }

    private void send(HttpExchange ex, String type, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", type);
        ex.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }
}