package zone.tec.servidor.clases;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * Código inspirado por el enseñado por  xcheko51x
 * https://github.com/xcheko51x/Leer-JSON-JSONSimple-JAVA
 */
public class LectorJson {
    public LectorJson() {



        JSONParser parser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("clients.JSON"));


            array = (JSONArray) jsonObject.get("Usuarios");


        } catch(Exception ignored) { }

    }

    private  JSONArray array;
    public JSONArray read(String args) {

        JSONParser parser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("usuarios.json"));


             array = (JSONArray) jsonObject.get("Usuarios");
            /*
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);

                System.out.println("DATOS DEL USUARIO: " + i);
                System.out.println("ID: " + jsonObject1.get("id"));
                System.out.println("Nombre: " + jsonObject1.get("nombre"));
                System.out.println("Telefono: " + jsonObject1.get("telefono"));
                System.out.println("Email: " + jsonObject1.get("email"));

            }*/

        } catch(Exception ignored) { }

    return array;
    }
    public JSONObject giveMe(String ID)
        {
            JSONObject x= new JSONObject();

            for ( Object i:array)
                {
                    if(((JSONObject) i).toString().contains(ID)){
                        x= (JSONObject) i;
                    }
                }
            return x;
        }

    public  void write(JSONObject x)
    {


    }
    public JSONArray giveMeJson(){
        return  array;
    }
}