public class TallerDos {
    public static void main(String[] args) {
       
    }
}

class NodoSencillo {
    int dato;
    NodoSencillo sig;

    public NodoSencillo(int dato){
        this.dato = dato;
        this.sig = null; 
    }
}

class Listica { 
    NodoSencillo cabeza;

    public void insercionFinal(int dato){
        NodoSencillo nuevo = new NodoSencillo(dato);
        if(cabeza == null) {
            this.cabeza = nuevo;
        }else {
            NodoSencillo temp = cabeza;
            while(temp.sig != null){
                temp = temp.sig;
            }
            temp.sig = nuevo;
        } 
    }

    // es más sencillo insertar al inicio
    public void insercionInicial(int dato){
        NodoSencillo nuevo = new NodoSencillo(dato);
        nuevo.sig = cabeza;
        cabeza = nuevo;
    }
}

class NodoDoble{
    int dato;
    NodoDoble ant;
    NodoDoble sig;

    public NodoDoble(int dato){
        this.dato = dato;
        this.ant = null;
        this.sig = null;
    }
}

class ListicaDoble {
    NodoDoble cabeza;

    public void insercionFinal(int dato){
        NodoDoble nuevo = new NodoDoble(dato);
        if(cabeza == null){
            this.cabeza = nuevo;
        } else {
            NodoDoble temp = cabeza;
            while(temp.sig != null){
                temp = temp.sig;
            }
            temp.sig = nuevo;
            nuevo.ant = temp;
        }
    }
    public void insertarInicio(int dato){
        NodoDoble nuevo = new NodoDoble(dato);
        nuevo.sig = cabeza;
        if(cabeza != null){
            cabeza.ant = nuevo;
        }
        cabeza = nuevo;
    }
}