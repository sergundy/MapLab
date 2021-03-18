package new_sem.lab_2;

public class test {
    public static void main(String[] args) {
        
        map<Integer, String> st = new map<Integer, String>();

        System.out.println("\n");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("\n");

        
        /*for(int i = 0;i<10;i++){
            st.put(i,"a");
        }*/
        
        // Добавление пар ключ-значение
        st.put(9, "a");
        st.put(1, "b");
        st.put(2, "c");
        st.put(6, "d");
        st.put(4, "e");
        st.put(5, "f");
        st.put(8, "j");
        st.put(7, "h");
        st.put(0, "i");
        st.put(3, "j");
        st.put(10,"l");
        
        System.out.println("Your tree (key | value) \n");
        System.out.println("balanses of every nodes");
        st.printBalance();
        //st.printTree(st.root, 0); // Вывод дерева

        System.out.println("\n");

        // Демонстрация работы метода для получения значения по ключу
        System.out.println(st.get(2));

        System.out.println("\n");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.println("\n");

        // Удаление по ключу
        //st.delete(2);
        //st.delete(7);

        //st.put(10,"new"); // Пример замены старого значения новым ("rho" заменяется на "new")
       
        //st.printTree(st.root, 0); // Вывод дерева
        
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