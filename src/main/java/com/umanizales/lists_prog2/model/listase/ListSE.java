package com.umanizales.lists_prog2.model.listase;
/**
 * Clase que me permite gestionar una lista simplemente enlazada
 * contiene los métodos u operaciones ....
 * solo cuenta con los atributo head = que representa el primer niño
 * ...
 */

import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    /**
     * Atributo que representa el inicio de la lista y es
     */
    private Node head;
    private int count;

    /**
     * Método que adiciona un niño al final de la lista
     * @param boy
     * @throws ListaSeException
     */
    public void add(Boy boy) throws ListaSeException
    {
        /**
         * Se invoca el método encontrar por identificación, para verificar si
         * el niño que está ingresando ya existe
         */
        Boy boyExist= findByIdentification(boy.getIdentification());
        if(boyExist !=null)
        {
            /**
             * Si el niño ya existe se lanza la excepeción para comunicar al usuario en el controller
             */
            throw  new ListaSeException("La identificación ingresada ya existe");
        }
        /**
         * Si la cabeza esta vacia o nula entonces se adiciona un nuevo niño a la cabeza
         */
        if(head == null)
        {
            head = new Node(boy);
        }
        /**
         *  si no llama al ayudante para que agregue un nuevo niño a la cabeza y recorer la lista hasta llegar al ultimo
         */
        else
        {
            Node temp = head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
            }
            /**
             * y ya el queda parado en el ultimo
             */
            temp.setNext(new Node(boy));
        }
        count++;
    }

    /**
     * metodo para adiciona un niño al inicio de la lista
     * @param boy
     * @throws ListaSeException
     */

    public void addToStart(Boy boy) throws ListaSeException
    {
        /**
         * Se invoca el método encontrar por identificación, para verificar si
         * el niño que está ingresando ya existe
         */
        Boy boyExist= findByIdentification(boy.getIdentification());
        if(boyExist !=null)
        {
            /**
             * Si el niño ya existe se lanza la excepción para comunicar al usuario en el controller
             */
            throw  new ListaSeException("La identificación ingresada ya existe");
        }
        /**
         * Si la cabeza esta vacia o nula entonces se adiciona un nuevo niño a la cabeza
         */
        if( this.head==null)
        {
            this.head = new Node(boy);
        }
        /**
         *  si no llama al ayudante para que agregue un nuevo niño y pasa al siguiente nodo donde agrega
         *  al niño al inicio
         */
        else
        {
            Node temp = new Node(boy);
            temp.setNext(this.head);
            this.head= temp;
        }
        count++;
    }

    /**
     * metodo para adicionar por posicion a un niño
     * @param boy
     * @param position
     * @throws ListaSeException
     */

    public void addByPosition(Boy boy, int position) throws ListaSeException
    {
        /**Se invoca el método encontrar por identificación, para verificar si
         * el niño que está ingresando ya existe
         */
        Boy boyExist= findByIdentification(boy.getIdentification());
        if(boyExist !=null)
        {
            /**
             * Si el niño ya existe se lanza la excepción para comunicar al usuario en el controller
             */
            throw  new ListaSeException("La identificación ingresada ya existe");
        }
        /**
         * en este ciclo se valida la posicion del niño y si es mayor al numero de niños se adiciona uno nuevo
         */
        /// Validación de la posicíon
        if(position > count)
        {
            this.add(boy);
            return;
            //throw  new ListaSeException("La posición ingresada no es válida");
        }
        /**
         * si la posicion es igual 1 se adiciona el niño al inicio
         */
        if(position ==1)
        {
            addToStart(boy);
        }
        /**
         * si no se empiezan a contar los niños
         * y el ayudante inicia en la cabeza
         *  y mientras el temporal sea diferente de vacio (nulo) el ayudante va a tomar la poscion anterion
         */
        else
        {
            int cont=1;
            Node temp = this.head;
            while(temp!=null)
            {
                if(cont == position-1 )
                {
                    break;
                }
                temp= temp.getNext();
                count++;
            }
            /**
             * el nuevo niño se va adiconar en la posicion del temporal
             */
            //Ayudante va estar posicionado en la posición anterior
            Node nodeNew= new Node(boy);
            nodeNew.setNext(temp.getNext());
            temp.setNext(nodeNew);
            count++;
        }
    }

    /**metodo para invertir la lista se
     * @throws ListaSeException
     */

    public void invert() throws ListaSeException{
        /**
         * si la cabeza esta vacia se llama al ayudante y se crea una lista temporal
         * llamo a un ayudante y lo ubico en la cvabeza
         */
        if (this.head != null) {
            ListSE listTemp = new ListSE();
            Node temp = this.head;
            /**
             * se crea un siclo  para que recorra la lista de inicio a fin
             * uy cuando este vacia
             */
            while(temp != null)
            {
                /**
                 * la lista temporal se adiciona al inicio de atras para adelnate con ayuda del ayunte
                 *
                 */
                listTemp.addToStart(temp.getData());
                temp = temp.getNext();

            }
            /**
             * en la cabse se capturan los datos de la lista temporal que se invirtio
             */
            this.head = listTemp.getHead();
        }


    }

    /**
     * metodo para contar los niños de la lista
     * @return
     */
    public int count() {
        /**
         * se inicializa el contador en cero
         * y se pregunta si la cabeza esta vacia  el ayudante inicie en la cabeza
         */
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            /**
             * se realiza un ciclo para recorrer la lista de principio a fin  y contar los niños hasta que
             * el ayudante este vacio o null
             */
            while (temp != null)
            {
                count++;
                temp = temp.getNext();
            }
        }
        /**
         * retorna el conteo de los niños
         */
        return count;
    }


    public List<Boy> list() throws ListaSeException
    {
        if(this.head!=null)
        {
            Node temp = this.head;
            List<Boy> list= new ArrayList<>();
            while (temp != null)
            {
                list.add(temp.getData());
                temp = temp.getNext();
            }
            return list;
        }
        throw  new ListaSeException("No hay datos en la lista");
        //return null;

    }

    /**
     * metodo para cambiar los niños de los extremos(el del inicio por el del final)
     * @throws ListaSeException
     */
    public void changeXtremes() throws  ListaSeException{
        /**
         * pregunta si la cabeza esta vacia t si el bracito de lacabeza esta nulo
         * se obtiene los datos del niño que esta en el inicio
         * y sel llama el ayudante y se ubica en la cabeza
         */
        if (this.head != null && this.head.getNext() != null) {
            Boy start = this.head.getData();
            Node temp = head;
            /**
             *se recorre la lista de inicio hasta el fin y mi ayudante queda en el final cuando quede parado en el que esta vacio
             */
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /**
             * se realiza el cambio de los niños y el ultimo pasaria al inicio
             */
            this.head.setData(temp.getData());
            temp.setData(start);

        }
        /**
         * si no encuengtra los datos de los niños se envia un mensaje de error
         */
        else
        {
            throw  new ListaSeException("NO es posible ejecutar el cambio de extremos");
        }


    }

    /**
     * metodo para eliminar un nino de la lista se
     * @param identification
     * @throws ListaSeException
     */
    public void delete(String identification) throws ListaSeException
    {
        if(this.head!=null)
        {
            /**
             *se busca al niño por el numero de identificacion
             */
            if(this.head.getData().getIdentification().equals(identification))
            {
                this.head = this.head.getNext();
            }
            else
            {
                /**
                 *se recorre la lista de inicio a fin hasta que el ayudante encuntre vacio o null
                 */
                Node temp = this.head;
                while(temp!=null)
                {
                    /**
                     * si el ayudante en su siguiente encuentra vacio y que la identificacion sea igual a la ingresada
                     * el ayudante se para en la ubicacion del niño
                     */
                    if(temp.getNext() != null &&
                            temp.getNext().getData().getIdentification().equals(identification))
                    {
                        break;
                    }
                    temp= temp.getNext();
                }
                /**el ayudante va estar parado en la ubicacion anterior que se va a eliminar o va a ser vacia
                 *
                 **/
                //Temp va estar parado en el anterior al que debo eliminar o va a ser null
                if(temp!= null)
                {
                    temp.setNext(temp.getNext().getNext());
                }
                else
                {

                    /**
                     * se envia un mensaje de error si el numero e identificacion no fue encontrado o no existe
                     */
                    throw  new ListaSeException("La identificación "+identification + " no existe en la lista");
                }
            }
        }
        else
        {
            /**
             * se envia un mensaje de error la lista se encuentra vacia
             */
            throw  new ListaSeException("NO hay datos en la lista");
        }
    }

    /**
     * Método que me busca en la lista simplemente enlazada, un niño a partir de la identificación
     * Si no encuentra el niño retorna vacío (null)
     * @param identification Cédula, TI, CE , Sisben que identifica el niño que voy a buscar
     * @return El niño que encontré con todos sus datos
     */
    public Boy findByIdentification(String identification) {
        /**
         * Cómo no me puedo mover de la cabeza por que s eme vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp= this.head;
        /**
         * Creo un cilo para recorrer la lista SE de principio a fin
         * llego al final cuando mi ayudante queda parado en vacío (null)
         */
        while(temp!=null)
        {
            /**
             * Pregunto si el niño en el cual está ubicado mi ayudante es el de la identificación
             * que estoy buscando ingresado en el parámetro identificacion
             */
            if(temp.getData().getIdentification().equals(identification))
            {
                /**
                 * Lo encontré y lo retorno de inmediato
                 * Finaliza mi método
                 */
                return temp.getData();
            }
            /**
             * Mi ayudante se pasa al siguiente nodo
             */
            temp=temp.getNext();
        }
        /**
         * Si llega a esta línea significa que no encontré el niño y retorno vacío
         */
       return null;
    }

    /**
     * metodo para validar o verificar si la lista esta vacia o limpia
     * @throws ListaSeException
     */

    public void validateListEmpty() throws ListaSeException
    {
        /**
         * se pregunta si la cabeza esta vacia
         * si es asi se envia un mensaje de error indicando que no hay datos en la lista
         */
        if(this.head==null)
        {
            throw new ListaSeException("No hay datos en la lista");
        }
    }

    /**
     * metodo para obtener niños por genero
     * @param gender
     * @return llista temporal con el dato de los niños por genero
     * @throws ListaSeException
     */

    public ListSE getListSeBoysByGender(String gender) throws ListaSeException
    {
        /**
         * pimero se valida si la lista SE esta vacia
         * se llama al atudante para que se ubique en la pocision e la cabeza
         * se crea un lista temporal
         * y se recorre la lista de inicio a fin hasta que el ayudante sea encuentre una posicion vacia
         */
        validateListEmpty();
        Node temp= this.head;
        ListSE listTemp = new ListSE();
        while(temp !=null)
        {
            /**
             * se pregunta si el dato del genero obtenido por el ayudante es igual al dato ingresado
             * en la lista temporal se adiciona el dato del ayudante
             */
            if(temp.getData().getGender().name().equals(gender))
            {
                listTemp.add(temp.getData());
            }
            temp = temp.getNext();
        }
        /**
         * se retorna la lista termpotal con el dato de los niños por genero
         */
        return listTemp;
    }

    public void variantBoys() throws ListaSeException
    {
        validateListEmpty();
        ListSE kids= this.getListSeBoysByGender("MASCULINO");
        ListSE girls= this.getListSeBoysByGender("FEMENINO");
        ListSE minList= null;
        ListSE maxList= null;
        if(kids.getCount()> girls.getCount())
        {
            minList= girls;
            maxList = kids;
        }
        else
        {
            minList = kids;
            maxList = girls;
        }
        Node temp= minList.getHead();
        int pos=2;
        while(temp != null)
        {
            maxList.addByPosition(temp.getData(), pos);
            pos = pos +2;
            temp = temp.getNext();
        }
        this.head= maxList.getHead();

    }

    /**
     * Método que recibe el código de una ciudad y retorna la cantidad de niños
     */
    public int getCountBoysByLocation(String code)
    {
        /**
         * se llama al ayudante para que se ubique en la posicion de la cabeza
         * se inicializa un contador en cero
         * el ayudante  recorre la lista de inicio a fin
         */
        Node temp= this.getHead();
            int count=0;
            while(temp != null)
            {
                /**
                 * se pregunta si el dato el codigo de la lacoacion donde hay niños es igual al codifo ingresado
                 * cuenta los niños de esa ciudad
                 */
                if(temp.getData().getLocation().getCode().equals(code))
                {
                    count++;
                }
                temp = temp.getNext();
            }
        /**
         * retorna el numero de niños contados en dato de la ciudad ingresada
         */
        return count;

    }

}

