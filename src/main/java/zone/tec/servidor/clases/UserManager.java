package zone.tec.servidor.clases;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.servlet.ServletContext;

/**
 *
 * Código inspirado por el enseñado por  xcheko51x
 * https://github.com/xcheko51x/Leer-JSON-JSONSimple-JAVA
 */
public class UserManager {

    private  JSONArray array;  //ARREGLO DE TODOS LOS USUARIOS
    private final ServletContext context; //CONTEXTO NECESARIO PARA ENCONTRAR LAS COSAS
    private File datafile;
    private JSONObject jsonObject;

    public UserManager(ServletContext x)
        {
            context=x; //para ubicar los archivos  ocupamos un contexto
            refreshArray();
        }

    /* This returns the JSON array
   @Author: Adrian Gonzalez
   @Version: 5/07/20
   @Params:
   @Exeption:
   @returns: JSONArray
    */

    public JSONArray giveMeJson(){
        return  array;
    }
    /* This returns a Requested user  exploring array
   @Author: Adrian Gonzalez
   @Version: 5/07/20
   @Params: String id
   @Exeption:nothing
   @returns: JSONObject
    */
    public JSONObject giveMeUser(String id){
        JSONObject user= new JSONObject();
        for (Object i: array){

                    user= (JSONObject) i;
                    if(user.get("id").equals(id)){
                        break;
                    }
        }


        return user;
    }
    public void addToArray(JSONObject thing)
        {
        array.add(thing);

        }
        /* This saves the current state of JSON file
   @Author: Adrian Gonzalez
   @Version: 5/07/20
   @Params: nothing
   @Exeption: IOExeption
   @returns: nothinh
    */

    public void saveJSONfile()
        {
            try {
                FileWriter escritor= new FileWriter(datafile);
                escritor.write(jsonObject.toJSONString());
                escritor.flush();
                escritor.close();
            }catch (Exception ignored){}

        }
    /* This updates the context of "array" in case of deleting or adding something
    @Author: Adrian Gonzalez
    @Version: 5/07/20
    @Params: nothing
    @Exeption: IOExeption
    @returns: nothing
    */
    public void refreshArray()
        {

            try {
                JSONParser parser = new JSONParser();
              datafile = new File(context.getRealPath("WEB-INF/clients.text"));//  Esto adquiere el documento desde el archivo WAR, por lo tanto si usamos este no podremos editar el texto

                // datafile= new File("C:\\Users\\Adrián González\\Desktop\\CookTime-CE1103\\CookTime\\web\\WEB-INF\\clients.text");

                jsonObject = (JSONObject) parser.parse(new FileReader(datafile));
                array = (JSONArray) jsonObject.get("Usuarios");


            } catch(Exception ignored) { }

        }
}