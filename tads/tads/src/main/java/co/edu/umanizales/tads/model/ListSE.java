package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import lombok.Data;

@Data
public class ListSE {
    private Node head;
    private int size;

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
        size++;
    }
    public void gainPositionKid(String id,int gain){
        Node temp= head;
        gain=0;
        int sum=0;
        ListSE listSECp =new ListSE();
        if (head !=null){
            while (temp !=null && !temp.getData().getIdentification().equals(id)){
                listSECp.add(temp.getData());
            }
            temp.getNext();
        }
        sum=gain-getPosById(id);
        listSECp.addInpos(getKidByid(id),sum);
    }

    //get in pos by id
    public int getPosById(String id) {
        Node temp = head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                acum++;
                temp.getNext();
                return acum;
            }
        }
        return acum;
    }

    /* Adicionar al inicio
    si hay datos
    si
        meto al niño en un costal (nuevocostal)
        le digo a nuevo costal que tome con su brazo a la cabeza
        cabeza es igual a nuevo costal
    no
        meto el niño en un costal y lo asigno a la cabez
     */

    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
        size++;

    }

    //invert
    public void invert() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }
    public void changeExtremes(){
        if (this.head !=null && this.head.getNext()!=null)
        {
            Node temp = this.head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
        }
            //remp esta de ultimo
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
    }

    public void orderBoysToStart() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {

                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public int getCountKidsByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int verifyId(KidDTO kid){
        Node temp = this.head;
        Boolean found = false;
        while (temp !=null){
            if(temp.getData().getIdentification().equals(kid.getIdentification())){
                found = true;
                break;
            }
            temp = temp.getNext();
        }
        return found ?1 :0;
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
        while (!temp.getNext().getData().getIdentification().equals(id)) {
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
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                listSE1.add(temp.getData());
                temp.getNext();
            }
        }
        sum = advance - getPosById(id);
        listSE1.addInpos(getKidByid(id), sum);
    }
    private int getPosByIdentification(String identification){return 0;}
    //get Kid By id

    public Kid getKidByid(String id) {
        Node temp = head;
        if (head != null) {
            while (temp != null) {
                temp.getNext();
                while (!temp.getData().getIdentification().equals(id)) {
                    temp.getNext();
                }
                temp.getData();

            }
        }
        Kid kid =new Kid(temp.getData().getIdentification(),temp.getData().getName(),temp.getData().getAge());
        return kid;

    }
    public int getCountKidsBylocationAndGenderM(String code){
        int count=0;
        int countm=0;
        int countf=0;

        if(this.head !=null){
            Node temp = this.head;
            while (temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count ++;
                    if (temp.getData().getGender()== 'M'){
                        countm++;
                    }
                }
            }
            temp=temp.getNext();
        }
        return countm;
    }
    public int getCountKidsBylocationAndGenderF(String code){
        int count=0;
        int countm=0;
        int countf=0;

        if(this.head !=null){
            Node temp = this.head;
            while (temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count ++;
                }
            }
            temp=temp.getNext();
        }
        return countf;
    }
}





