package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class ListSE {
    private Node head;

    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {

                temp = temp.getNext();
            }
            // para en el ultimo
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
    }

    //get in pos by id
    public int getPosById(String id) {
        Node temp = head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getId().equals(id)) {
                acum++;
                temp.getNext();
                return acum;
            }
        }
        return acum;
    }

    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }


    }

    //metodo para añadir nuevo nodo y nuevo niño en un posicion
    public void addInpos(Kid kid, int pos) {
        Node temp = head;
        for (int i = 0; i < pos; i++) {
            temp = temp.getNext();
        }
        Node newNode = new Node(kid);
        temp.setNext(newNode);
    }


    //metodo para eliminar niños recibiendo el id
    public void deleteKid(String id) {
        Node temp = head;
        while (!temp.getNext().getData().getId().equals(id)) {
            temp = temp.getNext();
        }
        temp.getNext().setData(null);
    }

    //adelantar en posicion
    public void advanceInpos(Kid kid, int pos) {
        Node temp = head;
        for (int i = 0; i < pos; i++) {
            temp = temp.getNext();
        }
        Node newNode = new Node(kid);
        temp.setNext(newNode);
    }
    //get in pos


// ejercicios 4-7


    //eliminar por edad
    public void DeleteByAge(Byte age) {
        Node temp = head;

        ListSE listSE1 = new ListSE();
        //recorrer la lista original y crear la lista copia

        while (temp != null) {
            if (temp.getData().getAge() != age) {
                listSE1.addToStart(temp.getData());
                temp.getNext();

            }
        }
        this.head = listSE1.getHead();
    }


    //metodo para definirle que adelante un numero dado de posiciones
    public void AdvancePosition(String id, int advance) {
        Node temp = head;
        advance = 0;
        int sum = 0;
        ListSE listSE1 = new ListSE();
        if (head != null) {
            while (temp != null && !temp.getData().getId().equals(id)) {
                listSE1.add(temp.getData());
                temp.getNext();
            }
        }
        sum = advance - getPosById(id);
        listSE1.addInpos(getKidByid(id), sum);
    }

    //get Kid By id

    public Kid getKidByid(String id) {
        Node temp = head;
        if (head != null) {
            while (temp != null) {
                temp.getNext();
                while (!temp.getData().getId().equals(id)) {
                    temp.getNext();
                }
                temp.getData();

            }
        }
        Kid kid =new Kid(temp.getData().getId(),temp.getData().getName(),temp.getData().getAge());
        return kid;

    }

}





