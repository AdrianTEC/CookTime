package zone.tec.servidor.clases;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.gson.Gson;
import org.apache.catalina.startup.Catalina;
import org.apache.catalina.startup.Tomcat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.servlet.ServletContext;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * Código inspirado por el enseñado por  xcheko51x
 * https://github.com/xcheko51x/Leer-JSON-JSONSimple-JAVA
 */

public class JSONManager {

    private JSONArray userArray;  //ARREGLO DE TODOS LOS USUARIOS
    private JSONArray profilesArray;
    private JSONArray companiesArray;
    private JSONArray recipesArray;
    private final ServletContext context; //CONTEXTO NECESARIO PARA ENCONTRAR LAS COSAS
    private File datafile;
    private JSONObject jsonObject;

    public JSONManager(ServletContext x)
        {
            context=x; //para ubicar los archivos  ocupamos un contexto
            refreshArray();
        }




    /** This returns the JSON array asked for
   @Author: Adrian Gonzalez
   @Version: 5/07/20
   @Params:
   @Exeption:
   @returns: JSONArray
    **/

    public JSONArray giveMeJson(String arrayName){
        JSONArray array= new JSONArray();
        if(arrayName.equals("Users")){ array=userArray; }
        if(arrayName.equals("Profiles")){array=profilesArray;}
        if(arrayName.equals("Companies")){array=companiesArray;}
        if(arrayName.equals("Recipes")){array=recipesArray;}
        return  array;
    }





    /** This returns a Requested JSONobject from arrayName array
   @Author: Adrian Gonzalez
   @Version: 5/07/20
   @Params: String id ,String arrayName
   @Exeption:nothing
   @returns: JSONObject
    **/

    public JSONObject giveMeObjetWithdId(String arrayName,String id){
        //will returns this
        JSONObject object= new JSONObject();
        //this array will be explored
        JSONArray currentArray= null;

        if(arrayName.equals("Users")){
            currentArray= userArray; }
        if (arrayName.equals("Profiles"))
            { currentArray= profilesArray; }
        if(arrayName.equals("Companies")){
            currentArray= companiesArray; }
        if(arrayName.equals("Recipes")){
            currentArray= recipesArray; }
        if(currentArray!=null)
            {   //explores the array looking for
                for (Object i: currentArray){

                    object= (JSONObject) i;
                    if(object.get("id").equals(id)){
                        break;
                    }
                }
            }


        return object;
    }
    public void addToArray(String arrayName, JSONObject thing)
        {
            if(arrayName.equals("Users"))
                {
                    userArray.add(thing);

                }
            if(arrayName.equals("Profiles"))
            {
                profilesArray.add(thing);

            }

            if(arrayName.equals("Companies"))
            {
                companiesArray.add(thing);

            }
            if(arrayName.equals("Recipes"))
            {
                recipesArray.add(thing);

            }
        }
        /** This saves the current state of JSON file
   @Author: Adrian Gonzalez
   @Version: 5/07/20
   @Params: nothing
   @Exeption: IOExeption
   @returns: nothing
    **/

    public void saveJSONfile()
        {
            try {
                jsonObject.put("Usuarios",userArray);
                jsonObject.put("Empresas",companiesArray);
                jsonObject.put("Recetas",recipesArray);

                FileWriter escritor= new FileWriter(datafile);
                escritor.write(jsonObject.toJSONString());
                escritor.flush();
                escritor.close();
            }catch (Exception ignored){
            }

        }
    /** This updates the context of "array" in case of deleting or adding something
    @Author: Adrian Gonzalez
    @Version: 5/07/20
    @Params: nothing
    @Exeption: IOExeption
    @returns: nothing
    **/
    public void refreshArray()
        {

            try {
                JSONParser parser = new JSONParser();
                datafile = new File(context.getRealPath("WEB-INF/clients.JSON"));//  Esto adquiere el documento desde el archivo WAR, por lo tanto si usamos este no podremos editar el texto
                //datafile = new File("C:\\Users\\Adrián González\\Desktop\\CookTime\\web\\WEB-INF\\clients.JSON");//  Esto adquiere el documento desde el archivo WAR, por lo tanto si usamos este no podremos editar el texto

                jsonObject = (JSONObject) parser.parse(new FileReader(datafile));
                userArray = (JSONArray) jsonObject.get("Usuarios");


                profilesArray=(JSONArray) jsonObject.get("Perfiles");
                companiesArray=(JSONArray) jsonObject.get("Empresas");
                recipesArray=(JSONArray) jsonObject.get("Recetas");


            } catch(Exception ignored) { }

        }
    private String verificarClave(String clave) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(clave.getBytes());
            byte[] convertidor = md.digest();
            return DatatypeConverter.printHexBinary(convertidor).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    /** This Converts java objects in JSON objects
    @Author: Adrian Gonzalez
    @Version: 7/07/20
    @Params: Object thing
    @Exeption:
    @returns: JSONObject
    **/
    public JSONObject convertToJSON(Object thing)
    {

        JSONParser parser = new JSONParser();
        Gson gson= new Gson();
        JSONObject response= new JSONObject();
        try {
            response= (JSONObject) parser.parse(gson.toJson(thing));
        }catch (Exception ignored){}


        return  response;
    }
    public  void removeObjectWidthID(String id,String arrayname)
        {
            JSONArray array= giveMeJson(arrayname);
            int p=0;
            for(Object i:array)
                {
                    if(((JSONObject)i).get("id").equals(id) )
                        {
                        }
                    else {p++;}
                }
            array.remove(0);


        }
}