package controller;

import modeles.SkillExperience;
import org.junit.jupiter.api.Test;
import GlobalVariables.EnvironmentVariable;
import modeles.Developer;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
public class DevelopersControllerTests {
    @Test
    public void testGetAllDevelopers() throws Exception {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

            when(mockConnection.createStatement()).thenReturn(mockStatement);
            when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true, false);
            when(mockResultSet.getInt("id")).thenReturn(1);
            when(mockResultSet.getString("first_name")).thenReturn("John");
            when(mockResultSet.getString("last_name")).thenReturn("Doe");
            when(mockResultSet.getString("email")).thenReturn("example@example.com");
            when(mockResultSet.getString("skill_name")).thenReturn("java");
            when(mockResultSet.getInt("experience_id")).thenReturn(1);
            when(mockResultSet.getInt("experience")).thenReturn(1);
            when(mockResultSet.getString("level")).thenReturn("junior");
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());

            EnvironmentVariable.setConnection(mockConnection);

            List<Developer> developers = DevelopersController.getAllDevelopers();

            assertEquals(1, developers.size());
            verify(mockConnection, times(1)).createStatement();
            verify(mockStatement, times(1)).executeQuery(anyString());
            verify(mockResultSet, times(3)).next();
    }

    @Test
    public void testGetDeveloperById() throws Exception {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("John");
        when(mockResultSet.getString("last_name")).thenReturn("Doe");
        when(mockResultSet.getString("email")).thenReturn("e@e.com");
        when(mockResultSet.getString("skill_name")).thenReturn("java");
        when(mockResultSet.getInt("experience_id")).thenReturn(1);
        when(mockResultSet.getInt("experience")).thenReturn(1);
        when(mockResultSet.getString("level")).thenReturn("junior");
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        Developer developer = DevelopersController.getDeveloperById(1);

        assertEquals(1, developer.getId());
        verify(mockConnection, times(2)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(2)).executeQuery();
        verify(mockResultSet, times(2)).next();
    }

    /*
    @Test
    public void testCreateDeveloper() throws Exception {
        Developer developer = new Developer();
        developer.setId(1);
        developer.setName("John Doe");
        developer.setEmail("e@e.com");

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        Developer newDeveloper = DevelopersController.createDeveloper(developer);

        assertEquals(developer.getId(), newDeveloper.getId());
        assertEquals(developer.getName(), newDeveloper.getName());
        assertEquals(developer.getEmail(), newDeveloper.getEmail());
    }

    @Test
    public void testUpdateDeveloper() throws Exception {
        Developer developer = new Developer();
        developer.setId(1);
        developer.setName("John Doe");
        developer.setEmail("e@e.com");

        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);

        Developer updatedDeveloper = DevelopersController.updateDeveloper(developer);

        assertEquals(developer.getId(), updatedDeveloper.getId());
        assertEquals(developer.getName(), updatedDeveloper.getName());
        assertEquals(developer.getEmail(), updatedDeveloper.getEmail());

    }
    */

    @Test
    public void testDeleteDeveloper() throws Exception {
        Developer developer = new Developer();
        developer.setId(1);
        developer.setName("John Doe");
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
        EnvironmentVariable.setConnection(mockConnection);


        DevelopersController.deleteDeveloper(developer);

        verify(mockConnection, times(4)).prepareStatement(anyString());
        verify(mockPreparedStatement, times(2)).executeUpdate();
    }

}
