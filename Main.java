public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    // Ejemplificacion SRP---------------------------------------------------------------------------------------------------------
    // Dentro de la clase coche no deberia existir con metodos no correspondientes
    class Coche {
        String marca;

        Coche(String marca){ this.marca = marca; }

        String getMarcaCoche(){ return marca; }

        void guardarCocheDB(Coche coche){ ... }
    }

    // se crea una clase encargada de cumplir esa funcion que no corresponde a la principal
    class Coche {
        String marca;

        Coche(String marca){ this.marca = marca; }

        String getMarcaCoche(){ return marca; }
    }

    class CocheDB{
        void guardarCocheDB(Coche coche){ ... }
        void eliminarCocheDB(Coche coche){ ... }
    }


    //Ejemplificacion  Open/closed principle------------------------------------------------------------------------------------------
    // Al existe de forma cerrada los datos de los vehiculos, al crear mas se debera modificar el codigo lo cual va en contra de los principios
    class Coche {
        String marca;

        Coche(String marca){ this.marca = marca; }

        String getMarcaCoche(){ return marca; }
    }

    public static void main(String[] args) {
        Coche[] arrayCoches = {
                new Coche("Renault"),
                new Coche("Audi")
        };
        imprimirPrecioMedioCoche(arrayCoches);
    }

    public static void imprimirPrecioMedioCoche(Coche[] arrayCoches){
        for (Coche coche : arrayCoches) {
            if(coche.marca.equals("Renault")) System.out.println(18000);
            if(coche.marca.equals("Audi")) System.out.println(25000);
        }
    }

    Coche[] arrayCoches = {
            new Coche("Renault"),
            new Coche("Audi"),
            new Coche("Mercedes")
    };



    public static void imprimirPrecioMedioCoche(Coche[] arrayCoches){
        for (Coche coche : arrayCoches) {
            if(coche.marca.equals("Renault")) System.out.println(18000);
            if(coche.marca.equals("Audi")) System.out.println(25000);
            if(coche.marca.equals("Mercedes")) System.out.println(27000);
        }
    }
    // la solucion se da por medio de la creacion de una sola clase abstrcta que permite que las demas copien los datos que no coinciden entre ellas asi creando mas clases y evidatando modificar el existene
    abstract class Coche {
        // ...
        abstract int precioMedioCoche();
    }

    class Renault extends Coche {
        @Override
        int precioMedioCoche() { return 18000; }
    }

    class Audi extends Coche {
        @Override
        int precioMedioCoche() { return 25000; }
    }

    class Mercedes extends Coche {
        @Override
        int precioMedioCoche() { return 27000; }
    }

    public static void main(String[] args) {

        Coche[] arrayCoches = {
                new Renault(),
                new Audi(),
                new Mercedes()
        };

        imprimirPrecioMedioCoche(arrayCoches);
    }

    public static void imprimirPrecioMedioCoche(Coche[] arrayCoches){
        for (Coche coche : arrayCoches) {
            System.out.println(coche.precioMedioCoche());
        }
    }

    //Ejemplificacion   Liskov substitution principle ---------------------------------------------------------------------------------------
    //AL Generar una clase que hereda de Bird, las clases hijo no pueden heredar correctamente, para validarlo, se crea una clase aparte que herede de bird y cumpla con mas cualidades de la otras clase hijo
    public interface Bird {
        void makeSound();
    }

    public interface FlyingBird extends Bird {
        void fly();
    }

    public class Sparrow implements FlyingBird {
        public void fly() {
            // fly logic
        }
        public void makeSound() {
            // chirp
        }
    }

    public class Ostrich implements Bird {
        public void makeSound() {
            // grunt
        }
    }



    //Ejemplificacion   Interface segregation principle ----------------------------------------------------------------------------
    // no deberian usar interfaces que no implementan
    interface IAve {
        void volar();
        void comer();
    }

    class Loro implements IAve{

        @Override
        public void volar() {
            //...
        }

        @Override
        public void comer() {
            //..
        }
    }

    class Tucan implements IAve{
        @Override
        public void volar() {
            //...
        }

        @Override
        public void comer() {
            //..
        }
    }

    // separa las diversas funciones en interfaces para que sean usadas segun correspondan dentro de cada clase

    interface IAve {
        void comer();
    }
    interface IAveVoladora {
        void volar();
    }

    interface IAveNadadora {
        void nadar();
    }

    class Loro implements IAve, IAveVoladora{

        @Override
        public void volar() {
            //...
        }

        @Override
        public void comer() {
            //...
        }
    }

    class Pinguino implements IAve, IAveNadadora{

        @Override
        public void nadar() {
            //...
        }

        @Override
        public void comer() {
            //...
        }
    }

    //Ejemplificacion  Dependency inversion principle --------------------------------------------------------------------
    //Los módulos de alto nivel no deberían depender de módulos de bajo nivel. Ambos deberían depender de abstracciones.
    //
    class DatabaseService{
        //...
        void getDatos(){ //... }
        }

        class AccesoADatos {

            private DatabaseService databaseService;

            public AccesoADatos(DatabaseService databaseService){
                this.databaseService = databaseService;
            }

            Dato getDatos(){
                databaseService.getDatos();
                //...
            }
        }


        //Para arreglar esto, podemos hacer que el módulo dependa de una abstracción más genérica


        interface Conexion {
            Dato getDatos();
            void setDatos();
        }

        class AccesoADatos {

            private Conexion conexion;

            public AccesoADatos(Conexion conexion){
                this.conexion = conexion;
            }

            Dato getDatos(){
                conexion.getDatos();
            }
        }



        lass DatabaseService implements Conexion {

            @Override
            public Dato getDatos() { //... }

                @Override
                public void setDatos() { //... }
                }

                class APIService implements Conexion{

                    @Override
                    public Dato getDatos() { //... }

                        @Override
                        public void setDatos() { //... }
                        }


}