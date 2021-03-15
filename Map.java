package new_sem.lab_2;

public class map<Key extends Comparable<Key>, Value> {

    // корневой элемент дерева
    Node root;

    private class Node {
        private final Key key;   // ключ
        private Value val;       // связанное значение
        private int height;      // высота поддерева
        private int size;        // количество узлов в поддереве
        private Node left;       // левое поддерево
        private Node right;      // правое поддерево

        public Node(Key key, Value val, int height, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.height = height;
        }
    }

    // Инициализирует пустую таблицу символов
    public map() {
    }

    // Удаление всех элементов 
    public void deleteAll() {
        root = null;
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return root == null;
    }
     
    // Возвращает количество узлов в поддереве
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    // Возвращает высоту поддерева
    private int height(Node x) {
        if (x == null) return 0; 
        return x.height;
    }

    /* Вставляет указанную пару ключ-значение, заменяя старое значение  новым значением, если уже существует указанный ключ 
    и удаляет указанный ключ (и связанное с ним значение), если указанное значение равно null */
    public void put(Key key, Value val) {
        if (val == null) { 
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    /* Вставляет пару ключ-значение в поддерево. Заменяет старое значение  новым значением, если  уже существует указанный ключ
    и удаляет указанный ключ (и связанное с ним значение), если указанное значение равно null */
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 0, 1);// Если узел == null, то создаём на этом месте новый узел
        int cmp = key.compareTo(x.key); // Сравнение по ключу
        if (cmp < 0) {
            x.left = put(x.left, key, val); // Если меньше, то идём влево
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, val); // Если больше, то идём вправо
        }
        else {
            x.val = val; // Заменяет старое значение новым
            return x;
        }
        
        x.size = 1 + size(x.left) + size(x.right); // Обновление значения size (количество узлов в поддереве)
        x.height = 1 + Math.max(height(x.left), height(x.right)); // Обновление значения высоты. Math.max() — возвращает максимальное значение из двух аргументов
        return balance(x);
    }

    // Выполняет балансировку
    private Node balance(Node x) {
        if (balanceFactor(x) < -1) {
            if (balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        }
        else if (balanceFactor(x) > 1) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        
        return x;
    }

    // Возвращает коэффициент баланса поддерева. Коэффициент баланса равен разности значений высот левого и правого поддеревьев
    private int balanceFactor(Node x) {
        return height(x.left) - height(x.right);
    }

    // Поворачивает данное поддерево вправо
    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right); // Обновление значения size (количество узлов в поддереве)
        x.height = 1 + Math.max(height(x.left), height(x.right)); // Обновление значения высоты. Math.max() — возвращает максимальное значение из двух аргументов
        y.height = 1 + Math.max(height(y.left), height(y.right)); // Обновление значения высоты. Math.max() — возвращает максимальное значение из двух аргументов
        return y;
    }

    // Поворачивает данное поддерево влево
    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right); // Обновление значения size (количество узлов в поддереве)
        x.height = 1 + Math.max(height(x.left), height(x.right)); // Обновление значения высоты. Math.max() — возвращает максимальное значение из двух аргументов
        y.height = 1 + Math.max(height(y.left), height(y.right)); // Обновление значения высоты. Math.max() — возвращает максимальное значение из двух аргументов
        return y;
    }

    // Удаляет указанный ключ и связанное с ним значение
    public void delete(Key key) {
        root = delete(root, key);
    }

    // Удаляет указанный ключ и связанное с ним значение из заданного поддерева
    private Node delete(Node x, Key key) {
        int cmp = key.compareTo(x.key); // Сравнение по ключу
        if (cmp < 0) {
            x.left = delete(x.left, key);
        }
        else if (cmp > 0) {
            x.right = delete(x.right, key);
        }
        else {
            if (x.left == null) {
                return x.right;
            }
            else if (x.right == null) {
                return x.left;
            }
            else {
                Node y = x;
                x = min(y.right);
                x.right = deleteMin(y.right);
                x.left = y.left;
            }
        }
        x.size = 1 + size(x.left) + size(x.right); // Обновление значения size (количество узлов в поддереве)
        x.height = 1 + Math.max(height(x.left), height(x.right)); // Обновление значения высоты (x). Math.max() — возвращает максимальное значение из двух аргументов
        return balance(x);
    }

    // Удаляет наименьший ключ и связанное значение из данного поддерева
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);  // Обновление значения size (количество узлов в поддереве)
        x.height = 1 + Math.max(height(x.left), height(x.right)); // Обновление значения высоты. Math.max() — возвращает максимальное значение из двух аргументов
        return balance(x);
    }

    // Возвращает узел с наименьшим ключом в поддереве
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    // Вывод дерева 
    public void printTree(Node root, int height){

        if(root==null){
            return;
        }
        printTree(root.right, height+1);
        if(height!=0){
            for(int i=0;i<height-1;i++)
                System.out.print("|\t");
                System.out.println( "├───────" + root.key + " " + root.val);
        }
        else
            System.out.println(root.key + " " + root.val);
        printTree(root.left, height+1);
    }

    // Конструктор копирования
    public map(map<Key,Value> copy) {
        this.root = copy.root;
    }
}   
