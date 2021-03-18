package new_sem.lab_2;

public class test {
    public static void main(String[] args) {
        
        map<Integer, String> st = new map<Integer, String>();

        System.out.println("\n");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("\n");
        
        // Добавление пар ключ-значение
        st.put(9, "alpha");
        st.put(1, "beta");
        st.put(2, "gamma");
        st.put(6, "delta");
        st.put(4, "teta");
        st.put(5, "yota");
        st.put(8, "epsilon");
        st.put(7, "geksa");
        st.put(0, "tau");
        st.put(3, "phi");
        st.put(10,"rho");
        
        
        System.out.println("Your tree (key | value) \n");
   
        st.printTree(st.root, 0); // Вывод дерева   
        
        System.out.println("\n");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("\n");

        // Удаление по ключу
        st.delete(2);
        st.delete(7);

        st.put(10,"new"); // Пример замены старого значения новым ("rho" заменяется на "new")
       
        st.printTree(st.root, 0); // Вывод дерева
        
        st.deleteAll(); // Удаление всех элементов дерева

        System.out.println("\n");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("\n");

        // Проверка на пустоту (если дерево пустое будет выведено true, иначе false)
        System.out.println("Empty check: " + st.isEmpty());

        // Демонстрация работы конструктора копирования
        map<Integer,String> copy = new map<Integer, String>(st);

        System.out.println("\n");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("\n");
        
        if(copy.root == null){  // Если копия дерева пуста
            System.out.println("Copy of your tree is empty");
        } else {
            System.out.println("Copy of your tree \n");
            copy.printTree(copy.root, 0); // Вывод копии дерева
        }

        System.out.println("\n");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("\n");

    }
}