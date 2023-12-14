package dao;

import modelo.Jugador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;

public class JugadorDao {
	ConexionBD cn=new ConexionBD();

    public List<Jugador> obtenerTodosJugadores() {
        List<Jugador> jugadores = new ArrayList<>();

        String query = "SELECT * FROM jugadores";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Jugador jugador = new Jugador();
                jugador.setCodigo(resultSet.getInt("codigo"));
                jugador.setNick(resultSet.getString("nick"));
                jugador.setNombre(resultSet.getString("nombre"));
                jugador.setPosicion(resultSet.getString("posicion"));
                jugador.setFechaIngresoEquipo(resultSet.getDate("fecha_ing_equi").toLocalDate());
                jugador.setCodigoEquipo(resultSet.getInt("cod_equi"));

                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jugadores;
    }
   

    public boolean insertarJugador(Jugador jugador) {
        String query = "INSERT INTO jugadores (nick, nombre, posicion, fecha_ing_equi, cod_equi) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setString(1, jugador.getNick());
            preparedStatement.setString(2, jugador.getNombre());
            preparedStatement.setString(3, jugador.getPosicion());
            preparedStatement.setDate(4, java.sql.Date.valueOf(jugador.getFechaIngresoEquipo()));
            preparedStatement.setInt(5, jugador.getCodigoEquipo());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean actualizarJugador(Jugador jugador) {
        String query = "UPDATE jugadores SET nick=?, nombre=?, posicion=?, fecha_ing_equi=?, cod_equi=? WHERE codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setString(1, jugador.getNick());
            preparedStatement.setString(2, jugador.getNombre());
            preparedStatement.setString(3, jugador.getPosicion());
            preparedStatement.setDate(4, java.sql.Date.valueOf(jugador.getFechaIngresoEquipo()));
            preparedStatement.setInt(5, jugador.getCodigoEquipo());
            preparedStatement.setInt(6, jugador.getCodigo());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarJugador(int codigo) {
        String query = "DELETE FROM jugadores WHERE codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, codigo);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public Jugador buscarJugadorPorCodigo(int codigo) {
        String query = "SELECT * FROM jugadores WHERE codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapearJugadorDesdeResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private Jugador mapearJugadorDesdeResultSet(ResultSet resultSet) throws SQLException {
        Jugador jugador = new Jugador();
        jugador.setCodigo(resultSet.getInt("codigo"));
        jugador.setNick(resultSet.getString("nick"));
        jugador.setNombre(resultSet.getString("nombre"));
        jugador.setPosicion(resultSet.getString("posicion"));
        jugador.setFechaIngresoEquipo(resultSet.getDate("fecha_ing_equi").toLocalDate());
        jugador.setCodigoEquipo(resultSet.getInt("cod_equi"));

        return jugador;
    }
    
}
