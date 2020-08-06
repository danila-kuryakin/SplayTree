package ru.kuryakin;


import org.junit.Test;

import static org.junit.Assert.*;

public class SplayTreeTest {

    @Test
    public void add() {
        SplayTree<Integer> tree = new SplayTree<Integer>();
        tree.add(5);
        tree.add(9);
        tree.add(13);
        tree.add(11);
        tree.add(1);
        assertFalse(tree.add(11));
        assertFalse(tree.add(1));

        assertEquals(5, tree.size());
    }

    @Test
    public void testInteger1(){
        SplayTree<Integer> tree = new SplayTree<Integer>();
        tree.add(10);
        tree.add(5);
        tree.add(7);
        tree.add(8);

        tree.remove(7);

        assertEquals(3, tree.size());
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(7));
    }

    @Test
    public void testInteger2(){
        SplayTree<Integer> tree = new SplayTree<Integer>();
        tree.add(100);
        tree.add(55);
        tree.add(74);
        tree.add(88);
        tree.add(53);
        tree.add(52);
        tree.add(71);
        tree.add(86);
        tree.add(102);
        tree.add(59);
        tree.add(77);

        assertEquals(11, tree.size());

        assertFalse(tree.remove(6));
        assertTrue(tree.remove(53));
        assertTrue(tree.remove(71));

        assertEquals(9, tree.size());
        assertTrue(tree.contains(102));
        assertTrue(tree.contains(74));
        assertFalse(tree.contains(7));
    }

    @Test
    public void testString(){
        SplayTree<String> tree = new SplayTree<String>();
        tree.add("Cat");
        tree.add("Dog");
        tree.add("Cow");
        tree.add("Kitten");

        assertEquals(4, tree.size());
        assertTrue(tree.contains("Cat"));
        assertTrue(tree.remove("Cat"));
        assertFalse(tree.contains("Cat"));
        assertEquals(3, tree.size());

        tree.add("Cow");
        assertTrue(tree.contains("Cow"));
        assertFalse(tree.contains("Calf"));
    }

    @Test
    public void testSplay(){
        SplayTree<Integer> tree = new SplayTree<Integer>();
        tree.add(5);
        tree.add(9);
        tree.add(13);
        tree.add(11);
        tree.add(3);

        assertTrue(tree.splay(9));
        assertTrue(tree.splay(13));
        assertTrue(tree.splay(11));
        assertFalse(tree.splay(7));
        assertFalse(tree.splay(1));
    }

    @Test
    public void testIterator(){
        SplayTree<Integer> tree = new SplayTree<Integer>();
        assertTrue(tree.isEmpty());
        tree.add(5);
        tree.add(9);
        tree.add(13);
        tree.add(11);
        tree.add(3);

        int[] mas = new int[tree.size()];
        int[ ]mas2 = {3, 5, 9, 11, 13};
        int j = 0;

        for(int i: tree) {
            mas[j] = i;
            j++;
        }

        assertFalse(tree.isEmpty());
        assertArrayEquals(mas2, mas);
        assertTrue(tree.splay(13));

        j = 0;
        for(int i: tree) {
            mas[j] = i;
            j++;
        }
        assertArrayEquals(mas2, mas);
    }
}
