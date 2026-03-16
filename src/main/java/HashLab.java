// Yonatan Rubin
// M21105076
// Hash Lab – Java implementation of LQ hashing and bucket hashing

public class HashLab {

	private static final int[] INPUT = { 27, 53, 13, 10, 138, 109, 49, 174, 26, 24 };

	private static final int NA = 13; // table size for LQ hashing
	private static final int NB = 10; // table size for bucket hashing
	private static final int PRIME = 19; // 4k+3 prime given in the worksheet

	private static int[] tableA = new int[NA]; // LQ hashing table
	private static String[] tableB = new String[NB]; // bucket hashing table

	// initialize tables
	static {
		for (int i = 0; i < NA; ++i)
			tableA[i] = -1; // -1 means empty
		for (int i = 0; i < NB; ++i)
			tableB[i] = ""; // empty string/empty bucket
	}

	// Linear Quotient Hashing
	public static int hashA(int key) {
		int ip = key % NA;
		int q = key / NA;

		int offset = (q % NA != 0) ? q : PRIME;

		int pos = ip;

		// probe until empty
		while (tableA[pos] != -1) {

			pos = (pos + offset) % NA;
		}

		return pos;
	}

	// Bucket hashing
	public static int hashB(int key) {
		return key % NB;
	}

	public static void main(String[] args) {

		int slot;
		for (int key : INPUT) {
			// table A
			slot = hashA(key);
			tableA[slot] = key;

			// table B
			slot = hashB(key);
			// if the slot we want to insert into is not empty
			// then we want to separate the numbers by commas to tell them apart
			if (!tableB[slot].isEmpty()) {
				tableB[slot] += ", ";
			}
			tableB[slot] += key;
		}

		// print results
		System.out.println("Linear Quotient Hashing (part A)");
		for (int i = 0; i < NA; ++i) {
			// print the row, a colon, and whatever is in the slot. if empty print nothing
			System.out.println(i + ": " + (tableA[i] == -1 ? "" : tableA[i]));
			// then go to next line and do it again
		}

		System.out.println("\nBucket Hashing (part B)");
		for (int i = 0; i < NB; ++i) {
			System.out.println(i + ": " + tableB[i]);
		}
	}
}
