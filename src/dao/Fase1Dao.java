package dao;

import modelo.Fase1;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;

public class Fase1Dao {
	ConexionBD cn=new ConexionBD();

    public List<Fase1> obtenerTodosResultadosFase1() {
        List<Fase1> resultadosFase1 = new ArrayList<>();

        String query = "SELECT * FROM fase1";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Fase1 resultadoFase1 = new Fase1();
                resultadoFase1.setCodigo(resultSet.getInt("codigo"));
                resultadoFase1.setCodigoEquipo(resultSet.getInt("cod_equi"));
                resultadoFase1.setPuntaje(resultSet.getString("Puntaje"));
                resultadoFase1.setEstado(resultSet.getString("Estado"));
                resultadoFase1.setGrupo(resultSet.getString("Grupo"));

                resultadosFase1.add(resultadoFase1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultadosFase1;
    }
    
    

    public boolean insertarResultadoFase1(Fase1 resultadoFase1) {
        String query = "INSERT INTO fase1 (cod_equi, Puntaje, Estado, Grupo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, resultadoFase1.getCodigoEquipo());
            preparedStatement.setString(2, resultadoFase1.getPuntaje());
            preparedStatement.setString(3, resultadoFase1.getEstado());
            preparedStatement.setString(4, resultadoFase1.getGrupo());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean actualizarResultadoFase1(Fase1 resultadoFase1) {
        String query = "UPDATE fase1 SET cod_equi=?, Puntaje=?, Estado=?, Grupo=? WHERE codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, resultadoFase1.getCodigoEquipo());
            preparedStatement.setString(2, resultadoFase1.getPuntaje());
            preparedStatement.setString(3, resultadoFase1.getEstado());
            preparedStatement.setString(4, resultadoFase1.getGrupo());
            preparedStatement.setInt(5, resultadoFase1.getCodigo());

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 
    public boolean eliminarResultadoFase1(int codigo) {
        String query = "DELETE FROM fase1 WHERE codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, codigo);

            int filasAfectadas = preparedStatement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Fase1 buscarResultadoFase1PorCodigo(int codigo) {
        String query = "SELECT * FROM fase1 WHERE codigo=?";

        try (PreparedStatement preparedStatement = cn.obtenerConexion().prepareStatement(query)) {
            preparedStatement.setInt(1, codigo);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapearResultadoFase1DesdeResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    
    private Fase1 mapearResultadoFase1DesdeResultSet(ResultSet resultSet) throws SQLException {
        Fase1 resultadoFase1 = new Fase1();
        resultadoFase1.setCodigo(resultSet.getInt("codigo"));
        resultadoFase1.setCodigoEquipo(resultSet.getInt("cod_equi"));
        resultadoFase1.setPuntaje(resultSet.getString("Puntaje"));
        resultadoFase1.setEstado(resultSet.getString("Estado"));
        resultadoFase1.setGrupo(resultSet.getString("Grupo"));

        return resultadoFase1;
    }
    
    
}
