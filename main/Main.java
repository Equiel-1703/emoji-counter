package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    private static final HashMap<Integer, Integer> emojiMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Inform input file: ");
        String filePath = s.nextLine();
        s.close();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while (line != null) {
                line.codePoints().forEach(e -> {
                    if (!emojiMap.containsKey(e)) {
                        emojiMap.put(e, 0);
                    } else {
                        int current = emojiMap.get(e);
                        emojiMap.put(e, ++current);
                    }
                });
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        class Value {
            private int value;
            private int key;

            public Value(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public int getKey() {
                return key;
            }

            public int getValue() {
                return value;
            }

            @Override
            public String toString() {
                return String.format("%s: %s", new String(Character.toChars(key)), value);
            }
        }

        ArrayList<Value> values = new ArrayList<>();

        emojiMap.forEach((k, v) -> values.add(new Value(k, v)));

        values.sort((o1, o2) -> o2.getValue() - o1.getValue());

        try (PrintWriter pw = new PrintWriter("output.txt")) {
            values.forEach(pw::println);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    }
}