package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.Team;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TeamControllerTests {
    @Test
    public void testGetAllTeams() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // return true first time, false second time
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Team");
        when(mockResultSet.getString("description")).thenReturn("Test Description");
        when(mockResultSet.getInt("id_project")).thenReturn(1);
        when(mockResultSet.getString("name_project")).thenReturn("Test Project");
        when(mockResultSet.getInt("id_developer")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("John");
        when(mockResultSet.getString("last_name")).thenReturn("Doe");
        when(mockResultSet.getString("email")).thenReturn("e@e.com");

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        EnvironmentVariable.setConnection(mockConnection);

        List<Team> teams = TeamController.getAllTeams();

        assertEquals(1, teams.size());
        verify(mockConnection, times(1)).createStatement();
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testGetTeamById() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Team");
        when(mockResultSet.getString("description")).thenReturn("Test Description");
        when(mockResultSet.getInt("id_project")).thenReturn(1);
        when(mockResultSet.getString("name_project")).thenReturn("Test Project");
        when(mockResultSet.getInt("id_developer")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("John");
        when(mockResultSet.getString("last_name")).thenReturn("Doe");
        when(mockResultSet.getString("email")).thenReturn("e@e.com");

        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        List<Team> team = TeamController.getTeamByIdProject(1);

        assertEquals(1, team.size());

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    @Test
    public void testCreateTeam() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        Team team = new Team();
        team.setDeveloper_name("John");
        team.setProject_name("Test Project");
        team.setId_project(1);
        team.setId_developer(1);

        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt(1)).thenReturn(1);
        EnvironmentVariable.setConnection(mockConnection);

        TeamController.createTeam(team);

        verify(mockConnection, times(1)).prepareStatement(anyString(), anyInt());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteTeam() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        Team team = new Team();
        team.setDeveloper_name("John");
        team.setProject_name("Test Project");
        team.setId_project(1);
        team.setId_developer(1);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        TeamController.deleteTeam(team.getId_project());

        verify(mockConnection, times(1)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}
