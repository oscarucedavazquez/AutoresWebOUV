package autores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionBD {

	private static final String URL_BD = "jdbc:mysql://iescristobaldemonroy.duckdns.org:33306/BD13";
	private static final String CONTRASENNA = "n130kj5k";
	private static final String USUARIO = "USER_BD13";

	private static Connection conexion;

	public static void crearConexion() throws SQLException {
		conexion = DriverManager.getConnection(URL_BD, USUARIO, CONTRASENNA);
		System.out.println("Conectado");

	}

	public static void cerrarConexion() throws SQLException {
		conexion.close();

	}

	public static int insertarAutor(String nombre, String nacionalidad, String fechaNacimiento) throws SQLException {
		Statement sentencia = conexion.createStatement();
		// numeroAct es el numero de filas afectadas
		int numeroFilasAfectadas = sentencia
				.executeUpdate("INSERT INTO Autor (Nombre, Nacionalidad, FNacimiento) VALUES  " + "('" + nombre + "', '"
						+ nacionalidad + "', '" + fechaNacimiento + "')");
		return numeroFilasAfectadas;

	}

	public static int insertarLibro(int isbn, String editorial, String titulo, String idioma, int id_autor, String tipo)
			throws SQLException {
		Statement sentencia = conexion.createStatement();
		// numeroFilasAfectadas es el numero de filas afectadas
		// Para introducir un libro se necesita antes el id del autor, esto se hacer con el principal usando el consultar autor por nombre y
		// obteniendo la id de autor ANTES de hacer el insterar libro
		int numeroFilasAfectadas = sentencia.executeUpdate("INSERT INTO Libro VALUES  " + "(" + isbn + ", '"
				+ editorial + "', '" + titulo + "', '" + idioma + "', " + id_autor + ", '" + tipo + "')");
		return numeroFilasAfectadas;
	}

	public static ResultSet consultarAutores() throws SQLException {
		ResultSet resultadoQuery;
		Statement sentencia;
		sentencia = conexion.createStatement();
		resultadoQuery = sentencia.executeQuery("select * from Autor");
		return resultadoQuery;
	}

	public static ResultSet consultarLibros() throws SQLException {
		ResultSet resultadoQuery;
		Statement sentencia;
		sentencia = conexion.createStatement();
		resultadoQuery = sentencia.executeQuery("select * from Libro");
		return resultadoQuery;
	}

	public static ResultSet consultarAutoresPorNombre(String nombreBuscar) throws SQLException {
		ResultSet resultadoQuery;
		Statement sentencia;
		sentencia = conexion.createStatement();
		resultadoQuery = sentencia.executeQuery("select * from Autor WHERE Nombre '%" + nombreBuscar + "%'");
		return resultadoQuery;
	}
}
