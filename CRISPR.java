/*
 * NAME: TODO First Last
 * PID: TODO Axxxxxxxx
 */


/**
 * Gene Splicing CRISPR Simulator
 *
 * @author TODO
 * @since TODO
 */
public class CRISPR {

    /*Sequences to use/test your CRISPR functions on. Please add more as you test*/
    private static String simpleGenome = "ACATATA";

    private static String sequencedGenome = "AAATTCAAGCGAGGTGATTACAACAAATTTTGCTGATGGTTTAGGCGTA"
            + "CAATCCCCTAAAGAATATAATTAAGAAAATAGCATTCCTTGTCGCCTAGAATTACCTACCGGCGTCCACCATACCTTCG"
            + "ATATTCGCGCCCACTCTCCCATTAGTCGGCACAAGTGGATGTGTTGCGATTGCCCGCTAAGATATTCTAAGGCGTAACG"
            + "CAGATGAATATTCTACAGAGTTGCCGTACGCGTTGAACACTTCACGGATGATAGGAATTTGCGTATAGAGCGGGTCATT"
            + "GAAGGAGATTACACTCGTAGTTAACAACGGGCCCGGCTCTATCAGAACACGAGTGCCTTGAATAACATACTCATCACTA";

    private static String overlappingGuide = "UAU";
    private static String guideRNA = "CUAAUGU";
    private static String splicedGene = "TAGACAT";


    /**
     * Program Entry, this simply runs
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        /*Should print out ACATATA (unchanged)*/
        //System.out.println(spliceDNA(simpleGenome, overlappingGuide, splicedGene));
        // Expected: ACATAGTA
        System.out.println(spliceDNA("ACATA", "UGU", "TAG"));
        System.out.println(spliceDNA("ATATA", "UA", "CAT"));
        System.out.println(spliceDNA("ATATA", "UAU", "CAT"));
        System.out.println(spliceDNA("ATATA", "CG", "CAT"));

        System.out.println(spliceDNA("ACATATA", "UAU", "TAGACAT"));
        System.out.println(spliceDNA("ACATATA", "UGU", "TAGACAT"));
        System.out.println(spliceDNA("ACATATA", "UA", "CAT"));

        String s1 = new String("java");
        String s2 = "java";
        String s3 = "java";

        System.out.println(s1.equals(s2));
    }

    /**
     *  Simulate gene splicing on a genome using CRISPR
     *
     * @param genomeSequence initial DNA encoding
     * @param guideSequence guideRNA encoding
     * @param splicedSequence target insertion gene encoding
     * @return modified genome
     */
    public static String spliceDNA(String genomeSequence, String guideSequence, String splicedSequence) {
        DoublyLinkedList<Character> genome = new DoublyLinkedList<>();
        DoublyLinkedList<Character> guideRNA = new DoublyLinkedList<>();

        populateFromDNA(genome, genomeSequence);
        populateDNAFromRNA(guideRNA, guideSequence);

        //TODO: Implement a splicing algorithm with will add the splicedSequence where appropriate to genome
        int[] indices = genome.match(guideRNA);
        DoublyLinkedList<Character> splicedSeq = new DoublyLinkedList<>();
        populateFromDNA(splicedSeq, splicedSequence);


        //System.out.println(genome);
        if (indices.length > 1 && indices[indices.length-2] + guideRNA.size() <= indices[indices.length - 1]) {
           // System.out.println(Arrays.toString(indices));
            genome.splice(indices[indices.length-1] + guideRNA.size(), splicedSeq);
        }

        for (int i = 0; i < indices.length - 1; i++) {
            if (indices[i] + guideRNA.size() <= indices[i+1] ) {
                genome.splice(indices[i] + guideRNA.size(), splicedSeq);
            }
        }

        if (indices.length == 1) {
            genome.splice(indices[0] + guideRNA.size(), splicedSeq);
        }
        /*
        System.out.println(guideRNA.size());
        System.out.println("genome: " + genome);
        System.out.println("guide: " + guideRNA);
        System.out.println("splice: " + splicedSeq);
        //System.out.println("index: " + indices[0]);
        System.out.println(Arrays.toString(indices));

        //genome.splice(indices[0] + splice.size(), splice);
           */
        return transcribeGeneticCode(genome);
    }

    /**
     * This is a direct encoding of the genetic code from the String to a LinkedList
     * @param dnaList to populate
     * @param dnaString DNA string encoding
     */
    public static void populateFromDNA(DoublyLinkedList<Character> dnaList, String dnaString) {
        //TODO: Populate dnaList with the characters in s
        char[] s = dnaString.toCharArray();
        for (char c : s) {
            dnaList.add(c);
        }
    }

    /**
     * This is an encoding of of the DNA that binds with the RNA
     * Remember that DNA pairs up A-T C-G, and RNA pairs up A-U C-G
     * Thus the guide RNA AUCG would match with the DNA TAGC
     * @param dnaList to populate
     * @param rnaString RNA string encoding
     */
    public static void populateDNAFromRNA(DoublyLinkedList<Character> dnaList, String rnaString) {
        //TODO: Populate dnaList with the DNA representation of the RNA Sequence
        char[] s = rnaString.toCharArray();
        //char[] dnaChar = new char[rnaString.length()];
        for (char c : s) {
            if (c == 'A') {
                dnaList.add('T');
            }
            else if (c == 'U') {
                dnaList.add('A');
            }
            else if (c == 'C') {
                dnaList.add('G');
            }
            else if (c == 'G') {
                dnaList.add('C');
            }
        }

        String dnaString = transcribeGeneticCode(dnaList);
        dnaList.clear();
        populateFromDNA(dnaList, dnaString);
    }

    /**
     * Recreate the original base sequence that was loaded into the list
     * @param geneticSequence list representation of the
     * @return base sequence of the genetic material
     */
    public static String transcribeGeneticCode(DoublyLinkedList<Character> geneticSequence) {
        String s = "";
        for (char c : geneticSequence) {
            s += c;
        }
        return s;
    }

}
