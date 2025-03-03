package lfp_battle_202003134;
import java.awt.Desktop;
import java.util.*;
import java.io.*;
/**
 * @author Sebastián Elgueta 202003134
 */
public class LFP_Battle_202003134 {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<ArrayList<Object>> jugadores= new ArrayList<>();
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        int num = 0;
        do{
            System.out.println("---------------Menú---------------");
            System.out.println("1. Cargar Archivo");
            System.out.println("2. Jugar");
            System.out.println("3. Generar Reporte de Mayor Ataque");
            System.out.println("4. Generar Reporte con Mayor Defensa");
            System.out.println("5. Mostrar información del desarrolador");
            System.out.println("6. Salir");
            System.out.println("");
            System.out.print("Seleccione una opción: ");
            num = sc.nextInt();
            switch(num){
                case 1:
                    cargararchivo();
                    break;
                case 2:
                    /*
                    for (int f = 0; f < jugadores.size(); f++) {
                        System.out.print("[");
                        for (int i = 0; i < 5; i++) {
                            System.out.print(jugadores.get(f).get(i));
                            if (i< 4) {
                                System.out.print(",");
                            }
                        }
                        System.out.println("]");
                    }*/
                    iniciarbatalla();
                    
                    break;
                case 3:
                    reportedeataque(jugadores);
                    break;
                case 4:
                    reportededefensa(jugadores);
                    break;
                case 5:
                    System.out.println("");
                    System.out.println("");
                    System.out.println("----------------------------------------------------");
                    System.out.println("--------------Sebastián Elgueta Ibarra--------------");
                    System.out.println("----------------CARNE_202003134---------------------");
                    System.out.println("---------------CUI_3007480000101--------------------");
                    System.out.println("----------------------------------------------------");
                    System.out.println("");
                    System.out.println("");
                    break;
                case 6:
                    System.out.println("--------Gracias por usar nuestra aplicación----------");
                    System.exit(0);
                default:
                    System.out.println("______________Opción no válida______________");
                    System.out.println("");
            }
            }while(num != 6);
    }
    
    
    
    public static void cargararchivo() {
        if (jugadores.size()!=0) {
            System.out.println("");
            System.out.println("........Ya existe un archivo cargado.........");
            System.out.println("");
        }else{
            System.out.print("Escriba el nombre del archivo que desea cargar: ");
            String document = sc.next();
            try (BufferedReader br = new BufferedReader(new FileReader(document))) {
                String linea;
                //System.out.println("\nContenido del archivo "+document+" :");
                while ((linea = br.readLine()) != null) {
                    //System.out.println(linea);
                    String[] datos = linea.split("\\|");
                    ArrayList<Object> personaje = new ArrayList<>();
                    personaje.add(true);
                    for (String valor : datos) {
                        try {
                            int numero = Integer.parseInt(valor);
                            personaje.add(numero);
                        } catch (NumberFormatException e) {
                            personaje.add(valor);
                        }
                    }
                    jugadores.add(personaje);
                }
                jugadores.remove(0);
                if (jugadores.size()%2==0) {
                    System.out.println("");
                    System.out.println("-----------------------");
                    System.out.println("Archivo Par Cargado");
                    System.out.println("-----------------------");
                    System.out.println("");
                }else{
                    System.out.println("");
                    System.out.println("-----------------------");
                    System.out.println("Archivo Impar Cargado");
                    System.out.println("-----------------------");
                    System.out.println("");
                }

            } catch (FileNotFoundException e) {
                System.out.println("El archivo no existe.");
            } catch (IOException e) {
                System.out.println("Error al leer el archivo.");
            }
        }
    }
    
    public static void iniciarbatalla(){
        if (jugadores.size()==0) {
            System.out.println("");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("Ningún archivo cargado");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("");
        }else{
            batalla();
        }
    }
    
    public static void batalla(){
        reportedeataqueydefensa(jugadores);
        do {
            System.out.println("");
            System.out.println("====================   NUEVA   RONDA   =====================");
            for (int i = 0; i < jugadores.size(); i+=2) {
                try {
                    System.out.println("");
                    System.out.println("#####___Batalla entre "+jugadores.get(i).get(1)+" vs "+jugadores.get(i+1).get(1)+"___#####");
                    
                } catch (Exception e) {
                    System.out.println(jugadores.get(i).get(1)+" avanza a la siguente ronda por default.");
                    System.out.println("");
                    break;
                }
                System.out.println("");
                
                int vidajugador1 = (int)jugadores.get(i).get(2);
                int vidajugador2 = (int)jugadores.get(i+1).get(2);
                
                while (vidajugador1>0&&vidajugador2>0) {                    
                    int ataque = (int)jugadores.get(i).get(3)-(int)jugadores.get(i+1).get(4);
                    System.out.println(jugadores.get(i).get(1)+" ataca a "+jugadores.get(i+1).get(1)+" causando "+ataque+" de daño.");
                    vidajugador2 -= ataque;
                    //System.out.println(jugadores.get(i+1).get(1)+" tiene "+vidajugador2+" de vida.");
                    if (vidajugador2<=0) {
                        System.out.println("");
                        System.out.println("******"+jugadores.get(i).get(1)+" gana la batalla.******");
                        System.out.println("_____________________________________________________________");
                        jugadores.get(i+1).set(0, false);
                        break;
                    } 
                    
                    ataque = (int)jugadores.get(i+1).get(3)-(int)jugadores.get(i).get(4);
                    System.out.println(jugadores.get(i+1).get(1)+" ataca a "+jugadores.get(i).get(1)+" causando "+ataque+" de daño.");
                    vidajugador1 -= ataque;
                    //System.out.println(jugadores.get(i).get(1)+" tiene "+vidajugador1+" de vida.");
                    if (vidajugador1<=0) {
                        System.out.println("");
                        System.out.println("******"+jugadores.get(i+1).get(1)+" gana la batalla.******");
                        System.out.println("_____________________________________________________________");
                        jugadores.get(i).set(0, false);
                        break;
                    } 
                };
            }
            
            //Jugadores en pie
            /*for (int f = 0; f < jugadores.size(); f++) {
                        System.out.print("[");
                        for (int i = 0; i < 5; i++) {
                            System.out.print(jugadores.get(f).get(i));
                            if (i< 4) {
                                System.out.print(",");
                            }
                        }
                        System.out.println("]");
            }*/
            
            //Eliminación de jugadores
            for (int i = 0; i < jugadores.size(); i++) {
                if ((boolean)jugadores.get(i).get(0)==false) {
                    jugadores.remove(i);
                    i=0;
                }
            }
            
            //Lista sobrevivientes
            /*System.out.println("-------------Sobrevivientes-------------");
            for (int f = 0; f < jugadores.size(); f++) {
                        System.out.print("[");
                        for (int i = 0; i < 5; i++) {
                            System.out.print(jugadores.get(f).get(i));
                            if (i< 4) {
                                System.out.print(",");
                            }
                        }
                        System.out.println("]");
            }*/
            
        } while (jugadores.size()>1);
        System.out.println("");
        System.out.println("**__**__EL CAMPEÓN DEL TORNEO ES "+jugadores.get(0).get(1)+"__**__**");
        System.out.println("");
        jugadores.remove(0);
    }
    
    public static void reportedeataque(ArrayList<ArrayList<Object>> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("Ningún archivo cargado");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("");
            return;
        }
        
        List<ArrayList<Object>> mejorataque = new ArrayList<>(jugadores);
        mejorataque.sort((a, b) -> Integer.compare((int) b.get(3), (int) a.get(3)));
        mejorataque = mejorataque.subList(0, Math.min(5, mejorataque.size()));
        
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html lang='es'><head><meta charset='UTF-8'><title>Ranking de Personajes</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; text-align: center; }");
        html.append("table { margin: 20px auto; border-collapse: collapse; width: 50%; }");
        html.append("th, td { border: 1px solid black; padding: 10px; }");
        html.append("th { background-color: #E65545; color: white; }");
        html.append("</style>");
        html.append("</head><body>");
        html.append("<h1>Ranking de Personajes</h1>");
        //----------------------------------------------------------------------------------
        html.append("<h2>Top 5 - Mayor Ataque</h2><table>");
        html.append("<tr><th>Posición</th><th>Nombre</th><th>Ataque</th></tr>");
        for (int i = 0; i < mejorataque.size(); i++) {
            html.append("<tr><td>").append(i + 1).append("</td><td>")
                .append(mejorataque.get(i).get(1)).append("</td><td>")
                .append(mejorataque.get(i).get(3)).append("</td></tr>");
        }
        html.append("</table>");
        //----------------------------------------------------------------------------------
        html.append("</body></html>");
        
        try {
            File archivo = new File("reporte_ataque.html");
            FileWriter escritor = new FileWriter(archivo);
            escritor.write(html.toString());
            escritor.close();
            Desktop.getDesktop().browse(archivo.toURI());
            System.out.println("Reporte de ataque generado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

    public static void reportededefensa(ArrayList<ArrayList<Object>> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("Ningún archivo cargado");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("");
            return;
        }
        
        List<ArrayList<Object>> mejordefensa = new ArrayList<>(jugadores);
        mejordefensa.sort((a, b) -> Integer.compare((int) b.get(4), (int) a.get(4)));
        mejordefensa = mejordefensa.subList(0, Math.min(5, mejordefensa.size()));
        
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html lang='es'><head><meta charset='UTF-8'><title>Ranking de Personajes</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; text-align: center; }");
        html.append("table { margin: 20px auto; border-collapse: collapse; width: 50%; }");
        html.append("th, td { border: 1px solid black; padding: 10px; }");
        html.append("th { background-color: #332DE0; color: white; }");
        html.append("</style>");
        html.append("</head><body>");
        html.append("<h1>Ranking de Personajes</h1>");
        //----------------------------------------------------------------------------------
        html.append("<h2>Top 5 - Mayor Defensa</h2><table>");
        html.append("<tr><th>Posición</th><th>Nombre</th><th>Defensa</th></tr>");
        for (int i = 0; i < mejordefensa.size(); i++) {
            html.append("<tr><td>").append(i + 1).append("</td><td>")
                .append(mejordefensa.get(i).get(1)).append("</td><td>")
                .append(mejordefensa.get(i).get(4)).append("</td></tr>");
        }
        html.append("</table>");
        //----------------------------------------------------------------------------------
        html.append("</body></html>");
        
        try {
            File archivo = new File("reporte_defensa.html");
            FileWriter escritor = new FileWriter(archivo);
            escritor.write(html.toString());
            escritor.close();
            Desktop.getDesktop().browse(archivo.toURI());
            System.out.println("Reporte de defensa generado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
    
    public static void reportedeataqueydefensa(ArrayList<ArrayList<Object>> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("Ningún archivo cargado");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-");
            System.out.println("");
            return;
        }

        List<ArrayList<Object>> mejorataque = new ArrayList<>(jugadores);
        List<ArrayList<Object>> mejordefensa = new ArrayList<>(jugadores);
        
        mejorataque.sort((a, b) -> Integer.compare((int) b.get(3), (int) a.get(3)));
        mejordefensa.sort((a, b) -> Integer.compare((int) b.get(4), (int) a.get(4)));
        mejorataque = mejorataque.subList(0, Math.min(5, mejorataque.size()));
        mejordefensa = mejordefensa.subList(0, Math.min(5, mejordefensa.size()));
        
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html lang='es'><head><meta charset='UTF-8'><title>Ranking de Personajes</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; text-align: center; }");
        html.append("table { margin: 20px auto; border-collapse: collapse; width: 50%; }");
        html.append("th, td { border: 1px solid black; padding: 10px; }");
        html.append(".ataque th { background-color: #E65545; color: white; }");
        html.append(".defensa th { background-color: #332DE0; color: white; }");
        html.append("</style>");
        html.append("</head><body>");
        html.append("<h1>Ranking de Personajes</h1>");
        //----------------------------------------------------------------------------------
        html.append("<h2>Top 5 - Mayor Ataque</h2><table class='ataque'>");
        html.append("<tr><th>Posición</th><th>Nombre</th><th>Ataque</th></tr>");
        
        for (int i = 0; i < mejorataque.size(); i++) {
            html.append("<tr><td>").append(i + 1).append("</td><td>")
                .append(mejorataque.get(i).get(1)).append("</td><td>")
                .append(mejorataque.get(i).get(3)).append("</td></tr>");
        }
        html.append("</table>");
        //----------------------------------------------------------------------------------
        html.append("<h2>Top 5 - Mayor Defensa</h2><table class='defensa'>");
        html.append("<tr><th>Posición</th><th>Nombre</th><th>Defensa</th></tr>");
        
        for (int i = 0; i < mejordefensa.size(); i++) {
            html.append("<tr><td>").append(i + 1).append("</td><td>")
                .append(mejordefensa.get(i).get(1)).append("</td><td>")
                .append(mejordefensa.get(i).get(4)).append("</td></tr>");
        }
        
        html.append("</table>");
        //----------------------------------------------------------------------------------
        html.append("</body></html>");
        
        try {
            File archivo = new File("reporte_ataque_y_defensa.html");
            FileWriter escritor = new FileWriter(archivo);
            escritor.write(html.toString());
            escritor.close();
            Desktop.getDesktop().browse(archivo.toURI());
            System.out.println("Reporte generado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}
