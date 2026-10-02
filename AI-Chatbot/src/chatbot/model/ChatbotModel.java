/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatbot.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/** MODEL: the chatbot's brain. It gets a message and returns a reply. */
public class ChatbotModel {

    private static final double MIN_SCORE = 0.5;   // how similar a question must be to answer it

    // Knowledge base: question -> answer (the FAQs)
    private final Map<String, String> faqs = new LinkedHashMap<>();

    public ChatbotModel() {
        faqs.put("What is Java", "Java is a high-level, object-oriented, platform-independent programming language.");
        faqs.put("What is OOP", "OOP (Object-Oriented Programming) organizes code into objects. Its pillars are encapsulation, inheritance, polymorphism and abstraction.");
        faqs.put("What is the JVM", "The JVM (Java Virtual Machine) runs compiled Java bytecode and makes Java platform independent.");
        faqs.put("Difference between JDK JRE and JVM", "JVM runs bytecode, JRE = JVM + libraries, JDK = JRE + development tools like javac.");
        faqs.put("What is an ArrayList", "ArrayList is a resizable array from java.util that implements the List interface.");
        faqs.put("What is multithreading", "Multithreading runs multiple threads at the same time using the Thread class or Runnable interface.");
        faqs.put("What is inheritance", "Inheritance lets a child class reuse the fields and methods of a parent class using 'extends'.");
        faqs.put("What is polymorphism", "Polymorphism means one thing in many forms: method overloading (compile time) and overriding (runtime).");
        faqs.put("What is encapsulation", "Encapsulation hides data using private fields and gives access through getters and setters.");
        faqs.put("What is abstraction", "Abstraction hides complex details and shows only essentials, using abstract classes and interfaces.");
        faqs.put("What is Spring", "Spring is a Java framework for building applications, known for dependency injection and Spring Boot.");
        faqs.put("What is Hibernate", "Hibernate is an ORM framework that maps Java objects to database tables.");
    }

    public synchronized String reply(String input) {
        String text = input.trim();
        String lower = text.toLowerCase();

        if (text.isEmpty()) {
            return "Please type something.";
        }

        // 1. TRAINING - the user teaches the bot a new FAQ:  teach: question | answer
        if (lower.startsWith("teach:")) {
            String[] parts = text.substring(6).split("\\|", 2);
            if (parts.length == 2 && !parts[0].trim().isEmpty() && !parts[1].trim().isEmpty()) {
                faqs.put(parts[0].trim(), parts[1].trim());
                return "Thanks! I have learned that.";
            }
            return "Format:  teach: question | answer";
        }

        // 2. RULE-BASED answers - fixed replies for fixed patterns
        if (lower.matches(".*\\b(hi|hello|hey)\\b.*")) {
            return "Hello! How can I help you?";
        }
        if (lower.matches(".*\\b(bye|goodbye)\\b.*")) {
            return "Goodbye! Have a nice day.";
        }
        if (lower.contains("help")) {
            return "Ask me about Java (OOP, JVM, ArrayList, multithreading...). Teach me with:  teach: question | answer";
        }

        // 3. NLP MATCHING - find the FAQ question most similar to the user's message
        Set<String> userWords = NlpProcessor.process(text);
        String bestAnswer = null;
        double bestScore = 0;
        for (Map.Entry<String, String> faq : faqs.entrySet()) {
            Set<String> faqWords = NlpProcessor.process(faq.getKey());
            double score = NlpProcessor.similarity(userWords, faqWords);
            if (score > bestScore) {
                bestScore = score;
                bestAnswer = faq.getValue();
            }
        }

        if (bestScore >= MIN_SCORE) {
            return bestAnswer;
        }
        return "Sorry, I don't know that. You can teach me:  teach: question | answer";
    }
}