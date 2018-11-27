package oving5.del2;

class HashTabell {

    private static final int PRIME = 43;
    private final int SIZE;
    private int[] tabell;

    HashTabell(int size) {
        SIZE = size;
        tabell = new int[size];
    }

    private int hashFunc1(int verdi) {
        return verdi % SIZE;
    }

    private int hashFunc2(int verdi) {
        return verdi % (SIZE - 1) + 1;
    }

    private int probe(int h1, int h2, int i) {
        return (h1 + i * h2) % SIZE;
    }

    // function to insert key into hash table
    boolean insert(int verdi) {
        // if hash table is full
        //if (lastFaktoren > arraySize) return false;
        // get index from first hash
        int h1 = hashFunc1(verdi);
        int h2 = hashFunc2(verdi);

        int index = h1;

        // if collision occurs
        if (tabell[index] != 0) {
            // get index2 from second hash
            for (int i = 1; i < SIZE - 1; i++) {
                index = probe(h1, h2, i);
                if (tabell[index] == 0) {
                    tabell[index] = verdi;
                    return true;
                }
            }
        }
        tabell[index] = verdi;
        return true;
    }

    // function to display the hash table
    void displayHash() {
        for (int i = 0; i < SIZE; i++) {
            if (tabell[i] != 0) {
                System.out.println(i + " --> " + tabell[i]);
            }
            //System.out.println(i);
        }
    }
}

