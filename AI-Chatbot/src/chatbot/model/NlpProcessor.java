/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package chatbot.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** MODEL: NLP (Natural Language Processing) steps, one method per step. */
public class NlpProcessor {

    // Common words that carry little meaning
    private static final List<String> STOP_WORDS = Arrays.asList(
            "a", "an", "the", "is", "are", "of", "to", "in", "do", "does", "i", "you", "me",
            "my", "can", "please", "tell", "about", "what", "how", "who", "explain", "define");

    // Step 1: TOKENIZATION - lowercase the text, remove symbols, split into words
    public static List<String> tokenize(String text) {
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9 ]", " ");
        return Arrays.asList(cleaned.trim().split("\\s+"));
    }

    // Step 2: STOP-WORD REMOVAL - keep only the important words
    public static List<String> removeStopWords(List<String> words) {
        List<String> result = new ArrayList<>();
        for (String w : words) {
            if (!w.isEmpty() && !STOP_WORDS.contains(w)) {
                result.add(w);
            }
        }
        return result;
    }

    // Step 3: STEMMING - reduce a word to its base form ("threads" -> "thread")
    public static String stem(String word) {
        if (word.length() > 3 && word.endsWith("s")) {
            return word.substring(0, word.length() - 1);
        }
        return word;
    }

    // Run all steps: text -> set of important base words
    public static Set<String> process(String text) {
        Set<String> result = new HashSet<>();
        for (String w : removeStopWords(tokenize(text))) {
            result.add(stem(w));
        }
        return result;
    }

    // Step 4: SIMILARITY - score from 0 to 1 = how many words two sentences share
    public static double similarity(Set<String> a, Set<String> b) {
        if (a.isEmpty() || b.isEmpty()) return 0;
        int common = 0;
        for (String w : a) {
            if (b.contains(w)) common++;
        }
        return common / Math.sqrt((double) a.size() * b.size());
    }
}