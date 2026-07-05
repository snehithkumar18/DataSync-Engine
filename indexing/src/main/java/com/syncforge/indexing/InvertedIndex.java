package com.syncforge.indexing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Inverted index for efficient full-text search.
 * Maps terms to document IDs and supports boolean queries.
 */
public class InvertedIndex {
    
    private final Map<String, List<Integer>> termToDocIds;
    private final Map<Integer, List<String>> docIdToTerms;
    private final String name;
    private int totalDocuments;
    
    public InvertedIndex() {
        this("default");
    }
    
    public InvertedIndex(String name) {
        this.name = name;
        this.termToDocIds = new HashMap<>();
        this.docIdToTerms = new HashMap<>();
        this.totalDocuments = 0;
    }
    
    /**
     * Indexes a document with the given terms.
     */
    public void indexDocument(int docId, List<String> terms) {
        if (terms == null || terms.isEmpty()) {
            return;
        }
        
        docIdToTerms.put(docId, new ArrayList<>(terms));
        totalDocuments = Math.max(totalDocuments, docId + 1);
        
        for (String term : terms) {
            String normalizedTerm = normalizeTerm(term);
            termToDocIds.computeIfAbsent(normalizedTerm, k -> new ArrayList<>()).add(docId);
        }
    }
    
    /**
     * Searches for documents containing all specified terms (AND query).
     */
    public List<Integer> searchAnd(List<String> terms) {
        if (terms == null || terms.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Integer> result = null;
        
        for (String term : terms) {
            String normalizedTerm = normalizeTerm(term);
            List<Integer> docIds = termToDocIds.getOrDefault(normalizedTerm, new ArrayList<>());
            
            if (result == null) {
                result = new ArrayList<>(docIds);
            } else {
                result.retainAll(docIds);
            }
            
            if (result.isEmpty()) {
                break;
            }
        }
        
        return result != null ? result : new ArrayList<>();
    }
    
    /**
     * Searches for documents containing any specified term (OR query).
     */
    public List<Integer> searchOr(List<String> terms) {
        if (terms == null || terms.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Integer> result = new ArrayList<>();
        
        for (String term : terms) {
            String normalizedTerm = normalizeTerm(term);
            List<Integer> docIds = termToDocIds.getOrDefault(normalizedTerm, new ArrayList<>());
            
            for (Integer docId : docIds) {
                if (!result.contains(docId)) {
                    result.add(docId);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Searches for documents containing the first term but not the second (NOT query).
     */
    public List<Integer> searchNot(String includeTerm, String excludeTerm) {
        List<Integer> includeDocs = termToDocIds.getOrDefault(normalizeTerm(includeTerm), new ArrayList<>());
        List<Integer> excludeDocs = termToDocIds.getOrDefault(normalizeTerm(excludeTerm), new ArrayList<>());
        
        List<Integer> result = new ArrayList<>();
        for (Integer docId : includeDocs) {
            if (!excludeDocs.contains(docId)) {
                result.add(docId);
            }
        }
        
        return result;
    }
    
    /**
     * Gets the document frequency for a term (number of documents containing it).
     */
    public int getDocumentFrequency(String term) {
        String normalizedTerm = normalizeTerm(term);
        return termToDocIds.getOrDefault(normalizedTerm, new ArrayList<>()).size();
    }
    
    /**
     * Gets the term frequency for a document (number of times term appears in document).
     */
    public int getTermFrequency(int docId, String term) {
        List<String> terms = docIdToTerms.get(docId);
        if (terms == null) {
            return 0;
        }
        
        String normalizedTerm = normalizeTerm(term);
        int count = 0;
        for (String t : terms) {
            if (normalizeTerm(t).equals(normalizedTerm)) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Gets all terms for a document.
     */
    public List<String> getTermsForDocument(int docId) {
        return docIdToTerms.getOrDefault(docId, new ArrayList<>());
    }
    
    /**
     * Gets all unique terms in the index.
     */
    public List<String> getAllTerms() {
        return new ArrayList<>(termToDocIds.keySet());
    }
    
    /**
     * Removes a document from the index.
     */
    public void removeDocument(int docId) {
        List<String> terms = docIdToTerms.remove(docId);
        
        if (terms != null) {
            for (String term : terms) {
                String normalizedTerm = normalizeTerm(term);
                List<Integer> docIds = termToDocIds.get(normalizedTerm);
                
                if (docIds != null) {
                    docIds.remove(Integer.valueOf(docId));
                    
                    if (docIds.isEmpty()) {
                        termToDocIds.remove(normalizedTerm);
                    }
                }
            }
        }
    }
    
    /**
     * Clears the index.
     */
    public void clear() {
        termToDocIds.clear();
        docIdToTerms.clear();
        totalDocuments = 0;
    }
    
    /**
     * Gets the total number of documents.
     */
    public int getTotalDocuments() {
        return totalDocuments;
    }
    
    /**
     * Gets the number of unique terms.
     */
    public int getUniqueTermCount() {
        return termToDocIds.size();
    }
    
    /**
     * Gets the index name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Normalizes a term for indexing.
     */
    private String normalizeTerm(String term) {
        if (term == null) {
            return "";
        }
        return term.toLowerCase().trim();
    }
    
    /**
     * Calculates TF-IDF score for a term in a document.
     */
    public double calculateTFIDF(int docId, String term) {
        int tf = getTermFrequency(docId, term);
        int df = getDocumentFrequency(term);
        
        if (tf == 0 || df == 0 || totalDocuments == 0) {
            return 0.0;
        }
        
        double tfScore = 1 + Math.log(tf);
        double idfScore = Math.log((double) totalDocuments / df);
        
        return tfScore * idfScore;
    }
    
    /**
     * Builder for creating inverted indexes.
     */
    public static class Builder {
        private String name = "default";
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public InvertedIndex build() {
            return new InvertedIndex(name);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
