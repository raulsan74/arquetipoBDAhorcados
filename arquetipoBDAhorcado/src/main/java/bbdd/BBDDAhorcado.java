package bbdd;

import java.sql.*;

import arquetipoBDAhorcado.AhorcadoException;


public class BBDDAhorcado {
	
	 
    private String servidor;
    private String usuario;
    private String pass=""; 
    private String bbdd="ahorcadobd";
    
    
    

    public BBDDAhorcado(){
        
        this.servidor = "jdbc:mysql://localhost:3306";
        this.bbdd = "ahorcadobd"; 
        this.usuario = "root";
        this.pass = ""; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (Exception e) {
            throw new AhorcadoException("Driver de BD no localizado"+e);
        }
    }
    
    
    
    public PlayerPojo getPlayer(String nombrePlayer) {
		PlayerPojo player;		
		player=new PlayerPojo();		
		
		 Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rst = null;

        try {
            //1. Conectar a bbdd
        	
        	 con = DriverManager.getConnection( this.servidor + "/" + this.bbdd, this.usuario, this.pass);

            //2. recuperar datos de SELECT filtrando por el nombre
            String sql = "SELECT * FROM partida WHERE nombre = ?";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nombrePlayer);
            rst = pstmt.executeQuery();

            //3. rellenar pojo con los datos. Si no se ha encontrado datos, devolver Pojo con nombre vacio
            if (rst.next()) {
                player.setNombre(rst.getString("nombre"));
                player.setEstado(rst.getInt("estado"));
                player.setIntentos(rst.getInt("intentos"));
                player.setPalabraJuego(rst.getString("palabraJuego"));
                player.setLetrasUtilizadas(rst.getString("letrasUtilizadas"));	
            } else {
                player.setNombre("");
            }

            //4. Cerrar conexion bbdd
            rst.close();
            pstmt.close();
            con.close();
        }
		//debe existir control de excepciones
        catch (Exception e) {
            throw new AhorcadoException("No es un jugador"+e);
        
        
        }
		return player;		
	}
    
    
    public void savePlayer(PlayerPojo player) {
    	
    	 Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rst = null;
        try {
            // 1. Conectar a bbdd
            con = DriverManager.getConnection( this.servidor + "/" + this.bbdd, this.usuario, this.pass);

            // 2. Realizar select para comprobar si ya existe el player en la tabla.
            String sqlSelect = "SELECT * FROM partida WHERE nombre = ?";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlSelect);
            pstmtSelect.setString(1, player.getNombre());
            rst = pstmtSelect.executeQuery();

            // 2.2 Si existe, realizamos un UPDATE sobre el registro
            if (rst.next()) {
                String sqlUpdate = "UPDATE partida SET estado = ?, intentos = ?, palabraJuego = ?, letrasUtilizadas = ? WHERE nombre = ?";
                PreparedStatement pstmtUpdate = con.prepareStatement(sqlUpdate);
                
                pstmtUpdate.setInt(1, player.getEstado());
                pstmtUpdate.setInt(2, player.getIntentos());
                pstmtUpdate.setString(3, player.getPalabraJuego());
                pstmtUpdate.setString(4, player.getLetrasUtilizadas());
                pstmtUpdate.setString(5, player.getNombre());
               
                
                pstmtUpdate.executeUpdate();
                pstmtUpdate.close();
            }
            // 2.3 Si no existe, realizamos un INSERT
            else {
                String sqlInsert = "INSERT INTO partida (nombre, estado, intentos,  palabraJuego, letrasUtilizadas) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmtInsert = con.prepareStatement(sqlInsert);
                pstmtInsert.setString(1, player.getNombre());
                pstmtInsert.setInt(2, player.getEstado());
                pstmtInsert.setInt(3, player.getIntentos());
                pstmtInsert.setString(4, player.getLetrasUtilizadas());
                pstmtInsert.setString(5, player.getPalabraJuego());
                pstmtInsert.executeUpdate();
                pstmtInsert.close();
            }

            // 3. Se cierra conexión
            rst.close();
            pstmtSelect.close();
            con.close();
        }
        // Control de excepciones
        catch (Exception e) {
            throw new AhorcadoException("No se puede guadar Información"+e);
        }
    }
		
		//1. Conectar a bbdd
		//2. recuperar datos de SELECT filtrando por el nombre
		//3. rellenar pojo con los datos. Si no se ha encontrado datos, devolver Pojo con nombre vacio
		//4. Cerrar conexion bbdd
		
		//debe existir control de excepciones
		
		
    public void cleanPlayer(PlayerPojo player) {
    	
    	 Connection con = null;
	        PreparedStatement pstmt = null;
	       
    	
    	
    	 try {
             // 1. Conectar a bbdd
             con = DriverManager.getConnection( this.servidor + "/" + this.bbdd, this.usuario, this.pass);
    	 
              pstmt=con.prepareStatement("delete from partida where nombre=?"); 
             pstmt.setString(1, player.getNombre());
             int i=pstmt.executeUpdate();
             
              
              pstmt.close();
          
    	 }
         catch (Exception e) {
             throw new AhorcadoException("No se puede limpiar el registro Jugador"+e);
         }
    	 
    	 
    }
    }
    
   

