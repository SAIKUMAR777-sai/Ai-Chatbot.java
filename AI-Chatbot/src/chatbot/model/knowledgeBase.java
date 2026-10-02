/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*package chatbot.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;*/

/** MODEL: stores all FAQ entries (built-in + taught) and persists taught ones to taught.txt. */
/*public class knowledgeBase {

    private static final Path TAUGHT_FILE = Paths.get("taught.txt");
    private final List<FaqEntry> entries = new ArrayList<>();

    public knowledgeBase() {
        loadDefaults();
        loadTaught();
    }

    public List<FaqEntry> getAll() {
        return entries;
    }

    public void add(String question, String answer) {
        entries.add(new FaqEntry(question, answer));
    }

    /** Adds a new entry and saves it so it survives restarts. */
   /* public void addAndSave(String question, String answer) {
        add(question, answer);
        try {
            String line = question + "|" + answer + System.lineSeparator();
            Files.write(TAUGHT_FILE, line.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Could not save taught.txt: " + e.getMessage());
        }
    }

    private void loadTaught() {
        try {
            if (!Files.exists(TAUGHT_FILE)) return;
            for (String line : Files.readAllLines(TAUGHT_FILE, StandardCharsets.UTF_8)) {
                String[] p = line.split("\\|", 2);
                if (p.length == 2) add(p[0].trim(), p[1].trim());
            }
        } catch (IOException e) {
            System.out.println("Could not read taught.txt: " + e.getMessage());
        }
    }

    private void loadDefaults() {
        add("What is CodeAlpha", "CodeAlpha is a software development company that builds scalable, enterprise-grade applications and runs internship programs.");
        add("What is the internship about", "The Java internship covers Java fundamentals, OOP, data structures, multithreading, file handling, and frameworks like Spring and Hibernate.");
        add("How many tasks must I complete", "You must complete at least 2 (or 3) out of the 4 tasks to receive the certificate.");
        add("What are the internship perks", "Offer letter, QR-verified completion certificate, unique ID certificate, letter of recommendation, placement support and resume building support.");
        add("How do I submit my task", "Upload your code to GitHub in a repo named CodeAlpha_ProjectName, post a LinkedIn video explaining it, then submit via the form shared in your WhatsApp group.");
        add("What is Java", "Java is a high-level, object-oriented, platform-independent programming language that runs on the JVM.");
        add("What is OOP", "Object-Oriented Programming organizes code into objects. Its four pillars are encapsulation, inheritance, polymorphism and abstraction.");
        add("What is the JVM", "The Java Virtual Machine executes compiled Java bytecode, making Java platform independent.");
        add("What is the difference between JDK JRE and JVM", "JVM runs bytecode, JRE = JVM + libraries to run programs, JDK = JRE + development tools like javac.");
        add("What is an ArrayList", "ArrayList is a resizable array implementation of the List interface in java.util.");
        add("What is multithreading", "Multithreading lets a program run multiple threads concurrently, using the Thread class or Runnable interface.");
        add("What is inheritance", "Inheritance lets a class (child) reuse fields and methods of another class (parent) using the 'extends' keyword.");
        add("What is polymorphism", "Polymorphism lets one interface take many forms: method overloading (compile-time) and overriding (runtime).");
        add("What is encapsulation", "Encapsulation hides data by making fields private and exposing them through getters and setters.");
        add("What is Spring", "Spring is a Java framework for building enterprise applications, known for dependency injection and Spring Boot.");
        add("What is Hibernate", "Hibernate is an ORM framework that maps Java objects to database tables.");
        add("Who created you", "I was built in Java as part of the CodeAlpha internship, Task 3: AI Chatbot.");
        add("How are you", "I'm just a program, but I'm running great! How can I help you?");
        add("What can you do", "I can answer FAQs about the CodeAlpha internship and core Java concepts. You can also teach me new answers!");
    }
}
*/