package proyecto;

import java.io.Serializable;

public class ArbolB implements Serializable {

    NodoB root;
    int t = 3;

    ArbolB() {
        root = null;
    }

    void traverse() {
        if (root != null) {
            root.traverse();
            System.out.println("");
        }
    }

    void PrintLevels() {
        try {
            if (root != null) {
                for (int i = 0; i < root.n; i++) {
                    if (root.key[i] != null) {
                        System.out.print(root.key[i] + "  ");
                    }
                }

                System.out.println("");
                if (root.hijos != null) {
                    for (NodoB hijo : root.hijos) {
                        if (hijo != null) {
                            for (int j = 0; j < hijo.n; j++) {
                                if (hijo.key[j] != null) {
                                    System.out.print(hijo.key[j]);
                                }
                            }
                            System.out.print("|");
                        }
                    }
                    System.out.println("");
                    for (NodoB hijo : root.hijos) {
                        if (hijo != null) {
                            for (int j = 0; j < hijo.n; j++) {
                                if (hijo.hijos[j] != null) {
                                    for (int k = 0; k < hijo.hijos[j].n + 1; k++) {
                                        if (hijo.hijos[j].key[k] != null) {
                                            System.out.print(hijo.hijos[j].key[k]);
                                        }
                                    }
                                    System.out.print("|");
                                }
                            }
                            System.out.print("|");
                        }
                    }

                }
            }
        } catch (Exception x) {

        }

    }

    NodoB search(Registro k) {
        if (root == null) {
            return null;
        } else {
            return root.search(k);
        }
    }

    void insert(Registro r) {

        if (root == null) {
            root = new NodoB(t, true);
            root.key[0] = r;
            root.n = 1;
        } else {
            if (root.getN() == 2 * t - 1) {

                NodoB s = new NodoB(t, false);

                s.hijos[0] = root;

                s.splitChild(0, root);

                int i = 0;
                if (s.getKey(0).llave < r.getLlave()) {
                    i++;
                }
                s.getHijos()[i].insertNonFull(r);
                root = s;
            } else {
                root.insertNonFull(r);
            }
        }
    }

    void remove(Registro k) {
        if (root == null) {
            System.out.println("El Arbol esta vacio");

            return;
        }

        root.remove(k);

        if (root.n == 0) {
            NodoB tmp = root;
            if (root.isLeaf()) {
                root = null;
            } else {
                root = root.hijos[0];
            }

            tmp = null;
        }
        return;
    }

}
