package zone.tec.servidor.clases;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class  ServerSocketTom implements  Runnable {
    public   String Host;
    public  String ip;



    public ServerSocketTom() {
        ip= "vacio";
        Host= "vacio";

    }


    public static void main(String args[])
    {
        // Aquí va el cuerpo del método main.
    }
    @Override
    public void run() {


        try {

            ServerSocket servidor= new ServerSocket(8080); //intenta hacer la conexion
            Paquete paquete_rec;

            //noinspection InfiniteLoopStatement
            while (true) {


                Socket puenteS = servidor.accept();

                /*This catch who is connected
                  get from https://www.youtube.com/watch?v=zsONbH8HDEk&t=22s
                 */
                if(puenteS.getInetAddress()!=null){

                    InetAddress location=  puenteS.getInetAddress();
                    String IpRemota = location.getHostAddress();



                }




                ObjectInputStream informacion = new ObjectInputStream(puenteS.getInputStream());

                paquete_rec = (Paquete) informacion.readObject();




                /* Get from https://www.reddit.com/r/javahelp/comments/7qvqau/problem_with_updating_gui_javafx/
                 * it allows me to edit the DialogBox what is bein used by Hilo
                 */


                String ip = paquete_rec.getIp();


                //ASK IF IP IS EMPTY

                if(!ip.equals("")) {
                    try{
                        //BUILD SOCKET TO DESTINATARY
                        Socket destino = new Socket(ip, 9999 );
                        // MAKE A DATA FLOW
                        ObjectOutputStream P_reenvio = new ObjectOutputStream(destino.getOutputStream());


                        //OVERWRITTING P_reenvio DATA WITH P_recibido
                        P_reenvio.writeObject(paquete_rec);

                        //CLOSES SOCKETS
                        destino.close();
                        P_reenvio.close();

                        //CIERRO LOS FLUJOS DE DATOS
                    } catch (Exception ignored) {



                    }
                }

                puenteS.close();

                informacion.close();

            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }






}
