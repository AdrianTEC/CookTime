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
public class RecetaManager {

    private  JSONArray array;  //ARREGLO DE TODOS LOS USUARIOS
    private final ServletContext context; //CONTEXTO NECESARIO PARA ENCONTRAR LAS COSAS
    private File datafile;
    public RecetaManager(ServletContext x)
    {
        context=x; //para ubicar los archivos  ocupamos un contexto
        refreshArray();
    }

    /* This returns the JSON array
   @Author: Andrés Quirós
   @Version: 7/7/20
   @Params:
   @Exeption:
   @returns: JSONArray
 */

    public JSONArray giveMeJson(){
        return  array;
    }
    /* This returns a Requested user  exploring array
   @Author: Andrés Quirós
   @Version: 7/7/20
   @Params: String id
   @Exeption:nothing
   @returns: JSONObject
 */
    public JSONObject giveMeReceta(String id){
        JSONObject receta= new JSONObject();
        for (Object i: array){

            receta= (JSONObject) i;
            if(receta.get("id").equals(id)){
                break;
            }
        }


        return receta;
    }
    public void addToArray(JSONObject thing)
    {
        array.add(thing);

    }

    /* This saves the current state of JSON file
   @Author: Andrés Quirós
   @Version: 7/7/20
   @Params: nothing
   @Exeption: IOExeption
   @returns: nothinh
 */

    public void saveJSONfile()
    {
        try {
            FileWriter escritor= new FileWriter(datafile);
            escritor.write(array.toJSONString());
            escritor.flush();
            escritor.close();
        }catch (Exception ignored){}

    }
    /* This updates the context of "array" in case of deleting or adding something
@Author: Andrés Quirós
@Version: 7/7/20
@Params: nothing
@Exeption: IOExeption
@returns: nothing
*/
    public void refreshArray()
    {

        try {
            JSONParser parser = new JSONParser();
            datafile = new File(context.getRealPath("WEB-INF/recetas.text"));
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(datafile));
            array = (JSONArray) jsonObject.get("Recetas");


        } catch(Exception ignored) { }

    }
}